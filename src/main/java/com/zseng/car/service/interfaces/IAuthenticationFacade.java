package com.zseng.car.service.interfaces;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by cc on 2017/4/12.
 */
public interface IAuthenticationFacade {

    boolean isAuthenticated();

    boolean isUserAuth();

    boolean isAnonymous();

    Authentication getAuthentication();

    Object getPrincipal();

}