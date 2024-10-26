package com.mbs.movie_booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mbs.movie_booking.dto.EmailRequest;
import com.mbs.movie_booking.service.MailService;

@RestController
public class MailTestController {
@Autowired
    private MailService mailService; 
   
    @PostMapping("/sendEmail")
    public String sendTestEmail(@RequestBody EmailRequest emailRequest) {
        // if (emailRequest == null) {
        //     System.out.println("EmailRequest is null");
        //     return;
        // }
        // System.out.println("hi ");
        // System.out.println("Received Email "+emailRequest.getEmail());
        // System.out.println("Received Subject: " + emailRequest.getSubject());
        // System.out.println("Received Content: " + emailRequest.getContent());
        if (emailRequest == null) {
            return "EmailRequest is null";
        }

        String email = emailRequest.getEmail();
        String subject = emailRequest.getSubject();
        String content = emailRequest.getContent();

        if (email == null || email.isEmpty()) {
            return "Failed to send email: Email address is required.";
        }
        if (subject == null || subject.isEmpty()) {
            return "Failed to send email: Subject is required.";
        }
        if (content == null || content.isEmpty()) {
            return "Failed to send email: Content is required.";
        }

        try {
            mailService.sendEmail(email, subject, content);
            return "Email sent successfully to " + email;
        } catch (Exception e) {
            return "Failed to send email: " + e.getMessage();
        }
    
    }
}

