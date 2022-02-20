package com.xjt.exception;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.xjt.exception.mapper")
@SpringBootApplication
public class SpringbootExceptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootExceptionApplication.class, args);
    }

}
