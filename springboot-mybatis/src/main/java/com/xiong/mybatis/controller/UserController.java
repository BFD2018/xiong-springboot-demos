package com.xiong.mybatis.controller;

import com.xiong.mybatis.common.responseResult.ResponseResult;
import com.xiong.mybatis.entity.User;
import com.xiong.mybatis.entity.query.UserQueryBean;
import com.xiong.mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author jtxiong
 * @version 1.0
 * @description: TODO
 * @date 2024/3/31 11:16
 */
@RequestMapping("user")
@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("all")
    private ResponseResult<List> getAllUser(UserQueryBean userQueryBean){
        List<User> userList = userService.getAllUserList();

        return ResponseResult.success(userList);
    }

    @RequestMapping("getById/{id}")
    private ResponseResult<User> getUserById(@PathVariable("id") Long id){
        User user = userService.findById(id);

        return ResponseResult.success(user);
    }



    @RequestMapping("deleteById/{id}")
    private ResponseResult<User> deleteUserById(@PathVariable("id") Long id){
        int i = userService.deleteById(id);
        if(i > 0){
            return ResponseResult.success();
        }
        else{
            return ResponseResult.fail("delete user fail.");
        }
    }

    @PostMapping("add")
    public ResponseResult<User> addUser(User user) {
        if (user.getId()==null) {
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            userService.save(user);
        } else {
            user.setUpdateTime(LocalDateTime.now());
            userService.update(user);
        }
        return ResponseResult.success(userService.findById(user.getId()));
    }

    @GetMapping("edit/{userId}")
    public ResponseResult<User> editUser(@PathVariable("userId") Long userId) {
        return ResponseResult.success(userService.findById(userId));
    }

    // 使用 @Result @Select 注解查询
    @RequestMapping("findById/{id}")
    private ResponseResult<User> findUserById(@PathVariable("id") Long id){
        User user = userService.findUserById(id);

        return ResponseResult.success(user);
    }

    // 使用 @Result @Select 注解查询
    @RequestMapping("findAll")
    private ResponseResult<List<User>> findAllUser(){
        List<User> userList = userService.findAllUser();

        return ResponseResult.success(userList);
    }
}
