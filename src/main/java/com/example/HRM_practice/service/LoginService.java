package com.example.HRM_practice.service;

import com.example.HRM_practice.model.entity.Users;

import java.util.Optional;

public interface LoginService {

    Users login(String username, String password);

    Optional<Users> getUserByName(String userName);

    Optional<String> login(Users users);

}
