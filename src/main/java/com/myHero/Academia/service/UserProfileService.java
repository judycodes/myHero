package com.myHero.Academia.service;
import com.myHero.Academia.model.UserProfile;

import java.security.PublicKey;


public interface UserProfileService {
    UserProfile createUserProfile(String username, UserProfile newProfile);
    UserProfile getUserProfile(String username);
}
