package com.lee.config;

import com.lee.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    /*
    授权规则
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //定制请求的授权规则
        //角色的设置需要和上面的权限一致，不然会报403
        http.authorizeRequests().antMatchers("/").hasRole("1");
        //开启自动配置的登录功能
        http.csrf().disable()
                .formLogin()
                .loginPage("/index.html")
                .loginProcessingUrl("/user/login")
                .successForwardUrl("/user/login");
    }

     /*
    认证规则
     */
    @Autowired
    UserServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //在这里完成获得数据库中的用户信息
    //密码一定要加密
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
