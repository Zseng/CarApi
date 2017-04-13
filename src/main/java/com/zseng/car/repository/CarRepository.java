package com.zseng.car.repository;

import com.zseng.car.entity.CarEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cc on 2017/4/10.
 */
@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long>, JpaSpecificationExecutor<CarEntity> {

    CarEntity findById(Long id);

    Page<CarEntity> findAllByType(int type, Pageable pageable);

}
