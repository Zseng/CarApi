package com.zseng.car.service;

import com.zseng.car.common.PasswordHash;
import com.zseng.car.common.Util;
import com.zseng.car.dao.UserDao;
import com.zseng.car.entity.UserEntity;
import com.zseng.car.exception.ExistsException;
import com.zseng.car.exception.InvalidParamsException;
import com.zseng.car.exception.LoginFailException;
import com.zseng.car.exception.NotExistsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * Created by cc on 2017/4/10.
 */
@Component
public class UserService {

    @Autowired
    UserDao userDao;

    public UserEntity addUser(String username, String name, String phone, String password, String email) throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(phone) || StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
            throw new InvalidParamsException("params can not be blank");
        }
        UserEntity userEntity = userDao.findByUsername(username);
        if (userEntity != null) {
            throw new ExistsException("username exists.");
        }
        userEntity = userDao.findByPhone(phone);
        if (userEntity != null) {
            throw new ExistsException("phone has been taken.");
        }
        userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setPhone(phone);
        userEntity.setUsername(username);
        userEntity.setEmail(email);
        userEntity.setPassword(PasswordHash.createHash(password));
        userEntity.setCreateTime(Util.time());
        userEntity.setUpdateTime(userEntity.getCreateTime());

        return userDao.save(userEntity);
    }

    public UserEntity validateByUsername(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        UserEntity user = userDao.findByUsername(username);
        if (user == null || !PasswordHash.validatePassword(password, user.getPassword())) {
            throw new LoginFailException();
        }
        return user;
    }

    public UserEntity findByUsername(String username) {
        UserEntity user = userDao.findByUsername(username);
        if (user == null) {
            throw new NotExistsException();
        }
        return user;
    }

    public UserEntity update(String username, String password, String phone) throws InvalidKeySpecException, NoSuchAlgorithmException {
        UserEntity userEntity = userDao.findByUsername(username);
        if (userEntity == null) {
            throw new NotExistsException();
        }
        boolean hasChanged = false;
        if (StringUtils.isNotBlank(password)) {
            hasChanged = true;
            userEntity.setPassword(PasswordHash.createHash(password));
        }
        if (StringUtils.isNotBlank(phone)) {
            hasChanged = true;
            UserEntity other = userDao.findByPhone(phone);
            if (other != null) {
                throw new ExistsException("phone has been taken.");
            }
            userEntity.setPhone(phone);
        }
        if (hasChanged) {
            userEntity.setUpdateTime(Util.time());
            return userDao.save(userEntity);
        } else {
            throw new InvalidParamsException();
        }
    }
}
