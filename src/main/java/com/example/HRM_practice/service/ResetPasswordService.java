package com.example.HRM_practice.service;

import com.example.HRM_practice.model.entity.Users;

public interface ResetPasswordService {

    public Users checkPassword(Integer userId, Users user);

    public Users setNewPassword(Users user);
}
