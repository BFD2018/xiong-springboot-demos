package com.xjt.httpclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 测试HttpClient用法
 */
@Controller
public class HelloController {
    @GetMapping("/index")
    public String toIndex() {
        return "index.html";
    }
}
