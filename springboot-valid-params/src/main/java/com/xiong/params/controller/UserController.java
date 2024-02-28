package com.xiong.params.controller;

import com.xiong.params.entity.TUser;
import com.xiong.params.entity.param.UserParam;
import com.xiong.params.service.TUserService;
import com.xiong.params.utils.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private TUserService userService;

    @PostMapping("add")
    public RespBean add(@Valid @RequestBody UserParam userParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            errors.forEach(p -> {
                FieldError fieldError = (FieldError) p;
                log.error("Invalid Parameter : object - {},field - {},errorMessage - {}", fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
            });

            return RespBean.error("invalid parameter");
        }

        int i = userService.addUser(userParam);

        return i> 0 ? RespBean.ok("success"):RespBean.error("insert user fail.");
    }

    @GetMapping("list")
    public RespBean list(){
        List<TUser> allUser = userService.getAllUser();

        return RespBean.ok("ok",allUser);
    }
}
