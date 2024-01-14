package com.xjt.test;

import com.xjt.mysql.entity.TUser;
import com.xjt.mysql.service.MyUserDetailService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
public class TestDemo {
    @Autowired
    public MyUserDetailService myUserDetailService;

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Test
    public void test01() {
        UserDetails xiong = myUserDetailService.loadUserByUsername("xiong");

        System.out.println(xiong.getUsername());
        System.out.println(xiong.getAuthorities());
    }

    @Test
    public void test02() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123"));
//        String sql = "select * from t_user";
//        List<TUser> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<TUser>(TUser.class));
//        System.out.println(userList);
    }
}
