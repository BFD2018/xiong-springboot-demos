package com.xjt.mysql.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping({"/login.html","login"})
    public String toLogin(){
        return "login";
    }

    @GetMapping({"/home","/"})
    public String toHome(){
        return "home";
    }
}
