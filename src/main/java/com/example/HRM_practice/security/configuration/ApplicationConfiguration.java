package com.example.HRM_practice.security.configuration;

import com.example.HRM_practice.model.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfiguration {

    @Autowired
    private final UsersRepository usersRepository;

    public ApplicationConfiguration(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    //用於查找用戶詳細訊息
    @Bean
    public UserDetailsService userDetailService(){
        return username -> (UserDetails) usersRepository.findByUserName(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with username" + username));
    }

    //執行身分驗證的關鍵組件
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    //將用戶密碼進行安全的雜湊存儲
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
