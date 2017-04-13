package com.zseng.car.service;

import com.zseng.car.common.PasswordHash;
import com.zseng.car.common.Util;
import com.zseng.car.repository.UserRepository;
import com.zseng.car.entity.UserEntity;
import com.zseng.car.exception.ExistsException;
import com.zseng.car.exception.InvalidParamsException;
import com.zseng.car.exception.LoginFailException;
import com.zseng.car.exception.NotExistsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by cc on 2017/4/10.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserEntity addUser(String username, String name, String phone, String password, String email) throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(phone) || StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
            throw new InvalidParamsException("params can not be blank");
        }
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity != null) {
            throw new ExistsException("username exists.");
        }
        userEntity = userRepository.findByPhone(phone);
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

        return userRepository.save(userEntity);
    }

    public UserEntity validateByUsername(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null || !PasswordHash.validatePassword(password, user.getPassword())) {
            throw new LoginFailException();
        }
        return user;
    }

    public UserEntity findByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new NotExistsException();
        }
        return user;
    }

    public UserEntity update(String username, String password, String phone) throws InvalidKeySpecException, NoSuchAlgorithmException {
        UserEntity userEntity = userRepository.findByUsername(username);
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
            UserEntity other = userRepository.findByPhone(phone);
            if (other != null) {
                throw new ExistsException("phone has been taken.");
            }
            userEntity.setPhone(phone);
        }
        if (hasChanged) {
            userEntity.setUpdateTime(Util.time());
            return userRepository.save(userEntity);
        } else {
            throw new InvalidParamsException();
        }
    }
}
