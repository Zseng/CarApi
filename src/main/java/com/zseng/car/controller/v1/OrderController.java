package com.zseng.car.controller.v1;

import com.fasterxml.jackson.annotation.JsonView;
import com.zseng.car.common.DataResponse;
import com.zseng.car.common.OutputEntityJsonView;
import com.zseng.car.service.AuthenticationFacadeService;
import com.zseng.car.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cc on 2017/4/12.
 */
@Controller
@RestController("V1.OrderController")
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    AuthenticationFacadeService authenticationFacadeService;

    @RequestMapping("/create")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse createOrder(@RequestParam("car_id") Long carId,
                                    @RequestParam("begin_time") Long beginTime,
                                    @RequestParam("end_time") Long endTime,
                                    @RequestParam("info") String info) {

        return DataResponse.create().put("order", orderService.createOrder(authenticationFacadeService.getUserAuth().getUserId(), carId, info, beginTime, endTime));
    }

    @RequestMapping("/update-status")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse updateOrderStatus(@RequestParam("car_id") Long carId, @RequestParam("status") int status, @RequestParam("reply") String reply) {

        return DataResponse.create().put("order", orderService.updateOrderStatusWithReply(authenticationFacadeService.getUserAuth().getUserId(), carId, status, reply));
    }

    @RequestMapping("/list-create")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getOrderListCreateByUserId(@PageableDefault(sort = {"createTime", "updateTime"}, direction = Sort.Direction.DESC) Pageable pageable) {

        return DataResponse.create().putPage("order_list", orderService.getOrderListCreateByUserId(authenticationFacadeService.getUserAuth().getUserId(), pageable));
    }

    @RequestMapping("/list-receive")
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse getOrderListCarOwnByUserId(@PageableDefault(sort = {"createTime", "updateTime"}, direction = Sort.Direction.DESC) Pageable pageable) {

        return DataResponse.create().putPage("order_list", orderService.getOrderListCarOwnByUserId(authenticationFacadeService.getUserAuth().getUserId(), pageable));
    }



}
