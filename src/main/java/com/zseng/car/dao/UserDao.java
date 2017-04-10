package com.zseng.car.dao;

import com.zseng.car.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cc on 2017/4/10.
 */
@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    UserEntity findByPhone(String phone);

}
