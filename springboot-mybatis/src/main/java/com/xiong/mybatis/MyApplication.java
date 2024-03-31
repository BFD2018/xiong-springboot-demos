package com.xiong.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jtxiong
 * @version 1.0
 * @description: TODO
 * @date 2024/3/31 11:09
 */
@SpringBootApplication
@MapperScan("com.xiong.mybatis.mapper")
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class,args);
    }
}
