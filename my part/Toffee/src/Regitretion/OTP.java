package Regitretion;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * The type Otpmail.
 */
public class OTP {

    /**
     * Mn int.
     *
     * @param reciever the reciever
     * @return the int
     */
    public int mn(String reciever) {
        // Set the email properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Set the email credentials
        final String username = "ahmedelnile44@gmail.com"; /*PUT UR ACTUAL MAIL*/
        final String password = "iowumtrioodilviy";  /*PUT THE PASSWORD YOU KNEW FROM 2STEP VERIFICARION*/

        // Create a new session with the email properties and credentials
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a new message
            Message message = new MimeMessage(session);

            // Set the recipient email address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(reciever)); /*HERE THE RECIEVER EMAIL*/

            // Set the email subject
            message.setSubject("OTP");
            // Generate a random OTP
            int otp = (int) (Math.random() * 1000000);

            // Set the email content
            message.setText("Your OTP is " + otp );

            // Send the email
            Transport.send(message);

            System.out.println("OTP email sent successfully!");
            return otp;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}