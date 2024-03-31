package com.xiong.mybatis.service;

import com.xiong.mybatis.entity.Role;
import com.xiong.mybatis.entity.query.RoleQueryBean;

import java.util.List;

public interface IRoleService {
    List<Role> findList(RoleQueryBean roleQueryBean);

    int add(RoleQueryBean roleQueryBean);
}
