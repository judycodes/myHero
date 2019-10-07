package com.myHero.Academia.controller;

import com.myHero.Academia.model.User;
import com.myHero.Academia.model.JwtResponse;
import com.myHero.Academia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    //=== return userService.listUsers() ===//
    @GetMapping("/listUsers")
    public Iterable<User> listUsers() {
        return userService.listUsers();
    }


    //=== return "Hello World") ===//
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!!";
    }


    //=== parameter  newUser & return user token ===//
    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        return ResponseEntity.ok(new JwtResponse(userService.createUser(newUser)));
    }


    //=== parameter user & return user token ===//
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return ResponseEntity.ok(new JwtResponse(userService.login(user)));
    }

}
