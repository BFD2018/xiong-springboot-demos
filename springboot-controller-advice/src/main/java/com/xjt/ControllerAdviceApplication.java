package com.xjt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ControllerAdviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControllerAdviceApplication.class, args);
    }

}
