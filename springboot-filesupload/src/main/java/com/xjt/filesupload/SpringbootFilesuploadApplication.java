package com.xjt.filesupload;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.xjt.filesupload.mapper")
public class SpringbootFilesuploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootFilesuploadApplication.class, args);
    }

}
