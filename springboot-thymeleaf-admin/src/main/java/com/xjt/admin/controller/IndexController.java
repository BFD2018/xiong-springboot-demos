package com.xjt.admin.controller;

import com.xjt.admin.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @GetMapping(path = {"/","/index","index"})
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model){
        if("xiong".equals(user.getUserName()) && "123456".equals(user.getPassword())){
            session.setAttribute("loginUser",user);

            return "redirect:/main.html";
        }else{
            model.addAttribute("msg","用户名或密码错误");
            return "loign";
        }
    }

    @GetMapping("/main.html")
    public String mainPage(HttpSession session,Model model){
        Object loginUser = session.getAttribute("loginUser");
        if(loginUser != null){
            return "main";
        }else{
            //回到登录页面
            model.addAttribute("msg","请重新登录");
            return "login";
        }
    }

}
