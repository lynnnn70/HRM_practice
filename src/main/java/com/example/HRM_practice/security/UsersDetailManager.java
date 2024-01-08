package com.example.HRM_practice.security;

import com.example.HRM_practice.model.entity.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersDetailManager extends UserDetailsService {

    //新增用戶
    void createUser(Users users);

    //修改用戶
    void updateUser(Users users);

    //刪除用戶
    void deleteUser(String userName);

    //修改密碼
    void changePassword(String oldPassword, String newPassword);

    //用戶是否存在
    boolean userExists(String userName);
}
