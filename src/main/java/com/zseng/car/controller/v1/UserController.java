package com.zseng.car.controller.v1;

import com.fasterxml.jackson.annotation.JsonView;
import com.zseng.car.common.DataResponse;
import com.zseng.car.common.OutputEntityJsonView;
import com.zseng.car.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by cc on 2017/4/10.
 */
@RestController("UserController")
@RequestMapping("/api/v1/user")
@Component
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse register(@RequestParam("username") String username,
                                 @RequestParam("name") String name,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("password") String password,
                                 @RequestParam(value = "email", required = false, defaultValue = "") String email) throws InvalidKeySpecException, NoSuchAlgorithmException {

        return DataResponse.create().put("user", userService.addUser(username, name, phone, password, email));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse login(@RequestParam("username") String username, @RequestParam("password") String password) throws InvalidKeySpecException, NoSuchAlgorithmException {

        return DataResponse.create().put("user", userService.findByUsername(username));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @JsonView(OutputEntityJsonView.Detail.class)
    public DataResponse update(@RequestParam("username") String username,
                               @RequestParam(value = "password", required = false, defaultValue = "") String password,
                               @RequestParam(value = "phone", required = false, defaultValue = "") String phone) throws InvalidKeySpecException, NoSuchAlgorithmException {

        return DataResponse.create().put("user", userService.update(username, password, phone));
    }

}
