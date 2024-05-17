package com.example.demo.service;

import com.example.demo.dao.ForgotPasswordDAO;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
import java.sql.SQLException;

public class ForgotPasswordService {
    private ForgotPasswordDAO forgotPasswordDAO = new ForgotPasswordDAO();

    public void updateResetToken(String email, String token) throws SQLException {
        forgotPasswordDAO.updateResetToken(email, token);
    }

    public void sendResetLink(String email, String token) throws MessagingException {
        String resetLink = "http://localhost:8080/demo_war_exploded/resetpassword.jsp?reset_token=" + token;

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("dinukrana@gmail.com", "ptsfvzgbeupjfmmo");
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("dinukrana@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject("Password Reset Request");
        message.setText("To reset your password, please click on the following link: " + resetLink);

        Transport.send(message);
    }
}
