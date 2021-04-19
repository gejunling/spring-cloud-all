package com.ge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyAuthprovider implements AuthenticationProvider {

    @Autowired
    MyUserService userSrv;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 密码校验

        System.out.println("开始自定义验证~~~~");
        System.out.println(authentication);
        // 表单提交的用户名和密码
        String username = authentication.getPrincipal().toString();
        String rawPassword = authentication.getCredentials().toString();

        //查询用户名
        UserDetails userDetails = userSrv.loadUserByUsername(username);

        // 密码加密器
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // 密码加密
        // 注意 不能对提交的密码加密，再和数据库中的密码进行比较，因为随机盐的缘故，对提交的密码加密，肯定和数据库中的密码不相同。
        // 应该使用BCryptPasswordEncoder提供的match方法，比对密码
        //String encodePass = passwordEncoder.encode(rawPassword);
        //if (userDetails.getPassword().equals(encodePass)) {

        if (passwordEncoder.matches(rawPassword, userDetails.getPassword())) {
            //密码通过，登录成功
            return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        }
        throw new BadCredentialsException("用户名或密码错误！");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        return true;
    }
}
