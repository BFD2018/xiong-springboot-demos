package com.xjt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.xjt.mapper")
@SpringBootApplication
public class DockerComposeApplication {
    public static void main(String[] args) {
        SpringApplication.run(DockerComposeApplication.class,args);
    }
}
