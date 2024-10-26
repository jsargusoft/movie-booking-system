package com.mbs.movie_booking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mbs.movie_booking.service.MailService;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender) {
        this.javaMailSender = mailSender;
    }

    @Override
    public void sendEmail(String email, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom("anshumavgahlot10@gmail.com");
        message.setSubject(subject);
        message.setText(content);

        javaMailSender.send(message);
    }
}
