package com.mbs.movie_booking.service;

public interface MailService {
    void sendEmail(String to, String subject, String body);
}
