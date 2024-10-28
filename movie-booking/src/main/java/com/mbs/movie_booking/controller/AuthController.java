package com.mbs.movie_booking.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbs.movie_booking.dto.LoginRequest;
import com.mbs.movie_booking.dto.LoginResponse;
import com.mbs.movie_booking.jwtSecurity.service.AuthService;
import com.mbs.movie_booking.service.PasswordResetService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {
    private final AuthService authService;
private final PasswordResetService passwordResetService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refreshToken(
            @RequestHeader(value = "Refresh-Token", required = true) String refreshToken) {
        return authService.refresh(refreshToken);
    }

    @PostMapping("/logout")
    public ResponseEntity<LoginResponse> logout(HttpServletRequest request) {
        return authService.logout(request);
    }
   @PostMapping("/reset-request") // New endpoint for password reset
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> payload) {
        // Extract email from the map
        String trimmedEmail = payload.get("email").trim();
        passwordResetService.initiatePasswordReset(trimmedEmail);
        return ResponseEntity.ok("Password reset email sent.");
    }
}
