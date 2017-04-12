package com.zseng.car.service;

import com.zseng.car.common.Util;
import com.zseng.car.dao.HistoryRepository;
import com.zseng.car.entity.HistoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by cc on 2017/4/12.
 */
@Service
@Transactional
public class HistoryService {

    @Autowired
    HistoryRepository historyRepository;

    public void insertOne(Long userId, Long carId) {
        HistoryEntity history = new HistoryEntity();
        history.setCarId(carId);
        history.setCreateTime(Util.time());
        history.setUpdateTime(history.getCreateTime());

        historyRepository.save(history);
    }

}
