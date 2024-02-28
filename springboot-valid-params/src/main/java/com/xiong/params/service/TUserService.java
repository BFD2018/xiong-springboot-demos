package com.xiong.params.service;

import com.xiong.params.entity.TUser;
import com.xiong.params.entity.param.UserParam;

import java.util.List;

/**
 * @author jtxiong
 * @version 1.0
 * @description: TODO
 * @date 2024/2/28 21:11
 */
public interface TUserService {
    int addUser(UserParam userParam);

    List<TUser> getAllUser();
}
