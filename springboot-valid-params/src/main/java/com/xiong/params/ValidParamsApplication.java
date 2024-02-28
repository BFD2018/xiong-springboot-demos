package com.xiong.params;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: springboot参数校验
 * @author jtxiong
 * @date 2024/2/25 18:29
 * @version 1.0
 */
@MapperScan("com.xiong.params.mapper")
@SpringBootApplication
public class ValidParamsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ValidParamsApplication.class, args);
    }

}
