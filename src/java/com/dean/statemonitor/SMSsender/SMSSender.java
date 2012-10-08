package com.dean.statemonitor.SMSsender;
import com.dean.statemonitor.Properties.DB;
import java.util.Date;

public class SMSSender implements Runnable {

    private static final long STANDARD = 500;
    private static final long LONG = 2000;
    private static final long VERYLONG = 20000;
    ComConnector mySerial = null;
    static final private char cntrlZ = (char) 26;
    String in, out;
    Thread aThread = null;
    private long delay = STANDARD;
    String recipient = null;
    String message = null;
    private String csca = ""; // the message center
    private String comport = "";
    private ComParameters defaultParameters =  null;
    public int step;
    public int status = -1;
    public long messageNo = -1;

    public SMSSender(String recipient, String message) {

        try {
            System.getProperties().load(DB.class.getResourceAsStream("Statemon.properties"));
            csca = System.getProperty("MessageCenter");
            comport = System.getProperty("smsComport");
            defaultParameters = new ComParameters(comport, 9600, 0, 0, 8, 1, 0);

        } catch (Exception e) {
            System.out.println(e + "exception in the Sender.java");
        }

        this.recipient = recipient;
        this.message = message;

    }

    public int send() throws Exception {

        ComParameters params = defaultParameters;
        mySerial = new ComConnector(params);
        mySerial.openConnection();
        aThread = new Thread(this);
        aThread.start();
        //log("start");
        return 0;
    }

    /**
     * implement the dialogue thread, message / response via steps, handle time
     * out
     */
    public void run() {

        boolean timeOut = false;
        long startTime = (new Date()).getTime();



        while ((step < 7) && (!timeOut)) {
//      log(""+((new Date()).getTime() - startTime);
            //check where we are in specified delay
            timeOut = ((new Date()).getTime() - startTime) > delay;

            //if atz does not work, type to send cntrlZ and retry, in case a message was stuck
            if (timeOut && (step == 1)) {
                step = -1;
                mySerial.send("" + cntrlZ);
            }

            //read incoming string
            String result = mySerial.getIncommingString();

//    log ("<- "+result+"\n--------");
            int expectedResult = -1;

            try {
                //log ("Step:"+step);

                switch (step) {
                    case 0:

                        mySerial.send("atz");
                        delay = LONG;
                        startTime = (new Date()).getTime();
                        break;

                    case 1:
                        delay = STANDARD;
                        mySerial.send("ath0");
                        startTime = (new Date()).getTime();
                        break;
                    case 2:
                        expectedResult = result.indexOf("OK");

                        //log ("received ok ="+expectedResult);
                        if (expectedResult > -1) {
                            mySerial.send("at+cmgf=1");
                            startTime = (new Date()).getTime();
                        } else {
                            step = step - 1;
                        }
                        break;
                    case 3:
                        expectedResult = result.indexOf("OK");

                        // log ("received ok ="+expectedResult);
                        if (expectedResult > -1) {
                            mySerial.send("at+csca=\"" + csca + "\"");
                            startTime = (new Date()).getTime();
                        } else {
                            step = step - 1;
                        }

                        break;
                    case 4:
                        expectedResult = result.indexOf("OK");

                        // log ("received ok ="+expectedResult);
                        if (expectedResult > -1) {
                            mySerial.send("at+cmgs=\"" + recipient + "\"");
                            startTime = (new Date()).getTime();
                        } else {
                            step = step - 1;
                        }

                        break;
                    case 5:
                        expectedResult = result.indexOf(">");

                        // log ("received ok ="+expectedResult);
                        if (expectedResult > -1) {
                            mySerial.send(message + cntrlZ);
                            startTime = (new Date()).getTime();
                        } else {
                            step = step - 1;
                        }
                        delay = VERYLONG;//waitning for message ack

                        break;

                    case 6:
                        expectedResult = result.indexOf("OK");
                        //read message number
                        if (expectedResult > -1) {
                            int n = result.indexOf("CMGS:");
                            result = result.substring(n + 5);
                            n = result.indexOf("\n");
                            status = 0;
                            messageNo = Long.parseLong(result.substring(0, n).trim());

                            log("sent message no:" + messageNo);


                        } else {
                            step = step - 1;
                        }

                        break;
                }
                step = step + 1;

                Thread.sleep(100);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        mySerial.closeConnection();
        if (timeOut) {
            status = -2;
            log("*** time out at step " + step + "***");
        }
    }

    /**
     * logging function, includes date and class name
     */
    private void log(String s) {
        System.out.println(new java.util.Date() + ":" + this.getClass().getName() + ":" + s);
    }
}