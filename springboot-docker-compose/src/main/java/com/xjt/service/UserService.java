package com.xjt.service;

import com.xjt.entity.User;

public interface UserService {
    User getUserById(Long id);

    int insertUser(User user);
}
