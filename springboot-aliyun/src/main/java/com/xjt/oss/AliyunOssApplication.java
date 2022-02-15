package com.xjt.oss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.xjt.oss.mapper")
@SpringBootApplication
public class AliyunOssApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliyunOssApplication.class, args);
    }

}
