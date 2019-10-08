package com.myHero.Academia.config;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {

        Authentication getAuthentication();

}
