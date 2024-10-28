package com.mbs.movie_booking.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mbs.movie_booking.jwtSecurity.util.PasswordTokenUtil;
import com.mbs.movie_booking.models.Token;
import com.mbs.movie_booking.models.User;
import com.mbs.movie_booking.repository.TokenRepository;
import com.mbs.movie_booking.repository.UserRepository;
import com.mbs.movie_booking.service.MailService;
import com.mbs.movie_booking.service.PasswordResetService;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void initiatePasswordReset(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Token token = PasswordTokenUtil.generateResetToken(user, tokenRepository);
            tokenRepository.save(token);
            sendResetEmail(user.getEmail(), token.getValue());
        }
    }

    private void sendResetEmail(String email, String tokenValue) {
        String subject = "Password Reset Request";
        String content = "To reset your password, click the link below:\n" +
                "http://localhost:8080/reset-password?token=" + tokenValue;
        mailService.sendEmail(email, subject, content);
    }

    @Override
    public boolean validateToken(String tokenValue) {
        Optional<Token> optionalToken = tokenRepository.findByValue(tokenValue); // Fixing this line
        return optionalToken.isPresent() && optionalToken.get().isValid(); // Ensure token is valid
    }

    @Override
    public void resetPassword(String tokenValue, String newPassword) {
        Optional<Token> optionalToken = tokenRepository.findByValue(tokenValue); // Get the Optional
        if (optionalToken.isPresent() && optionalToken.get().isValid()) { // Check if present and valid
            Token token = optionalToken.get();
            User user = token.getUser();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            token.setDisabled(true);
            tokenRepository.save(token);
        }
    }
}
