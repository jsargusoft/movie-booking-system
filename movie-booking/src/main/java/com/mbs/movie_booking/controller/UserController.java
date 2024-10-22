package com.mbs.movie_booking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mbs.movie_booking.dto.UserRegisterInfo;
import com.mbs.movie_booking.models.User;
import com.mbs.movie_booking.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public void register(@Valid @RequestBody UserRegisterInfo userRegister) {
        userService.registerUser(userRegister);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserDetails() {
        User user = userService.getCurrentlyLoggedInUser();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

}
