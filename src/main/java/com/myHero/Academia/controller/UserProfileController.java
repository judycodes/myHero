package com.myHero.Academia.controller;

import com.myHero.Academia.model.UserProfile;
import com.myHero.Academia.repository.UserProfileRepository;
import com.myHero.Academia.service.UserProfileService;
import com.myHero.Academia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    UserProfileService userProfileService;

    //===testing purposes===//
    @Autowired
    public void setUserProfileService(UserProfileService userProfileService){
        this.userProfileService = userProfileService;
    }

    //=== parameter username & parameter newProfile & return===//
    @PostMapping
    //@PutMapping
    public UserProfile createUserProfile(@RequestBody UserProfile userProfile) {
        return userProfileService.createUserProfile(userProfile);
    }

    //=== parameter username & return===//
    @GetMapping
    public UserProfile getUserProfile() {
        return userProfileService.getUserProfile();
    }

}
