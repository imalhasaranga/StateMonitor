package com.dean.statemonitor.email;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class emailClient implements Runnable {


    public void startSendingThread() {
        new Thread(this).start();
    }
    public String from, to, subject, message;
    private String html;

    @Override
    public void run() {
        sendMail(from, to, subject, message);
    }

    public void sendMail(String from, String to, String subject, String message) {

        String host = "smtp.gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.gmail.com", host);
        props.put("mail.debug", "true");
        props.put("mail.smtp.user", "senders mail address");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        Session session = Session.getInstance(props);

        try {

            String email = System.getProperty("email");
            String pass = System.getProperty("password");
            from = email;
            Transport bus = session.getTransport("smtp");
            bus.connect("smtp.gmail.com", email, pass);
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            String[] aa = to.split(";");
            InternetAddress[] address = new InternetAddress[aa.length];
            int ci = 0;
            for (String tt : aa) {
                address[ci++] = (new InternetAddress(tt));
            }

            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());

            setTextContent(msg, message);
            setHTMLContent(msg);
            msg.saveChanges();
            bus.sendMessage(msg, address);
            bus.close();

        } catch (MessagingException mex) {
            while (mex.getNextException() != null) {
                Exception ex = mex.getNextException();
                if (!(ex instanceof MessagingException)) {
                    break;
                } else {
                    mex = (MessagingException) ex;
                }
            }
        }
    }

    public void setTextContent(Message msg, String mytxt) throws MessagingException {
        msg.setText(mytxt);
        msg.setContent(mytxt, "text/html");

    }

    public void setMultipartContent(Message msg) throws MessagingException {
        MimeBodyPart p1 = new MimeBodyPart();
        p1.setText("This is part one of a test multipart e-mail.");
        MimeBodyPart p2 = new MimeBodyPart();
        p2.setText("This is the second part", "us-ascii");
        Multipart mp = new MimeMultipart();
        mp.addBodyPart(p1);
        mp.addBodyPart(p2);
        msg.setContent(mp);
    }

    public void setFileAsAttachment(Message msg, String filename)
            throws MessagingException {

        MimeBodyPart p1 = new MimeBodyPart();
        p1.setText("This is part one of a test multipart e-mail."
                + "The second part is file as an attachment");

        MimeBodyPart p2 = new MimeBodyPart();

        FileDataSource fds = new FileDataSource(filename);
        p2.setDataHandler(new DataHandler(fds));
        p2.setFileName(fds.getName());

        Multipart mp = new MimeMultipart();
        mp.addBodyPart(p1);
        mp.addBodyPart(p2);

        msg.setContent(mp);
    }

    public void setHTMLContent(Message msg) throws MessagingException {

      if(getHtml() != null){
        msg.setDataHandler(new DataHandler(new HTMLDataSource(getHtml())));
      }
    }

    /**
     * @return the html
     */
    public String getHtml() {
        return html;
    }

    /**
     * @param html the html to set
     */
    public void setHtml(String html) {
        this.html = html;
    }

    class HTMLDataSource implements DataSource {

        private String html;

        public HTMLDataSource(String htmlString) {
            html = htmlString;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            if (html == null) {
                throw new IOException("Null HTML");
            }
            return new ByteArrayInputStream(html.getBytes());
        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            throw new IOException("This DataHandler cannot write HTML");
        }

        @Override
        public String getContentType() {
            return "text/html";
        }

        @Override
        public String getName() {
            return "JAF text/html dataSource to send e-mail only";
        }
    }
}
