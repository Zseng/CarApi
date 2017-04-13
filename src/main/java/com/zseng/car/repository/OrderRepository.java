package com.zseng.car.repository;

import com.zseng.car.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cc on 2017/4/12.
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {

    List<OrderEntity> findByCarIdAndBeginTimeLessThanEqualAndEndTimeGreaterThanEqualAndStatus(Long carId, Long beginTime, Long endTime, int status);

    OrderEntity findByIdAndStatus(long id, int status);

    Page<OrderEntity> findByUserId(Long userId, Pageable pageable);

    Page<OrderEntity> findByCarOwnerId(Long carOwnerId, Pageable pageable);

}
