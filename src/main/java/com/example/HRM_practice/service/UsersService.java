package com.example.HRM_practice.service;

import com.example.HRM_practice.model.entity.Users;

import java.util.Optional;

public interface UsersService {

    //新增使用者帳號與密碼

    //登入
    public Optional<String> login(Users users);

    //登出

    //listUserById
}
