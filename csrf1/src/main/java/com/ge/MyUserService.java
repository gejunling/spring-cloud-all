package com.ge;

import java.util.Random;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 在这里执行查询
        System.out.println("开始查询数据源。。。");

        if(new Random().nextBoolean()) {

            throw new LockedException("用户已锁定");
        }else {
            throw new BadCredentialsException("我错了");
        }
    }

}
