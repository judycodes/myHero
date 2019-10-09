package com.myHero.Academia.controller;

import com.myHero.Academia.model.UserProfile;
import com.myHero.Academia.repository.UserRepository;
import com.myHero.Academia.service.UserProfileServiceStub;
import com.myHero.Academia.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.bind.annotation.GetMapping;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileControllerTest {

    private UserProfileController userProfileController;


    @Before
    public void initializeUserProfileController() {
         userProfileController = new UserProfileController();
         userProfileController.setUserProfileService(new UserProfileServiceStub());
    }

    @Test
    public void createUserProfile_SaveUserProfile_Success() throws Exception{
        UserProfile userProfile = new UserProfile();
        userProfile.setSecondary_email("iAmBetterThanJean@Ruler.com"); // this is meant to work
//        userProfile.setSecondary_email(null); // this is not mean to work

        //UserProfile newProfile = userProfileController.createUserProfile("Jean", userProfile);
        UserProfile newProfile = userProfileController.createUserProfile(userProfile);

        Assert.assertNotNull(newProfile);
        Assert.assertEquals(newProfile.getSecondary_email(), userProfile.getSecondary_email());
    }



}
