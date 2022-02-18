package com.example.notwebapp;

import com.example.notwebapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//方式二：Spring boot 的入口类实现CommandLineRunner接口

@SpringBootApplication
public class NotwebappApplication implements CommandLineRunner {
    //第二步：通过容器获取bean，并注入给userService
    @Autowired
    public StudentService studentService;

    public static void main(String[] args) {
        //第一步：SpringBoot的启动程序，会初始化spring容器
        SpringApplication.run(NotwebappApplication.class, args);
    }

    //覆盖接口中的run方法
    @Override
    public void run(String... args) throws Exception {
        //第三步：容器启动后调用run方法，在该方法中调用业务方法
        String s = studentService.sayHello();
        System.out.println(s);
    }
}
