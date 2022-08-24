package com.xjt.mysql.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjt.mysql.entity.TUser;
import com.xjt.mysql.mapper.TUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class MyUserDetailService implements UserDetailsService, UserDetailsPasswordService {
    @Autowired
    private TUserMapper tUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.根据用户查询用户
        QueryWrapper<TUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        TUser user = tUserMapper.selectOne(wrapper);
        if (ObjectUtils.isEmpty(user)) throw new UsernameNotFoundException("用户名不存在");

        //2.获取角色
        user.setRoles(tUserMapper.getRolesByUid(user.getId()));
        return user;
    }

    //默认使用DelegatingPasswordEncoder 默认使用相当最安全密码加密 Bcrypt ----> Cxxx
    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        int update = tUserMapper.updatePasswordByUsername(user.getUsername(), newPassword);
        if(update>0){
            ((TUser)user).setPassword(newPassword);
        }

        return user;
    }
}
