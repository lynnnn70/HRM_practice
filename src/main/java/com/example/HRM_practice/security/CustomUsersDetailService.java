package com.example.HRM_practice.security;

import com.example.HRM_practice.model.entity.Users;
import com.example.HRM_practice.model.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

//UserDetailsService 定義了獲取用戶詳細訊息的唯一一個方法，通過用戶名獲取用戶資訊
@Service
public class CustomUsersDetailService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByUserName(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found"));

        //使用的密碼帶有{noop}前綴，表示他是名文
        return new org.springframework.security.core.userdetails.User(users.getUserName(),
                "{noop}" + users.getPassword(), getAuthorities(users));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Users user) {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());

    }

}
