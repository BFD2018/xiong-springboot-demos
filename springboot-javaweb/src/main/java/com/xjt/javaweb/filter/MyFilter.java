package com.xjt.javaweb.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/*方式一：实现Filter接口*/
@WebFilter("/*")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化MyFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("您已进入filter过滤器 doFilter，您的请求正常，请继续遵循规则....");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("myfilter被销毁了。。。");
    }
}
