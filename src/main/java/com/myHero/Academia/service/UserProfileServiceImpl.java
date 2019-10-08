package com.myHero.Academia.service;

import com.myHero.Academia.controller.SecurityController;
import com.myHero.Academia.model.User;
import com.myHero.Academia.model.UserProfile;
import com.myHero.Academia.repository.UserRepository;
import com.myHero.Academia.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService{

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserService userService;

    @Autowired
    SecurityController securityController;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileService userProfileService;


    //=== parameter username & parameter newProfile & return newProfile===//
    @Override
    public UserProfile createUserProfile(UserProfile newProfile) {
        String username = securityController.getCurrentUserName();
        User user = userService.getUser(username);
        newProfile.setUser(user);
        user.setUserProfile(newProfile);
        return userProfileRepository.save(newProfile);
    }

    //=== parameter username & return//
    @Override
    public UserProfile getUserProfile() {
        String username = securityController.getCurrentUserName();
        return userProfileRepository.findProfileByUsername(username);

    }

}
