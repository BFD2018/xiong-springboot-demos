package com.xjt.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjt.shiro.domain.TPerm;
import com.xjt.shiro.domain.TUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TUserMapper extends BaseMapper<TUser> {
    TUser getRolesByUsername(String username);


    List<TPerm> findPermsListByRoleId(Integer roleId);

    Set<String> getAllPermissionsByUsername(String principal);
}
