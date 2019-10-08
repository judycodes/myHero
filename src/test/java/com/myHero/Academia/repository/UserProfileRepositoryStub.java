package com.myHero.Academia.repository;

import com.myHero.Academia.model.User;
import com.myHero.Academia.model.UserProfile;

import java.util.Optional;

public class UserProfileRepositoryStub implements UserProfileRepository {

    @Override
    public <S extends UserProfile> S save(S entity) {
        UserProfile userProfile = new UserProfile();
        userProfile.setSecondary_email("iAmBetterThanJean@Ruler.com");

        return (S)userProfile;
    }

    @Override
    public <S extends UserProfile> Iterable<S> saveAll(Iterable<S> varl) {
        return null;
    }

    @Override
    public Optional<UserProfile> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long along) {
        return false;
    }

    @Override
    public Iterable<UserProfile> findAll() {
        return null;
    }

    @Override
    public Iterable<UserProfile> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long along) {
    }

    @Override
    public void delete(UserProfile entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserProfile> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public UserProfile findProfileByUsername(String username) {
        return null;
    }


}




