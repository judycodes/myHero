package com.myHero.Academia.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "users")
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    @JsonBackReference
    private UserProfile userProfile;

    //=== empty constructor ===//
    public User() {}

    //=== return userProfile ===//
    public UserProfile getUserProfile() {return userProfile;}

    //=== parameter userProfile ===//
    public void setUserProfile(UserProfile userProfile) {this.userProfile = userProfile;}

    //=== return Id ===//
    public Long getId() {return id;}

    //=== parameter Id ===//
    public void setId(Long id) {this.id = id;}

    //=== return Username ===//
    public String getUsername() {return username;}

    //=== parameter Username ===//
    public void setUsername(String username) {this.username = username;}

    //=== return Password ===//
    public String getPassword() {return password;}

    // parameter Password ===//
    public void setPassword(String password) {this.password = password;}

    //=== parameter Email ===//
    public String getEmail() {return email; }

    //=== parameter Email===//
    public void setEmail(String email) {this.email = email;}



}
