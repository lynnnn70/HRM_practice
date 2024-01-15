package com.example.HRM_practice.service.serviceImpl;

import com.example.HRM_practice.common.StatusCode;
import com.example.HRM_practice.model.entity.Users;
import com.example.HRM_practice.model.repository.UsersRepository;
import com.example.HRM_practice.service.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users resetPassword(Integer userId, String keyOldPassword,
                               String newPassword){
        Optional<Users> usersOptional = usersRepository.findById(userId);
        if(!usersOptional.isPresent()){
            return null;
        }
        Users users = usersOptional.get();
        if(!users.getPassword().equals(keyOldPassword)){
            return null;
        }
        users.setPassword(newPassword);
        usersRepository.save(users);
        return users;
    }



}
