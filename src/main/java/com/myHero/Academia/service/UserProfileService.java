package com.myHero.Academia.service;
import com.myHero.Academia.model.UserProfile;

import java.security.PublicKey;

public interface UserProfileService {

    //=== parameter username &  newProfile===//
    UserProfile createUserProfile(UserProfile newProfile);

    //=== parameter username & return username
    UserProfile getUserProfile();
}
