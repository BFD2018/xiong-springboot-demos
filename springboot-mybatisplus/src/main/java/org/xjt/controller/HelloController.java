package org.xjt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping(path = {"/", "/index", "/index.html"})
    public String toIndexPage() {
        return "index";
    }
}
