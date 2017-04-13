package com.zseng.car.service;

import com.zseng.car.common.Util;
import com.zseng.car.entity.CarEntity;
import com.zseng.car.entity.OrderEntity;
import com.zseng.car.exception.NotAllowedException;
import com.zseng.car.exception.NotExistsException;
import com.zseng.car.repository.CarRepository;
import com.zseng.car.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by cc on 2017/4/12.
 */
@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepo;

    @Autowired
    CarRepository carRepo;

    public OrderEntity createOrder(Long userId, Long carId, String info, Long beginTime, Long endTime) {
        if (orderRepo.findByCarIdAndBeginTimeLessThanEqualAndEndTimeGreaterThanEqualAndStatus(carId, beginTime, beginTime, OrderEntity.ORDER_STATUS_INIT).size() > 0) {
            throw new NotAllowedException();
        }
        CarEntity car = carRepo.findById(carId);
        if (car == null) {
            throw new NotExistsException();
        }
        OrderEntity order = new OrderEntity();
        order.setUserId(userId);
        order.setInfo(info);
        order.setCarId(car.getId());
        order.setCarOwnerId(car.getOwnerId());
        order.setBeginTime(beginTime);
        order.setEndTime(endTime);
        order.setCreateTime(Util.time());
        order.setUpdateTime(order.getCreateTime());

        return orderRepo.save(order);
    }

    public OrderEntity updateOrderStatusWithReply(long userId, long carId, int status, String reply) {
        OrderEntity order = orderRepo.findOne(carId);
        if (order == null) {
            throw new NotExistsException();
        }
        if (order.getStatus() != OrderEntity.ORDER_STATUS_INIT) {
            throw new NotAllowedException("order status is already updated!");
        }
        if (order.getUserId() != userId && order.getCarOwnerId() != userId) {
            throw new NotAllowedException();
        }
        order.setStatus(status);
        order.setReply(reply);
        order.setUpdateTime(Util.time());

        return orderRepo.save(order);
    }

    public Page<OrderEntity> getOrderListCreateByUserId(Long userId, Pageable pageable) {

        return orderRepo.findByUserId(userId, pageable);
    }

    public Page<OrderEntity> getOrderListCarOwnByUserId(Long userId, Pageable pageable) {

        return orderRepo.findByCarOwnerId(userId, pageable);
    }

}
