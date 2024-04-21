package com.example.HRM_practice.service.serviceImpl;

import com.example.HRM_practice.model.entity.Users;
import com.example.HRM_practice.model.repository.UsersRepository;
import com.example.HRM_practice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users login(String username, String password) {
        Optional<Users> optionalUser = usersRepository.findByUserName(username);
        if (optionalUser.isPresent()) {
            return null;
        }

//        optionalUser.filter(users -> {
//            return BCrypt.checkpw(optionalUser.get().getPassword(), password)
//        }).map(JwtUtil)

        return null;
    }

    @Override
    public Optional<Users> getUserByName(String userName) {
        return Optional.empty();
    }

    @Override
    public Optional<String> login(Users users) {
        return Optional.empty();
    }


}
