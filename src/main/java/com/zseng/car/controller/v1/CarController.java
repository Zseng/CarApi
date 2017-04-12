package com.zseng.car.controller.v1;

import com.fasterxml.jackson.annotation.JsonView;
import com.zseng.car.common.DataResponse;
import com.zseng.car.common.OutputEntityJsonView;
import com.zseng.car.service.AuthenticationFacadeService;
import com.zseng.car.service.CarService;
import com.zseng.car.service.HistoryService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cc on 2017/4/10.
 */
@RestController("CarController")
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

    //TODO 这里逻辑会比较复杂，设计到各个参数的权重
    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse recommend() {

        if (authenticationFacadeService.isUserAuth()) {
            logger.info("userAuth: " + authenticationFacadeService.getUserAuth());
        } else {
            logger.info("not user auth");
        }

        return DataResponse.create();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @JsonView(OutputEntityJsonView.Basic.class)
    public DataResponse carList(@PageableDefault(page = 0, size = 12, sort = {"createTime", "updateTime"}, direction = Sort.Direction.DESC) Pageable pageable) {

        return DataResponse.create().putPage("car_list", carService.getCarList(pageable));
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
                                @RequestParam("img") String img,
                                @RequestParam(value = "discount", required = false, defaultValue = "0.00") Double discount) {


        return DataResponse.create().put("car", carService.saveCar(id, name, info, brand, city, county, district, type, price, priceType, phone, img, discount));
    }


}
