package com.myHero.Academia.controller;

import com.myHero.Academia.model.User;
import com.myHero.Academia.model.JwtResponse;
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
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        return ResponseEntity.ok(new JwtResponse(userService.createUser(newUser)));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return ResponseEntity.ok(new JwtResponse(userService.login(user)));
    }






}
