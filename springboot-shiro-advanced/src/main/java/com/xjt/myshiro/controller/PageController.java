package com.xjt.myshiro.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("view")
public class PageController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @RequiresAuthentication     //示当前Subject已经身份验证或者通过记住我登录的
    @GetMapping("/index")
    public String indexPage() {
        return "/index";
    }

    @RequiresPermissions("user:*")     //表示当前Subject需要权限 user:*
    @GetMapping("/user/list")
    public String userListPage() {
        log.warn("xjt--->{}", "进入view.user.list.html");

        return "/user/list";

    }

    @RequiresPermissions("route:*")
    @GetMapping("/route/list")
    public String routeListPage() {
        log.warn("xjt--->{}", "进入view.route.list.html");

        return "/route/list";
    }

    @RequiresPermissions("*:*")
    @GetMapping("/admin/index")
    public String adminPage() {
        log.warn("xjt--->{}", "进入view.admin.index.html");
        return "/admin/index";
    }

    @ResponseBody
    @RequiresRoles(value = {"userManager", "admin"}, logical = Logical.OR)    //表示当前Subject需要角色 userManager或admin
    @GetMapping("/admin/user/list")
    public String adminUserPage() {
        System.out.println("========>/admin/user/list<=========");
        return "/admin/user-list";
    }

    @ResponseBody
    @GetMapping("/error/runtime")
    public void testRuntimeError() {
        int i = 12 / 0;
    }
}
