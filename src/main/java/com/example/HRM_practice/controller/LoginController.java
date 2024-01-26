package com.example.HRM_practice.controller;

import com.example.HRM_practice.common.StatusCode;
import com.example.HRM_practice.response.UsersResponse;
import com.example.HRM_practice.model.entity.Users;
import com.example.HRM_practice.service.serviceImpl.LoginServiceImpl;
import com.example.HRM_practice.util.ValidateUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@Tag(name = " login api", description = "API for user login")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping("login")
    public UsersResponse login(@RequestBody Users user){
        log.debug("login data invalid, userName:{}", user.getUserName());
        if(!isLoginDataCorrect(user)){
            return new UsersResponse(StatusCode.NotFound);
        }
        loginService.login(user);
        return new UsersResponse(StatusCode.OK) ;
    }

    private boolean isLoginDataCorrect(Users user){
        return ValidateUtil.isUserNameCorrect(user.getUserName())&&
                ValidateUtil.isPasswordCorrect(user.getPassword());
    }


}
