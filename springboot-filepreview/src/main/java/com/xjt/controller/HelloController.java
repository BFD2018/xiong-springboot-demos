package com.xjt.controller;

import com.xjt.domain.Employees;
import com.xjt.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author jtxiong
 * @version 1.0
 * @description: TODO
 * @date 2024/1/13 18:26
 */
@Controller
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping("/main")
    public String hellopage(Model model){
        List<Employees> allEmployee = helloService.getAllEmployee();
        model.addAttribute("employeeList",allEmployee);
        return "main";
    }
}
