package com.blr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/admin")  //ADMIN
    public String admin() {
        return "admin ok";
    }

    @GetMapping("/user")  //USER
    public String user() {
        return "user ok";
    }

    @GetMapping("/getInfo")  //READ_INFO
    public String getInfo() {
        return "info ok";
    }

}