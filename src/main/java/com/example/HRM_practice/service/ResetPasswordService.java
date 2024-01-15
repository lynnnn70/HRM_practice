package com.example.HRM_practice.service;

import com.example.HRM_practice.model.dto.ResetPasswordDto;
import com.example.HRM_practice.model.entity.Users;

public interface ResetPasswordService {

    Users resetPassword(Integer userId, ResetPasswordDto resetPasswordDto);
}
