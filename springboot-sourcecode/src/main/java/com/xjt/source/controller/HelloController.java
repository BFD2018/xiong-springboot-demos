package com.xjt.source.controller;

import com.xjt.source.domain.TUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Api(tags = "HELLO模块")
@Controller
@RequestMapping("/hello")
public class HelloController {

    @ApiOperation(value = "首页")
    @GetMapping("/index")
    public String toIndex(Model model) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "xiong");
        map.put("age", "18");
        map.put("addr", "shenzhen");
        model.addAttribute("user", map);

        return "index";
    }

    @ApiOperation(value = "用户列表页")
    @GetMapping("/user/list")
    public String toUserList() {

        return "/user/user-list";
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/user/add")
    public String addUser(Model model, @ApiParam(value = "用户名") String name, @ApiParam(value = "用户年龄") Integer age, @ApiParam(value = "用户性别") Integer sex) {
        TUser tUser = new TUser().setName(name).setAge(age).setSex(sex == 1 ? true : false);
        model.addAttribute("userObj", tUser);

        return "/user/user-add";
    }
}
