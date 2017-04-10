package com.zseng.car.service;

import com.zseng.car.dao.UserDao;
import com.zseng.car.entity.UserEntity;
import com.zseng.car.model.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by cc on 2017/4/10.
 */
@Component
public class UserAuthService  implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("username not found");
        }

        return new UserAuth(user);
    }

}
