package com.xjt.authority.service;

import com.xjt.authority.entity.TRole;
import com.xjt.authority.entity.TUser;
import com.xjt.authority.mapper.TUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private TUserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.根据用户名查询用户信息
        TUser user = userMapper.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<TRole> roles = userMapper.getUserRoleByUid(user.getId());
        System.out.println("roles===>" + roles);
        user.setRoleList(roles);

        return user;
    }
}
