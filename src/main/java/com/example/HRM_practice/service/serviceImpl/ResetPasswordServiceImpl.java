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
    public Users checkPassword(Integer userId, String oldPassword){
        Optional<Users> usersOptional = usersRepository.findById(userId);
        return usersOptional.orElse(null);
        //   if(!usersOptional.isPresent()){
        //            return null;
        //        }
        //        return usersOptional.get();

    }
    @Override
    public Users setNewPassword(Users user) {
        Optional<Users> usersOptional = usersRepository.findById(user.getUserId());
        usersOptional.ifPresent(existUser ->{
            existUser.setPassword(user.getPassword());
            usersRepository.save(existUser);
        });
           return usersOptional.orElse(null);

    }

}
