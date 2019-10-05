package com.myHero.Academia.service;

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
    UserRepository userRepository;

    @Override
    public UserProfile createUserProfile(String username, UserProfile newProfile) {
        User user = userService.getUser(username);
        newProfile.setUser(user);
        user.setUserProfile(newProfile);
        return userProfileRepository.save(newProfile);
    }

    @Override
    public UserProfile getUserProfile(String username) {
        return userProfileRepository.findProfileByUsername(username);

    }

}
