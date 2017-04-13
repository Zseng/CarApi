package com.zseng.car.service;

import com.zseng.car.common.Util;
import com.zseng.car.entity.HistoryEntity;
import com.zseng.car.repository.CarRepository;
import com.zseng.car.entity.CarEntity;
import com.zseng.car.exception.InvalidParamsException;
import com.zseng.car.exception.NotExistsException;
import com.zseng.car.repository.HistoryRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by cc on 2017/4/10.
 */
@Service
@Transactional
public class CarService {

    @Autowired
    CarRepository carRepo;

    @Autowired
    HistoryRepository historyRepo;

    public CarEntity getCarDetail(Long id) {

        CarEntity car = carRepo.findById(id);
        if (car == null) {
            throw new NotExistsException();
        }
        car.setHot(car.getHot() + 1);

        return carRepo.save(car);
    }

    public Page<CarEntity> getCarList(Specification<CarEntity> spec, Pageable pageable) {

        return carRepo.findAll(spec, pageable);
    }

    public CarEntity saveCar(Long id, String name, String info, String brand, Integer city, Integer county, Integer district, Integer type, Double price, Integer priceType, String phone, String img, Double discount) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(info) || StringUtils.isBlank(brand) || StringUtils.isBlank(phone) || StringUtils.isBlank(img)) {
            throw new InvalidParamsException();
        }
        boolean isNew = false;
        CarEntity car = carRepo.findById(id);
        if (car == null) {
            car = new CarEntity();
            car.setCreateTime(Util.time());
            isNew = true;
        }
        car.setName(name);
        car.setBrand(brand);
        car.setInfo(info);
        car.setCity(city);
        car.setCounty(county);
        car.setDistrict(district);
        car.setType(type);
        car.setPrice(price);
        car.setPriceType(priceType);
        car.setPhone(phone);
        car.setImg(img);
        car.setDiscount(discount);
        car.setUpdateTime(isNew ? Util.time() : car.getCreateTime());

        return carRepo.save(car);
    }

    public Page<CarEntity> recommend(Long userId, Pageable pageable) {

        HistoryEntity history = historyRepo.findTop1ByUserId(userId);
        CarEntity car = carRepo.findById(history.getCarId());

        return carRepo.findAllByType(car.getType(), pageable);
    }
}
