package com.myHero.Academia.repository;

import com.myHero.Academia.model.UserProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {
    @Query("FROM UserProfile up INNER JOIN User u ON u.username = ?1 AND up.id = u.userProfile.id")

    public UserProfile findProfileByUsername(String username);
}
