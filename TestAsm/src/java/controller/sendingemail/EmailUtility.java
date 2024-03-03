/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.sendingemail;

import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;

/**
 * A utility class for sending e-mail messages
 *
 * @author www.codejava.net
 *
 */

import jakarta.mail.internet.MimeMessage;

public class EmailUtility {

    public static void sendEmail(String host, String port, String user, String pass, String recipient, String subject, String content) throws MessagingException {
        // Cấu hình properties
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        // Tạo một phiên gửi email
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            // Tạo một đối tượng MimeMessage
            MimeMessage message = new MimeMessage(session);

            // Thiết lập thông tin người gửi, người nhận và chủ đề
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject, "UTF-8");

            // Thiết lập nội dung tin nhắn
            message.setText(content, "UTF-8");

            // Gửi tin nhắn
            Transport.send(message);

            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

