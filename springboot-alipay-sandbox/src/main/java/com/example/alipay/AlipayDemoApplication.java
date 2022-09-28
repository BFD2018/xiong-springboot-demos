package com.example.alipay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.alipay.dao")
@SpringBootApplication
public class AlipayDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlipayDemoApplication.class, args);
    }

}
