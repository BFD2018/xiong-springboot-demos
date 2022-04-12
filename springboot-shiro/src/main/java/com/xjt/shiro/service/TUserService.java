package com.xjt.shiro.service;

import com.xjt.myshiro.domain.TPerm;
import com.xjt.myshiro.domain.TUser;
import com.xjt.myshiro.utils.RespBean;

import java.util.List;
import java.util.Set;

public interface TUserService {
    List<TPerm> findPermsListByRoleId(Integer roleId);

    TUser findByUsername(String principal);

    RespBean addUser(String username, String password);

    Set<String> getPermissionsByUsername(String principal);
}
