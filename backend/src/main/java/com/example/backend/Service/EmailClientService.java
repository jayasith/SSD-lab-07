package com.example.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailClientService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String email, String body, String subject){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("spring.email.from@gmail.com");
        mailMessage.setTo(email);
        mailMessage.setText(body);
        mailMessage.setSubject(subject);

        mailSender.send(mailMessage);
        System.out.println("email was send");
    }
}
