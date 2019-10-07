package com.myHero.Academia.service;

import com.myHero.Academia.model.Post;
import com.myHero.Academia.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    //=== return all users ===//
    public Iterable<User> listUsers();

    //===parameter newUser & return token ===//
    public String createUser (User newUser);

    //===parameter user & return token ===//
    public String login(User user);

    //=== parameter username & return User ===//
    public User getUser(String username);

}
