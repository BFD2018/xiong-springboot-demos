package com.xjt.exception.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjt.exception.domain.TUser;
import com.xjt.exception.exception.BadConfigurationException;
import com.xjt.exception.exception.BadCredentialsException;
import com.xjt.exception.exception.EntityNotFoundException;
import com.xjt.exception.mapper.TUserMapper;
import com.xjt.exception.service.TUserService;
import com.xjt.exception.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TUserServiceImpl implements TUserService {
    @Autowired
    private TUserMapper userMapper;

    @Override
    public RespBean toLogin(String username, String password) {
        QueryWrapper<TUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        TUser user = null;
        try {
            user = userMapper.selectOne(wrapper);
        } catch (BadConfigurationException e) {
            throw new BadConfigurationException("查询数据库异常");
        }
        if (user == null) {
            throw new EntityNotFoundException(user.getClass(), "没有找到该用户", "");
        }
        if (!user.getPassword().equals(password)) {
            throw new BadCredentialsException("密码错误");
        }
        return RespBean.success("ok", user);
    }
}
