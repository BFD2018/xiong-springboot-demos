package com.blr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


//用来对 spring security 进行自定义配置
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("**/oauth/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .and()
                .oauth2Login(); //使用 oauth2 认证  配置文件中配置认证服务

    }
}