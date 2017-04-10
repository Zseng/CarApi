package com.zseng.car.service;

import com.zseng.car.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by cc on 2017/4/10.
 */
@Component
public class UserService {

    @Autowired
    UserDao userDao;

}
