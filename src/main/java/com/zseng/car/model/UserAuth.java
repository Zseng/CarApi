package com.zseng.car.model;

import com.zseng.car.entity.UserEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

/**
 * Created by cc on 2017/4/10.
 */
public class UserAuth  extends User {

    private UserEntity userEntity;

    public UserAuth(UserEntity userEntity) {
        super(userEntity.getUsername(), userEntity.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.userEntity = userEntity;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    public Long getUserId() {
        return userEntity.getId();
    }
}
