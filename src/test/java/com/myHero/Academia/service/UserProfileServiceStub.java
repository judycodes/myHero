package com.myHero.Academia.service;

import com.myHero.Academia.model.User;
import com.myHero.Academia.model.UserProfile;

public class UserProfileServiceStub implements UserProfileService {

    @Override
    public UserProfile createUserProfile(UserProfile newProfile) {
        UserProfile userProfile = new UserProfile();

        userProfile.setSecondary_email("iAmBetterThanJean@Ruler.com");

        if(newProfile == null){
            return null;
        }


        return userProfile;
    }




    @Override
    public UserProfile getUserProfile() {
        return null;
    }

}
