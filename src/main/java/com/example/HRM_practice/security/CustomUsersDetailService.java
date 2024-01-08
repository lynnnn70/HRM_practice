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

    //用於提供該用戶授權權限集合
    //@return 用戶被授權的權限集合
    //將分配給用戶的角色轉換為 GrantedAuthority 對象的集合
    public Collection<? extends GrantedAuthority> getAuthorities(Users users){
        //用Java流 將用戶的每個角色映射為一個 SimpleGrantedAuthority 對象，該對象實現了 GrantedAuthority 接口，結果收集到一個列表並返回
        return users.getRoles().stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getRoleName()))
                .collect(Collectors.toList());
    }


}
