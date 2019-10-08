package com.myHero.Academia.service;

import com.myHero.Academia.model.UserProfile;

public class UserProfileServiceStub implements UserProfileService {

    @Override
    public UserProfile createUserProfile(String username, UserProfile newProfile) {
        UserProfile userProfile = new UserProfile();

        userProfile.setSecondary_email("iAmBetterThanJean@Ruler.com");

        return userProfile;
    }

    @Override
    public UserProfile getUserProfile(String username) {
        return null;
    }

}
