package com.example.HRM_practice.service;

import com.example.HRM_practice.exception.AccountUnavaliableException;
import com.example.HRM_practice.model.entity.Users;

public interface RegisterService {

    public Integer register(Users user) throws AccountUnavaliableException;
}
