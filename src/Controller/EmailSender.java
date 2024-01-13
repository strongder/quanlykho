package Controller;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.*;

public class EmailSender {
	public  int sendEmail(String to) {
		
		int body = randomPassword();
		// ktlk togk rlkp bhzi;
        final String username = "vanthanh1810xx@gmail.com";
        final String password = "ktlktogkrlkpbhzi";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); //smtp host
        props.put("mail.smtp.port", "587");   // port

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        try {
            //email người gửi
            message.setFrom(new InternetAddress(username));
            // Thông tin người nhận
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
           // Tiêu đề email
            message.setSubject("Phần mềm quản lý kho hàng máy tính");
            // Nội dung email
            message.setText("Mật khẩu mới:" + body);

            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        
        return body;
    }
	
	public int randomPassword()
	{
		Random r = new Random();
		int pass = r.nextInt(900000)+100000;
		return pass;
	}
}
