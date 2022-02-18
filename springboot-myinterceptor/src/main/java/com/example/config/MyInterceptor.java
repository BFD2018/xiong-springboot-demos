package com.example.config;

import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("执行MyInterceptor.preHandle==============>");
        HashMap<String, String> loginUser = (HashMap<String, String>) request.getSession().getAttribute("login_user");

        if(ObjectUtils.isEmpty(loginUser)){
            response.sendRedirect(request.getContextPath()+"/springboot/login");
            //被拦截
            return false;
        }else{
            //true:通过
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("执行MyInterceptor.postHandle--------------->");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("执行MyInterceptor.afterCompletion++++++++++++++++++++++++>");
    }
}
