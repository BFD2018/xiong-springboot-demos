package com.xjt.mysql.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xjt.mysql.filter.LoginCatpchaFilter;
import com.xjt.mysql.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailService myUserDetailService;

//    @Bean
//    public PasswordEncoder BcryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public LoginCatpchaFilter loginCatpchaFilter() throws Exception {
        LoginCatpchaFilter loginCatpchaFilter = new LoginCatpchaFilter();
        //1.认证Url
        loginCatpchaFilter.setFilterProcessesUrl("/doLogin");
        //2.认证接收参数
        loginCatpchaFilter.setUsernameParameter("username");
        loginCatpchaFilter.setPasswordParameter("password");
        loginCatpchaFilter.setCaptchaParameter("captcha");
        //3.指定认证管理器
        loginCatpchaFilter.setAuthenticationManager(authenticationManagerBean());

        //4.指定成功时处理器
        loginCatpchaFilter.setAuthenticationSuccessHandler((res,resp,authentication) ->{
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("msg", "登录成功");
            result.put("用户信息", authentication.getPrincipal());
            resp.setContentType("application/json;charset=UTF-8");
            resp.setStatus(HttpStatus.OK.value());
            String s = new ObjectMapper().writeValueAsString(result);
            resp.getWriter().println(s);
        });

        //5.认证失败处理器
        loginCatpchaFilter.setAuthenticationFailureHandler((res,resp,ex) ->{
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("msg", "登录失败: " + ex.getMessage());
            resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setContentType("application/json;charset=UTF-8");
            String s = new ObjectMapper().writeValueAsString(result);
            resp.getWriter().println(s);
        });

        return loginCatpchaFilter;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**").permitAll()     //注意swagger页面需要一些样式 需要请求/webjars
                .mvcMatchers("/login.html").permitAll()
                .mvcMatchers("/captcha").permitAll()        //验证码
                .mvcMatchers("/dist/**").permitAll()        // static/dist 目录放行
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")       //指定自定义登录界面
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((req, resp, ex) -> {
                    Map<String, Object> result = new HashMap<String, Object>();
                    result.put("msg", "必须认证之后才能访问! " + ex.getMessage());
                    result.put("code", HttpStatus.UNAUTHORIZED.value());
                    resp.setStatus(HttpStatus.UNAUTHORIZED.value());
                    resp.setContentType("application/json;charset=UTF-8");
                    String s = new ObjectMapper().writeValueAsString(result);
                    resp.getWriter().println(s);
                })
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html")
                .and().csrf().disable();

        http.addFilterAt(loginCatpchaFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
