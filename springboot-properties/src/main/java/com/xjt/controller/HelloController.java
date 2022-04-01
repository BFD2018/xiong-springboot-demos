package com.xjt.controller;

import com.xjt.utils.MyJDBC;
import com.xjt.utils.MyPeople;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${xjt.name}")
    private String name;


    @Autowired
    private MyPeople myPeople;

    @Autowired
    private MyJDBC myJDBC;


    @GetMapping("/test01")
    public void test01(){
        System.out.println(name);

        System.out.println(myPeople);

        System.out.println(myJDBC);
    }
}
