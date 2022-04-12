package com.xjt.myshiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.xjt.myshiro.mapper")
@SpringBootApplication
public class SpringbootShiroAdvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShiroAdvancedApplication.class, args);
    }

}
