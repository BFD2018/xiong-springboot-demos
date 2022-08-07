package com.xjt.admin.config;

import com.xjt.admin.interceptor.MyLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdminWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyLoginInterceptor())
                .addPathPatterns("/**")         //拦截所有请求
                .excludePathPatterns("/","/login","/css/**","/fonts/**","/images/**","/js/**");         //放行部分请求
    }
}
