package com.xjt.shiro.service;

import com.xjt.shiro.domain.TPerm;
import com.xjt.shiro.domain.TUser;
import com.xjt.shiro.utils.RespBean;

import java.util.List;
import java.util.Set;

public interface TUserService {
    List<TPerm> findPermsListByRoleId(Integer roleId);

    TUser findByUsername(String principal);

    RespBean addUser(String username, String password);

    Set<String> getPermissionsByUsername(String principal);
}
