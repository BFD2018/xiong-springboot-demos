package com.xjt.shiro.controller;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import com.xjt.shiro.domain.TUser;
import com.xjt.shiro.service.TUserService;
import com.xjt.shiro.utils.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("user")
public class TUserController {
    @Autowired
    private TUserService tUserService;

    @PostMapping("/toLogin")
    public RespBean userLogin(@RequestBody HashMap<String, String> params, HttpSession session) {
        String username = params.get("username");
        String password = params.get("password");
        String verify_code = params.get("verify_code");

        //1、比较验证码
        String session_verify = (String) session.getAttribute("verify_code");
        log.warn("session_verify===>" + session_verify);
        if (StringUtils.hasLength(verify_code) && session_verify.equalsIgnoreCase(verify_code)) {
            try {
                //2、用户名 密码登录
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                subject.login(token);

                //从session中获取认证的TUser对象 返回给前端
                TUser user = (TUser) session.getAttribute("USER_SESSION");
                return RespBean.success("ok", user);
            } catch (UnknownAccountException e) {
                e.printStackTrace();
                return RespBean.error("用户名错误！");
            } catch (IncorrectCredentialsException e) {
                e.printStackTrace();
                return RespBean.error("密码错误！");
            } catch (Exception e) {
                e.printStackTrace();
                return RespBean.error(e.getMessage());
            }
        } else {
            return RespBean.error("验证码错误！");
        }
    }

    @PostMapping("/toRegister")
    public RespBean registerHandler(@RequestBody HashMap<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");

        return tUserService.addUser(username, password);
    }

    @PostMapping("/toLogout")
    public RespBean logoutHandler() {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            return RespBean.success("退出成功");
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("error", e.getMessage());
        }
    }

    @GetMapping("/getCaptcha")
    public void get_captcha(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        // 三个参数分别为宽、高、位数
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        // 设置字体
        specCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // 有默认字体，可以不用设置
        // 设置类型，纯数字、纯字母、字母数字混合
        specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);

        String text = specCaptcha.text();
        System.out.println("验证码=========>" + text);
        session.setAttribute("verify_code", text);

        // 输出图片流
        CaptchaUtil.out(specCaptcha, request, response);
    }
}
