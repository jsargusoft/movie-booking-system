package com.mbs.movie_booking.jwtSecurity.util;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.mbs.movie_booking.enums.TokenType;
import com.mbs.movie_booking.models.Token;
import com.mbs.movie_booking.models.User;
import com.mbs.movie_booking.repository.TokenRepository;

public class PasswordTokenUtil {
public static Token generateResetToken(User user, TokenRepository tokenRepository) {
        String tokenValue;
        Optional<Token> token;

       
        do {
            tokenValue = UUID.randomUUID().toString();
            token = tokenRepository.findByValue(tokenValue);
        } while (token.isPresent()); // Repeat until a unique token is generated

        LocalDateTime expiryDate = LocalDateTime.now().plusHours(1);
        return Token.builder()
                .type(TokenType.PASSWORD_RESET) 
                .value(tokenValue)
                .expiryDate(expiryDate)
                .user(user)
                .disabled(false)
                .build();
    }
}
