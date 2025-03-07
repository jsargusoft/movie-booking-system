package com.mbs.movie_booking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterInfo {
    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Username is required.")
    private String username;

    @NotBlank(message = "Password is required.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*()]).{6,}$", message = "Password must be atleast 6 characters long and combination of uppercase letters, lowercase letters, numbers, special characters.")
    private String password;

    @NotEmpty(message = "Email is required.")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull
    @Size(min = 10, max = 10, message = "Phone number must contain exactly 10 digits")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must contain only digits")
    private String phone;
}

