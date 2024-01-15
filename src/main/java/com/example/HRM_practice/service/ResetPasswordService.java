package com.example.HRM_practice.service;

import com.example.HRM_practice.model.entity.Users;

public interface ResetPasswordService {

    Users resetPassword(Integer userId, String keyOldPassword,String newPassword);
}
