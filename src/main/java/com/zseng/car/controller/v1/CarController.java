package com.zseng.car.controller.v1;

import com.fasterxml.jackson.annotation.JsonView;
import com.zseng.car.common.DataResponse;
import com.zseng.car.common.OutputEntityJsonView;
import com.zseng.car.entity.CarEntity;
import com.zseng.car.service.AuthenticationFacadeService;
import com.zseng.car.service.CarService;
import com.zseng.car.service.HistoryService;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cc on 2017/4/10.
 */
@RestController("V1.CarController")
@RequestMapping("/api/v1/car")
@Controller
public class CarController {

    private static Logger logger = org.slf4j.LoggerFactory.getLogger(CarController.class);

    @Autowired
    CarService carService;
    @Autowired
    HistoryService historyService;

    @Autowired
    AuthenticationFacadeService authenticationFacadeService;

    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse recommend(
            @And({//这些参数可以由前端统计匿名用户的历史记录
                    @Spec(path = "brand", spec = Like.class),
                    @Spec(path = "city", spec = Equal.class),
                    @Spec(path = "county", spec = Equal.class),
                    @Spec(path = "district", spec = Equal.class),
                    @Spec(path = "type", spec = Equal.class),
                    @Spec(path = "discount", spec = GreaterThanOrEqual.class),
                    @Spec(path = "price_type", spec = Equal.class),
                    @Spec(path = "price", params = "price_less_than", spec = LessThanOrEqual.class),
                    @Spec(path = "price", params = "price_greater_than", spec = GreaterThanOrEqual.class)
            }) Specification<CarEntity> spec,
            @PageableDefault(size = 12, sort = {"createTime", "updateTime"}, direction = Sort.Direction.DESC) Pageable pageable) {

        //用户已经登录的话就返回根据历史记录的参数权重算出来的数据
        if (authenticationFacadeService.isUserAuth()) {
            return DataResponse.create().putPage("car_list", carService.recommend(authenticationFacadeService.getUserAuth().getUserId(), pageable));
        } else {//否则按照热门返回或者根据传来的数据筛选返回
            return DataResponse.create().putPage("car_list", carService.getCarList(spec, pageable));
        }

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse carList(
            @And({
                    @Spec(path = "name", spec = Like.class),
                    @Spec(path = "brand", spec = Like.class),
                    @Spec(path = "city", spec = Equal.class),
                    @Spec(path = "county", spec = Equal.class),
                    @Spec(path = "district", spec = Equal.class),
                    @Spec(path = "type", spec = Equal.class),
                    @Spec(path = "discount", spec = GreaterThanOrEqual.class),
                    @Spec(path = "price_type", spec = Equal.class),
                    @Spec(path = "price", params = "price_less_than", spec = LessThanOrEqual.class),
                    @Spec(path = "price", params = "price_greater_than", spec = GreaterThanOrEqual.class)
            }) Specification<CarEntity> spec,
            @PageableDefault(size = 12, sort = {"createTime", "updateTime"}, direction = Sort.Direction.DESC) Pageable pageable) {

        return DataResponse.create().putPage("car_list", carService.getCarList(spec, pageable));
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse carDetail(@RequestParam("id") Long id) {

        DataResponse dataResponse = DataResponse.create().put("car", carService.getCarDetail(id));
        if (authenticationFacadeService.isUserAuth()) {
            //如果用户已经登录 还需要更新该用户最近的浏览记录
            historyService.insertOne(authenticationFacadeService.getUserAuth().getUserId(), id);
        }
        return dataResponse;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse saveCar(@RequestParam(value = "id", required = false, defaultValue = "0") Long id,
                                @RequestParam("name") String name,
                                @RequestParam("info") String info,
                                @RequestParam("brand") String brand,
                                @RequestParam("city") Integer city,
                                @RequestParam("county") Integer county,
                                @RequestParam("district") Integer district,
                                @RequestParam("type") Integer type,
                                @RequestParam("price_type") Integer priceType,
                                @RequestParam("price") Double price,
                                @RequestParam("phone") String phone,
                                @RequestParam("img") String img,//逗号隔开的图片名称字符串eg: "a.png,b.png"
                                @RequestParam(value = "discount", required = false, defaultValue = "0.00") Double discount) {


        return DataResponse.create().put("car", carService.saveCar(id, name, info, brand, city, county, district, type, price, priceType, phone, img, discount));
    }


}
