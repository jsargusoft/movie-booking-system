package com.mbs.movie_booking.service;

public interface PasswordResetService {
    void initiatePasswordReset(String email);

    boolean validateToken(String tokenValue);

    void resetPassword(String tokenValue, String newPassword);
}
