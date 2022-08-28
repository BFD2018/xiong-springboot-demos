package com.xjt.source.config;

import com.xjt.source.domain.Account;
import com.xjt.source.domain.MyBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public MyBeanPostProcessor beanPostProcessor() {
        MyBeanPostProcessor myBeanPostProcessor = new MyBeanPostProcessor();
        return myBeanPostProcessor;
    }

    @Bean
    public Account account() {
        return new Account("fff");
    }
}
