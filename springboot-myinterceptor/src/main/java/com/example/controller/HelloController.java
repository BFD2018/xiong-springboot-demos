package com.example.controller;

import com.example.domain.TUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping("springboot")
public class HelloController {
    @GetMapping("hello")
    private String toHelloPage(){
        return "/test/hello";
    }

    @GetMapping("/login")
    private String toLoginPage(){
        return "/test/login";
    }

    @PostMapping("/doLogin")
    private void handleLoginReqest(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestBody TUser tUser) throws IOException, ServletException {
        String username = tUser.getUsername().trim();
        String password = tUser.getPassword().trim();

        if(username.equalsIgnoreCase("zhangsan") && password.equalsIgnoreCase("123456")){
            HashMap<String, String> map = new HashMap<>();
            map.put("username",username);
            map.put("password",password);
            session.setAttribute("login_user",map);
        }
    }
}
