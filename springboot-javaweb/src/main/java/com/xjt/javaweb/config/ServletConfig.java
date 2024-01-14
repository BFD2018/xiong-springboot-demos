package com.xjt.javaweb.config;

import com.xjt.javaweb.servlet.MyServletConf;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*方式二：通过配置类*/

@Configuration
public class ServletConfig {
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean<MyServletConf> servletRegistrationBean = new ServletRegistrationBean<>(new MyServletConf(), "/xxoo", "/serv");
        return servletRegistrationBean;
    }
}
