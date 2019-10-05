package com.myHero.Academia.service;

import com.myHero.Academia.config.JwtUtil;
import com.myHero.Academia.model.User;
import com.myHero.Academia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl  implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override

    public Iterable<User> listUsers() {return userRepository.findAll();}

    @Autowired
    @Qualifier("encoder")
    PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(username);

        if(user==null)
            throw new UsernameNotFoundException("User null");

        return new org.springframework.security.core.userdetails.User(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()),
                true, true, true, true, getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<>();

//        authorities.add(new SimpleGrantedAuthority(user.getName()));

        return authorities;
    }



    @Override
    public String createUser(User newUser) {

        if(userRepository.save(newUser) != null){
            UserDetails userDetails = loadUserByUsername(newUser.getUsername());
            return jwtUtil.generateToken(userDetails);
        }
        return null;
    }

    @Override
    public String login(User user){
        if(userRepository.login(user.getUsername(), user.getPassword()) != null){
            UserDetails userDetails = loadUserByUsername(user.getUsername());
            return jwtUtil.generateToken(userDetails);
        }
        return null;
    }


    @Override
    public User getUser(String username){
        return userRepository.findByUsername(username);
    }

}

