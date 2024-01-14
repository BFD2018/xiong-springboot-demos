package com.xjt.mysql.controller;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.xjt.mysql.entity.TUser;
import io.swagger.annotations.Api;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(tags = "HelloController")
@Controller
public class HelloController {

    @ResponseBody
    @GetMapping("/test")
    public Object test01() {
        //1.代码中获取用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //2.通过获取用户信息
        TUser user = (TUser) authentication.getPrincipal();
        System.out.println("username= " + user.getUsername());
        System.out.println("authorities= " + user.getAuthorities());

        return "user ok";
    }

    @ResponseBody
    @GetMapping("/test02")
    public Object test02() {
        return "hahha";
    }

    @GetMapping("/captcha")
    public void get_captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();

        // png类型
        SpecCaptcha captcha = new SpecCaptcha(130, 48);
        captcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        captcha.setLen(4);
        String text = captcha.text();// 获取验证码的字符
        request.getSession().setAttribute("myCaptcha", text);        //将验证码存入session

        captcha.out(outputStream);  // 输出验证码
    }
}
