package com.zseng.car.dao;

import com.zseng.car.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cc on 2017/4/10.
 */
@Repository
public interface CarDao extends JpaRepository<CarEntity, Long> {
}
