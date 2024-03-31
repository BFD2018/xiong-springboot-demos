package com.xiong.mybatis.mapper;

import com.xiong.mybatis.entity.Role;
import com.xiong.mybatis.entity.query.RoleQueryBean;

import java.util.List;

public interface IRoleMapper {
    List<Role> getAllRole();

    int add(RoleQueryBean roleQueryBean);
}
