package com.zseng.car.repository;

import com.zseng.car.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by cc on 2017/4/10.
 */
@Component
public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {

    HistoryEntity findTop1ByUserId(long userId);

}
