package com.xiong.params.service.impl;

import com.xiong.params.entity.TUser;
import com.xiong.params.entity.param.UserParam;
import com.xiong.params.mapper.TUserMapper;
import com.xiong.params.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author jtxiong
 * @version 1.0
 * @description: TODO
 * @date 2024/2/28 21:12
 */
@Service
public class TUserServiceImpl implements TUserService {
    @Autowired
    private TUserMapper userMapper;

    @Override
    public int addUser(UserParam userParam) {
        TUser user = TUser.builder()
                .username(userParam.getUsername())
                .password(userParam.getPassword())
                .sex(userParam.getSex())
                .deleted(userParam.getDeleted())
                .createTime(new Date()).build();
        int insert = userMapper.insert(user);
        return insert;
    }

    @Override
    public List<TUser> getAllUser() {
        List<TUser> tUsers = userMapper.selectList(null);
        return tUsers;
    }
}
