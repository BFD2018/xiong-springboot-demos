package com.xjt.exception.controller;

import com.xjt.exception.service.TUserService;
import com.xjt.exception.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping("user")
public class TUserController {
    @Autowired
    private TUserService userService;

    @ResponseBody
    @PostMapping("toLogin")
    private RespBean toLogin(@RequestBody HashMap<String, String> map) {
        System.out.println("map================>{}" + map);
        String username = map.get("username");
        String password = map.get("password");
        return userService.toLogin(username, password);
    }

    @GetMapping("view/login")
    private String loginPage() {
        int ret = 1 / 0;
        return "login";
    }
}
