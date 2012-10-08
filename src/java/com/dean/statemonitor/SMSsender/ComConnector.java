package com.dean.statemonitor.SMSsender;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;
import javax.comm.*;

public class ComConnector implements SerialPortEventListener, CommPortOwnershipListener {

    private ComParameters parameters;
    private OutputStream os;
    private InputStream is;
    private CommPortIdentifier portId;
    private SerialPort sPort;
    private boolean open;
    private String receptionString = "";

    public String getIncommingString() {
        byte[] bVal = receptionString.getBytes();
        receptionString = "";
        return new String(bVal);
    }

    public ComConnector(ComParameters parameters) {
        this.parameters = parameters;
        open = false;
    }

    public void openConnection() throws Exception {

        try {

            portId = CommPortIdentifier.getPortIdentifier(parameters.getPortName());
        } catch (Exception e) {
            System.out.println(e + "no such port in SerilaConn");
        }

        try {
            sPort = (SerialPort) portId.open("SMSConnector", 30000);
        } catch (PortInUseException e) {

            throw new Exception(e.getMessage());
        }
        sPort.sendBreak(1000);
        try {
            setConnectionParameters();
        } catch (Exception e) {
            sPort.close();
            throw e;
        }
        try {
            os = sPort.getOutputStream();
            is = sPort.getInputStream();
        } catch (IOException e) {
            sPort.close();
            throw new Exception("Error opening i/o streams");
        }
        try {
            sPort.addEventListener(this);
        } catch (TooManyListenersException e) {
            sPort.close();
            throw new Exception("too many listeners added");
        }

        sPort.notifyOnDataAvailable(true);
        sPort.notifyOnBreakInterrupt(true);
        try {
            sPort.enableReceiveTimeout(30);
        } catch (UnsupportedCommOperationException e) {
        }
        portId.addPortOwnershipListener(this);
        open = true;
    }

    public void setConnectionParameters() throws Exception {

        // Save state of parameters before trying a set.
        int oldBaudRate = sPort.getBaudRate();
        int oldDatabits = sPort.getDataBits();
        int oldStopbits = sPort.getStopBits();
        int oldParity = sPort.getParity();
        try {
            sPort.setSerialPortParams(parameters.getBaudRate(),
                    parameters.getDatabits(),
                    parameters.getStopbits(),
                    parameters.getParity());
        } catch (UnsupportedCommOperationException e) {
            parameters.setBaudRate(oldBaudRate);
            parameters.setDatabits(oldDatabits);
            parameters.setStopbits(oldStopbits);
            parameters.setParity(oldParity);
            throw new Exception("Unsupported parameter");
        }

        // Set flow control.
        try {
            sPort.setFlowControlMode(parameters.getFlowControlIn()
                    | parameters.getFlowControlOut());
        } catch (UnsupportedCommOperationException e) {
            throw new Exception("Unsupported flow control");
        }
    }

    public void closeConnection() {

        if (!open) {
            return;
        }
        if (sPort != null) {
            try {
                os.close();
                is.close();
            } catch (IOException e) {
                System.err.println(e);
            }
            sPort.close();
            portId.removePortOwnershipListener(this);
        }
        open = false;
    }

    public void sendBreak() {
        sPort.sendBreak(1000);
    }

    public boolean isOpen() {
        return open;
    }

    @Override
    public void serialEvent(SerialPortEvent e) {

        StringBuffer inputBuffer = new StringBuffer();
        int newData = 0;

        switch (e.getEventType()) {
            case SerialPortEvent.DATA_AVAILABLE:
                while (newData != -1) {
                    try {
                        newData = is.read();
                        if (newData == -1) {
                            break;
                        }
                        if ('\r' == (char) newData) {
                            inputBuffer.append('\n');
                        } else {
                            inputBuffer.append((char) newData);
                        }
                    } catch (IOException ex) {
                        System.err.println(ex);
                        return;
                    }
                }
                receptionString = receptionString + (new String(inputBuffer));
                break;

            // If break event append BREAK RECEIVED message.
            case SerialPortEvent.BI:
                receptionString = receptionString + ("\n--- BREAK RECEIVED ---\n");
        }

    }

    @Override
    public void ownershipChange(int type) {
       
    }

    public void send(String message) {
        byte[] theBytes = (message + "\n").getBytes();
        for (int i = 0; i < theBytes.length; i++) {

            char newCharacter = (char) theBytes[i];
            if ((int) newCharacter == 10) {
                newCharacter = '\r';
            }

            try {
                os.write((int) newCharacter);
            } catch (IOException e) {
                System.err.println("OutputStream write error: " + e);
            }

        }
        
    }
}
