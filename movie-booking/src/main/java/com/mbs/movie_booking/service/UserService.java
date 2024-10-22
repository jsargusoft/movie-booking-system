package com.mbs.movie_booking.service;

import java.util.List;

import com.mbs.movie_booking.dto.UserRegisterInfo;
import com.mbs.movie_booking.models.User;

public interface UserService {

    void registerUser(UserRegisterInfo userRegisterInfo);

    public User getCurrentlyLoggedInUser();

    List<User> findAll();
}
