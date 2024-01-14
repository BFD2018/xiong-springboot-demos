package com.xjt.mysql.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xjt.mysql.exception.CaptchaNotMatchException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

//自定义 filter
public class LoginCatpchaFilter extends UsernamePasswordAuthenticationFilter {
    public static final String FORM_CAPTCHA_KEY = "captcha";

    private String captchaParameter = FORM_CAPTCHA_KEY;

    public String getCaptchaParameter() {
        return captchaParameter;
    }

    public void setCaptchaParameter(String captchaParameter) {
        this.captchaParameter = captchaParameter;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        //这种方式是form表单请求域中获取（传统前后端不分离的开发方式）
        /*String username = request.getParameter("username");
        username = (username != null) ? username : "";
        username = username.trim();
        String password = request.getParameter("password");
        password = (password != null) ? password : "";
        //从表单中获取输入的验证码
        String captcha = request.getParameter(this.captchaParameter);*/

        //1.获取请求数据
        Map<String, String> userInfo = null;
        try {
            userInfo = new ObjectMapper().readValue(request.getInputStream(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String captcha = userInfo.get(getCaptchaParameter());//用来获取数据中验证码
        String username = userInfo.get(getUsernameParameter());//用来接收用户名
        String password = userInfo.get(getPasswordParameter());//用来接收密码

        //2.从session中获取验证码
        String sessionVerifyCode = (String) request.getSession().getAttribute("myCaptcha");

        if (!ObjectUtils.isEmpty(captcha) && !ObjectUtils.isEmpty(sessionVerifyCode) && captcha.equals(sessionVerifyCode)) {
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }

        try {
            throw new CaptchaNotMatchException("验证码不匹配!");
        } catch (CaptchaNotMatchException e) {
            e.printStackTrace();
        }

        return null;
    }
}
