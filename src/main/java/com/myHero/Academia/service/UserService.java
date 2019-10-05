package com.myHero.Academia.service;

import com.myHero.Academia.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;



public interface UserService extends UserDetailsService {
    public Iterable<User> listUsers();
    public String createUser (User newUser);
    public String login(User user);
    public User getUser(String username);
}
