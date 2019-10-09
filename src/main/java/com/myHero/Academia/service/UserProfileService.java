package com.myHero.Academia.service;

import com.myHero.Academia.model.UserProfile;

public interface UserProfileService {

    //=== parameter username &  newProfile===//
    UserProfile createUserProfile(UserProfile newProfile);

    //=== parameter username & return username
    UserProfile getUserProfile();
}
