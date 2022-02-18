package com.xjt.javaweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


//@ServletComponentScan(basePackages = {"com.xjt.javaweb.filter","com.xjt.javaweb.servlet"})
//@ServletComponentScan("com.xjt.javaweb.servlet")          //方式一：包扫描


@ServletComponentScan("com.xjt.javaweb.filter")
@SpringBootApplication
public class SpringbootJavawebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJavawebApplication.class, args);
    }

}
