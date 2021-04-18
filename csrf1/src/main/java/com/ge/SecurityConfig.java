package com.ge;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 两次虽然输入的都是123，但是输出的结果不一样，因为有随机盐
        System.out.println(encoder.encode("123"));
        System.out.println(encoder.encode("123"));
        http
                // 哪些地方需要登录
                .authorizeRequests()
                // 所有请求都需要验证
                .anyRequest()
                .authenticated()
                .and() // 把一个配置项，想象成一个xml标签，这个and()就相当于结束标签，只有看到结束标签，才标志着一个配置完成，才能继续后面的配置。

                // permitAll 给没登录的用户可以访问地址的权限
                // 自定义登录页面
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .permitAll()
                // 登录失败页面
                .failureUrl("/login.html?error")
                // 登录成功跳转页面
                .defaultSuccessUrl("/ok", true)
                .permitAll()
                // 配置登录页面表单的 name和password的属性名
                .passwordParameter("password")
                .usernameParameter("username")
                .failureHandler(
                        new AuthenticationFailureHandler() {
                            @Override
                            public void onAuthenticationFailure(
                                    HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    AuthenticationException e)
                                    throws IOException, ServletException {
                                e.printStackTrace();
                                // 判断异常信息，跳转不同的页面，比如密码过期(跳转到到更新密码页面)
                                httpServletRequest
                                        .getRequestDispatcher(httpServletRequest.getRequestURL().toString())
                                        .forward(httpServletRequest, httpServletResponse);
                                // 记录登录和失败的次数，失败次数超限之后，禁止登录
                            }
                        })
                .and()
                .csrf()
                // 这里传参还有一个CookieCsrfTokenRepository，就是保存在cookie中，不能预防csrf攻击，所以选择保存到session中。
                .csrfTokenRepository(new HttpSessionCsrfTokenRepository());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 配置用户的账号密码
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        auth
                .inMemoryAuthentication()
                .withUser("admin").password(encoder.encode("admin")).roles("admin")
                .and()
                .withUser("test").password(encoder.encode("test")).roles("user");

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
