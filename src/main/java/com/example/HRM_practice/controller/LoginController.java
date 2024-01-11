package com.example.HRM_practice.controller;

import com.example.HRM_practice.common.CommonResponse;
import com.example.HRM_practice.model.entity.Users;
import com.example.HRM_practice.service.UsersService;
import com.example.HRM_practice.util.ValidateUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@Tag(name = "login api", description = "API for user login")
public class LoginController {

    @Autowired
    private UsersService usersService;

    private  static final Logger log = LoggerFactory.getLogger(LoginController.class);

//    public ResponseEntity<CommonResponse<?>> login(@RequestBody Users users){
//        if(!isLoginDataCorrect(users)){
//            log.debug("Login data not correct:{}", users.getUserName());
//            return ResponseEntity.badRequest().body();
//        }
//        usersService.login
//    }

        private boolean isLoginDataCorrect(Users user){
            return ValidateUtil.isUserNameCorrect(user.getUserName()) &&
                    ValidateUtil.isPasswordCorrect(user.getPassword());
        }
}
