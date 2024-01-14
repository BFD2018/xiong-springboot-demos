package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    //定义需要拦截的路径
    String[] addPathPatterns = {
            "/springboot/**"
    };

    //定义不需要拦截的路径
    String[] excludePathPatterns = {
            "/test/**",
            "/springboot/login",
            "/springboot/doLogin",
            "/springboot/register",
            "/springboot/doRegister",
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns(addPathPatterns)
                .excludePathPatterns(excludePathPatterns);
    }
}
