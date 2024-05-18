package com.xiong.mybatis.service;

import com.xiong.mybatis.entity.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUserList();

    int save(User user);

    int update(User user);

    int deleteById(Long id);

    int deleteByIds(Long[] ids);

    User findById(Long id);

    User findUserById(Long id);

    List<User> findAllUser();
}
