package com.example.HRM_practice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    //首頁 所有用戶皆可拜訪
    @GetMapping("/")
    public String home(){
        return "home";
    }

    //登入頁面
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    //使用者可訪問的頁面

    //管理者可訪問的頁面
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    //禁止訪問的頁面
    @GetMapping("/access-denied")
    public String accessDenied(){
        return "accessDenied";
    }


}
