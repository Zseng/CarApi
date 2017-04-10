package com.zseng.car.dao;

import com.zseng.car.entity.LatestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by cc on 2017/4/10.
 */
@Component
public interface LatestDao extends JpaRepository<LatestEntity, Long> {
    
}
