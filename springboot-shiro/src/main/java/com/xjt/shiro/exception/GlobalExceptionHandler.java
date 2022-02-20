package com.xjt.shiro.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(basePackages = "com.xjt.shiro.controller")
public class GlobalExceptionHandler {

    /**
     * 捕获未授权的异常
     *
     * @return 返回未授权页面
     */
    @ExceptionHandler(value = AuthorizationException.class)
    public Object exceptionHandler(Exception e) {
        e.printStackTrace();
        System.out.println(" = = = = = = = = = = 捕获到授权异常 = = = = = = = = = = ");
        // 跳转相应的页面
        return "unauthorized";
    }

//    @ExceptionHandler(value = AuthorizationException.class)
//    public Object exceptionHandler() {
//        System.out.println(" = = = = = = = = = = 捕获到授权异常 = = = = = = = = = = ");
//        // 跳转相应的页面
//        return "unauthorized";
//    }
}
