package com.xjt.controller;

import com.xjt.entity.User;
import com.xjt.service.UserService;
import com.xjt.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Api(description = "用户User接口")
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("查询1条记录")
    @GetMapping("/get/{id}")
    public RespBean getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return RespBean.ok("ok", user);
    }

    @ApiOperation("数据库新增3条记录")
    @PostMapping("/add")
    public void addUser(@RequestBody User user) {
        userService.insertUser(user);
    }

}
