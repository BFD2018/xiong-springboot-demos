package com.xiong.mybatis.service.impl;

import com.xiong.mybatis.entity.User;
import com.xiong.mybatis.mapper.IUserMapper;
import com.xiong.mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jtxiong
 * @version 1.0
 * @description: TODO
 * @date 2024/3/31 11:30
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserMapper userMapper;

    @Override
    public List<User> getAllUserList() {
        return userMapper.findList();
    }

    @Override
    public int save(User user) {
        return userMapper.saveUser(user);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int deleteById(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int deleteByIds(Long[] ids) {
        return userMapper.deleteByIds(ids);
    }

    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public User findUserById(Long id) {
        return userMapper.findUserById(id);
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }
}
