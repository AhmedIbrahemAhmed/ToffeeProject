package Regitretion;

import java.util.Random;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class OTP{

    public int createOTP(){
        Random random = new Random();
        int randomNumber = random.nextInt(90000) + 10000;
        return randomNumber;
    }
    public void sendOTP(int code , String email){
        final String username = "toffosw.m.m.a@gmail.com"; // SMTP server username
        final String password = "H5word11PUBG"; // SMTP server password
        String from = "toffosw.m.m.a@gmail.com"; // sender email address
        String host = "smtp.gmail.com"; // SMTP server host
        String port = "465"; // SMTP server port
        String subject = "Your OTP Code"; // email subject
        String body = "Your OTP is: " + code; // email body

        // set SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // create a new Session with an authenticator
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // create a new MimeMessage object
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(subject);
            message.setText(body);

            // send the email
            Transport.send(message);

            System.out.println("OTP sent successfully!");
        } catch (MessagingException e) {
            System.err.println("Failed to send OTP: " + e.getMessage());
        }
    }

}