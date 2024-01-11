package com.example.HRM_practice.web;

import com.example.HRM_practice.model.entity.Users;
import com.example.HRM_practice.security.CustomUsersDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private CustomUsersDetailService customUsersDetailService;
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    //首頁 所有用戶皆可拜訪
    @GetMapping("/")
    public String home(){
        return "commons/home";
    }

    //登入頁面
    @GetMapping("/login")
    public String login(){
        return "commons/login";
    }

    //使用者可訪問的頁面
    @GetMapping("/user")
    public String user(Principal principal){
        //以下是用來觀察登入後 spring security 紀錄在 session 的登入資訊
        UsernamePasswordAuthenticationToken user = (UsernamePasswordAuthenticationToken) principal;
        log.info("登入資訊 :{}", user);
        log.info("登入資訊name :{}", user.getName());
        log.info("登入資訊credentials :{}", user.getCredentials()); //獲取用戶輸入的密碼字符串
        log.info("登入資訊principal :{}", user.getPrincipal());
        Users userPrincipal = (Users) user.getPrincipal();    //獲取代表當前用戶的資訊
        log.info("UserPrincipal 授權的ROLE :{}", userPrincipal.getAuthorities()); //獲取授權資料
        log.info("UserPrincipal 帳號 :{}", userPrincipal.getUserName());
        log.info("UserPrincipal 密碼 :{}", userPrincipal.getPassword());

        return "commons/user";
    }

    //管理者可訪問的頁面
    @GetMapping("/admin")
    public String admin(){
        return "commons/admin";
    }

    //禁止訪問的頁面
    @GetMapping("/access-denied")
    public String accessDenied(){
        return "commons/accessDenied";
    }



}
