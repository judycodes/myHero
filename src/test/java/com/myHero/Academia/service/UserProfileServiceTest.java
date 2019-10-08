package com.myHero.Academia.service;

import com.myHero.Academia.controller.UserProfileController;
import com.myHero.Academia.model.User;
import com.myHero.Academia.model.UserProfile;
import com.myHero.Academia.repository.UserProfileRepositoryStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

public class UserProfileServiceTest {
    private UserProfileController userProfileController;
    @Before
    public void initializeUserProfile(){
        userProfileController = new UserProfileController();
        userProfileController.setUserProfileService( new UserProfileServiceStub());



    }

    @Test
    public void createUserProfile_AddsProfile_Success(){
        UserProfile userProfile = new UserProfile();
        userProfile.setSecondary_email("iAmBetterThanJean@Ruler.com");

//        UserProfile newProfile = userProfileController.createUserProfile(userProfile); // meant to pass

        UserProfile newProfile = userProfileController.createUserProfile(null); //meant to fail

        System.out.println(newProfile);
        Assert.assertNotNull(newProfile);
        Assert.assertEquals(newProfile.getSecondary_email(), userProfile.getSecondary_email());

    }


}
