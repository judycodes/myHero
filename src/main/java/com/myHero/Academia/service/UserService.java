package com.myHero.Academia.service;

import com.myHero.Academia.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;



public interface UserService extends UserDetailsService {

    //===return list of users===//
    public Iterable<User> listUsers();

    //===parameter newUser & return===//
    public String createUser (User newUser);

    //===parameter user & return ===//
    public String login(User user);

    //=== parameter username * return===//
    public User getUser(String username);
}
