package com.myHero.Academia.controller;

import com.myHero.Academia.model.User;
import com.myHero.Academia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/list")
    public Iterable<User> listUsers() {
        return userService. listUsers();
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!!";
    }

    @PostMapping("/signup")
    public User createUser(@RequestBody User newUser) { return userService.createUser(newUser); }

    @GetMapping("/login/{username}/{password}")
    public User login(@PathVariable String username, @PathVariable String password){
        return userService.login(username, password);
    }






}
