//package com.example.HRM_practice.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//
//@Configuration
//public class SecurityConfiguration {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
//        http.authorizeRequests(auths -> auths
//                .antMatchers("/admin/**").hasRole("ADMIN")                   //設定只有ROLE_ADMIN權限能訪問的路徑
//                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")  //設定有ROLE_ADMIN or ROLE_USER權限能訪問的路徑
//                .antMatchers("/", "/home", "/login").permitAll())       //設定所有人可訪問的路徑
//            .formLogin(form -> form                                           //表單提交
//                .loginPage("/login")                                          //設定login路徑
//                .permitAll())
//            .logout(logout -> logout
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/")                                        //在這裡設置登出後的重導向頁面
//                .permitAll())                                                 //設定logout路徑
//            .exceptionHandling(exception -> exception
//                .accessDeniedHandler(accessDeniedHandler()));                 //設定無權限時的顯示頁面
//        return http.build();
//    }
//
//    //設定 無權限 時對應controller的路徑
//    @Bean
//    public AccessDeniedHandler accessDeniedHandler() {
//        return ((request, response, accessDeniedException) -> {
//            response.sendRedirect("/access-denied");
//        });
//    }
//    //設定編碼器，目前密碼無加密
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        //這個編碼器支持多種編碼方式。包括明文(添加{noop}前綴)
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//
//
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
////        http.csrf(AbstractHttpConfigurer::disable)   //禁止CSRF(跨網站請求偽造) 保護
////                .authorizeHttpRequests((authorize) -> authorize    //對所有訪問HTTP端點的HttpServletRequest進行限制
////                .antMatchers("/admin/**").hasRole("ADMIN")   //設定只有ROLE_ADMIN權限能訪問的路徑
////                .antMatchers("user/**").hasAnyRole("ADMIN", "USER")   //設定有ROLE_ADMIN or ROLE_USER權限能訪問的路徑
////                .antMatchers("/", "home", "login").permitAll()   //所有用戶皆可訪問，不需進行身分驗證
////                .anyRequest().authenticated()    //其他尚未匹配到的路徑都需要身份驗證
////                );
////        return httpSecurity.build();
////    }
//    //建立密碼演算的實例
//    @Bean
//    public PasswordEncoder getPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//}
