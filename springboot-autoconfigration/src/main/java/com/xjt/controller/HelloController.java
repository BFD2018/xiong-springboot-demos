package com.xjt.controller;

import com.xjt.domain.RespBean;
import com.xjt.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {
    @Autowired
    private HelloService helloServicel;

    @ResponseBody
    @RequestMapping(value = "/hello",method = RequestMethod.POST)
    private String toHello(){

        return "hello world!";
    }

    @RequestMapping(value = "/testmodel")
    @ModelAttribute
    private String testmodel(){

        return "hello";
    }

    @ResponseBody
    @GetMapping("/shiwu")
    private RespBean toMysqlShiwu(){
        return helloServicel.testShiwu();
    }

    @GetMapping("/person")
    private String toPerson(Model model){
        model.addAttribute("name","xiong");
        model.addAttribute("age",22);

        return "person";
    }
}
