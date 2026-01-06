package com.example.demo.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
    @Value("${notification.email.recipient}")
    private String recipientEmail;

    @Value("${notification.email.sender}")
    private String senderEmail;

    @Autowired
    private JavaMailSender emailSender;

    public void sendMessage(String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(recipientEmail);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
