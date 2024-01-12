package com.example.HRM_practice.service.serviceImpl;

import com.example.HRM_practice.exception.AccountUnavaliableException;
import com.example.HRM_practice.model.entity.Users;
import com.example.HRM_practice.model.repository.UsersRepository;
import com.example.HRM_practice.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Integer register(Users user) throws AccountUnavaliableException {
        Users newUser = new Users();
        String userName = user.getUserName();
        System.out.println(user.getPassword()+ "11111");
        if(isUserNameAvailable(userName)){
            throw new AccountUnavaliableException("username unavaliable");
        }
        newUser.setUserName(userName);
        newUser.setPassword(user.getPassword());
        return usersRepository.save(newUser).getUserId();
    }

    private boolean isUserNameAvailable(String userName){
        Optional<Users> existUser = usersRepository.findByUserName(userName);
        return existUser.isPresent();
    }
}
