package com.myHero.Academia.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.myHero.Academia.model.UserProfile;

import javax.persistence.*;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)

@Entity
@Table(name= "users")
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private UserProfile userProfile;

    //=== return userProfile ===//
    public UserProfile getUserProfile() {return userProfile;}

    //=== parameter userProfile ===//
    public void setUserProfile(UserProfile userProfile) {this.userProfile = userProfile;}

    //=== empty constructor ===//
    public User() {}

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
