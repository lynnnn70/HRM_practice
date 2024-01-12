package com.example.HRM_practice.service.serviceImpl;

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
    public Users checkPassword(Integer userId, Users user){
        Optional<Users> usersOptional = usersRepository.findById(userId);
        return usersOptional.orElse(null);
        //   if(!usersOptional.isPresent()){
        //            return null;
        //        }
        //        return usersOptional.get();

    }

    @Override
    public Users setNewPassword(Users user) {
        //這邊要new嗎?
        Users users = new Users();
        users.setPassword(user.getPassword());
        //其他兩個沒有set可以拿到原本的嗎??
        return users;
    }

}
