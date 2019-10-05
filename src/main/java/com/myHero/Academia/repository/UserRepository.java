package com.myHero.Academia.repository;

import com.myHero.Academia.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
    @Query("FROM User u WHERE u.username = ?1 AND u.password = ?2")

    public User login(String username, String password);
    public User findByUsername(String username);


    // ===remember to implement log in with email ===\\
//
//    @Query("From User u Where u.email =?1 And u.password = ?2")
//    public User loginEmail(String email, String password);
//
//
//    public User findByEmail(String email);
}
