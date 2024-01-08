package com.example.HRM_practice.security;

import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.util.Assert;


public class CachingUsersDetailsService implements UserDetailsService {

    //用戶緩存
    private UserCache userCache = new NullUserCache();
    //獲取用戶詳情 服務接口
    //以免從緩存中沒有獲取到用戶資訊
    //可通過提供的其他方式獲取用戶資訊
    private final UserDetailsService delegate;

    //建構子 初始化獲取用戶資訊的其他服務
    public CachingUsersDetailsService(UserDetailsService delegate) {
        this.delegate = delegate;
    }

    //獲取用戶緩存
    public UserCache getUserCache(){
        return this.userCache;
    }

    //設置用戶緩存
    public void setUserCache(UserCache userCache){
        this.userCache = userCache;
    }

    //透過用戶名稱獲取用戶詳情
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //從緩存中獲取
        UserDetails user = this.userCache.getUserFromCache(username);
        //如果緩存中的資訊為null
        if(user == null){
            //使用其他方式獲取用戶資訊
            user = this.delegate.loadUserByUsername(username);
        }
        //判斷用戶資訊是否為空
        Assert.notNull(user, () ->{
            return "UserDetailService" + this.delegate + "returned null for username " + username + " . This is an interface contract violation";
        });
        //不為空則向緩存中添加用戶資訊
        //下此可從緩存中獲取
        this.userCache.putUserInCache(user);
        //返回用戶信息
        return user;
    }
}
