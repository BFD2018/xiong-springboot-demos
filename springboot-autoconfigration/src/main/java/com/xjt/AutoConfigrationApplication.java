package com.xjt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.xjt.mapper")
public class AutoConfigrationApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AutoConfigrationApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

}
