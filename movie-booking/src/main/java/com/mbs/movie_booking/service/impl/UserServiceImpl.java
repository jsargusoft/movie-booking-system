package com.mbs.movie_booking.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.mbs.movie_booking.dto.UserRegisterInfo;
import com.mbs.movie_booking.models.User;
import com.mbs.movie_booking.repository.UserRepository;
import com.mbs.movie_booking.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Validated
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(UserRegisterInfo userRegisterInfo) {
        if (userRepository.findByEmail(userRegisterInfo.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = User.builder()
                .name(userRegisterInfo.getName())
                .username(userRegisterInfo.getUsername())
                .password(passwordEncoder.encode(userRegisterInfo.getPassword()))
                .email(userRegisterInfo.getEmail())
                .phone(userRegisterInfo.getPhone())
                .build();

        userRepository.save(user);
    }

    public User getCurrentlyLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No user is currently logged in.");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userRepository.findByEmail(userDetails.getUsername()).orElse(null);

        return user;
    }
}
