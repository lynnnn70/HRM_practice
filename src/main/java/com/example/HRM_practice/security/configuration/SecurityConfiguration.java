package com.example.HRM_practice.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(AbstractHttpConfigurer::disable)   //禁止CSRF(跨網站請求偽造) 保護
                .authorizeHttpRequests((authorize) -> authorize    //對所有訪問HTTP端點的HttpServletRequest進行限制
                .antMatchers("/admin/**").hasRole("ADMIN")   //設定只有ROLE_ADMIN權限能訪問的路徑
                .antMatchers("user/**").hasAnyRole("ADMIN", "USER")   //設定有ROLE_ADMIN or ROLE_USER權限能訪問的路徑
                .antMatchers("/", "home", "login").permitAll()   //所有用戶皆可訪問，不需進行身分驗證
                .anyRequest().authenticated()    //其他尚未匹配到的路徑都需要身份驗證
                );
        return httpSecurity.build();
    }
}
