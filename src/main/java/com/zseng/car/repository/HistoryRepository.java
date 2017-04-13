package com.zseng.car.repository;

import com.zseng.car.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by cc on 2017/4/10.
 */
@Component
public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {
    
}
