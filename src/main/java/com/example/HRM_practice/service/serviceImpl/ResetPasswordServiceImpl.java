package com.example.HRM_practice.service.serviceImpl;

import com.example.HRM_practice.model.dto.ResetPasswordDto;
import com.example.HRM_practice.model.entity.Users;
import com.example.HRM_practice.model.repository.UsersRepository;
import com.example.HRM_practice.service.ResetPasswordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {

    @Autowired
    private UsersRepository usersRepository;

//    @Override
//    public Users resetPassword(Integer userId, String keyOldPassword,
//                               String newPassword){
//        Optional<Users> usersOptional = usersRepository.findById(userId);
//        if(!usersOptional.isPresent()){
//            return null;
//        }
//        Users user = usersOptional.get();
//        if(!user.getPassword().equals(keyOldPassword)){
//            return null;
//        }
//        user.setPassword(newPassword);
//        usersRepository.save(user);
//        return user;
//    }

    @Override
    public Users resetPassword(Integer userId, ResetPasswordDto resetPasswordDto){
        Optional<Users> usersOptional = usersRepository.findById(userId);
        if(!usersOptional.isPresent()){
            return null;
        }
        Users user = usersOptional.get();
        if(!resetPasswordDto.getOldPassword().equals(user.getPassword())){
            return null;
        }
        //如果一樣就set再save
        user.setPassword(resetPasswordDto.getNewPasswordCheck());

        usersRepository.save(user);
        return user;
    }
    //Todo 確認需不需要
    private Users convert2Users(ResetPasswordDto resetPasswordDto){
        Users user = new Users();
        BeanUtils.copyProperties(resetPasswordDto, user);
        user.setPassword(resetPasswordDto.getNewPasswordCheck());
        return user;

    }



}
