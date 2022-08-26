package com.xjt.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjt.authority.entity.TRole;
import com.xjt.authority.entity.TUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TUserMapper extends BaseMapper<TUser> {
    TUser loadUserByUsername(String username);

    List<TRole> getUserRoleByUid(Integer uid);
}
