package com.xiong.mybatis.service.impl;

import com.xiong.mybatis.entity.Role;
import com.xiong.mybatis.entity.query.RoleQueryBean;
import com.xiong.mybatis.mapper.IRoleMapper;
import com.xiong.mybatis.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jtxiong
 * @version 1.0
 * @description: TODO
 * @date 2024/3/31 16:55
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleMapper roleMapper;

    @Override
    public List<Role> findList() {

        List<Role> roleList = roleMapper.getAllRole();
        return roleList;
    }

    @Override
    public int add(RoleQueryBean roleQueryBean) {
        return roleMapper.add(roleQueryBean);
    }
}
