package com.xiong.mybatis.controller;

import com.xiong.mybatis.entity.Role;
import com.xiong.mybatis.entity.User;
import com.xiong.mybatis.service.IRoleService;
import com.xiong.mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author jtxiong
 * @version 1.0
 * @description: TODO
 * @date 2024/5/18 16:17
 */
@Controller
@RequestMapping("thymeleaf")
public class ThymeleafController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/index")
    public String index(Model model) {
        List<User> allUser = userService.findAllUser();
        List<Role> roleList = roleService.findList();

        model.addAttribute("allUser",allUser);
        model.addAttribute("allRole",roleList);
        return "index";
    }
}
