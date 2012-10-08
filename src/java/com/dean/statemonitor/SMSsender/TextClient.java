package com.dean.statemonitor.SMSsender;


public class TextClient implements Runnable{

  public final static int SYNCHRONOUS=0;
  public final static int ASYNCHRONOUS=1;
  private Thread myThread=null;

  private int mode=-1;
  private String recipient=null;
  private String message=null;

  public int status=-1;
  public long messageNo=-1;


  public TextClient(int mode) {
      this.mode=mode;
    }

  public int sendMessage (String recipient, String message){
    this.recipient=recipient;
    this.message=message;
    myThread = new Thread(this);
    myThread.start();
    return status;
    }
    @Override
    public void run(){

    SMSSender aSender = new SMSSender(recipient,message);

    try{
     
          aSender.send ();

      if (mode==SYNCHRONOUS) {
          while (aSender.status == -1){
            Thread.sleep (1000);
          }
      }
      if (aSender.status == 0) messageNo=aSender.messageNo ;

    }catch (Exception e){

        e.printStackTrace();

    }

    this.status=aSender.status ;

    aSender=null;


  }
    public static void main(String[] args) {
            TextClient client = new TextClient(SYNCHRONOUS);
            client.sendMessage("+94778195095", "onna euuwa");
    }
}