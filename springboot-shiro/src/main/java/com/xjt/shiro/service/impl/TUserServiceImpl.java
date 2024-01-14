package com.xjt.shiro.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjt.shiro.domain.TPerm;
import com.xjt.shiro.domain.TUser;
import com.xjt.shiro.mapper.TUserMapper;
import com.xjt.shiro.service.TUserService;
import com.xjt.shiro.utils.RespBean;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TUserServiceImpl implements TUserService {
    @Autowired
    private TUserMapper tUserMapper;

    @Override
    public List<TPerm> findPermsListByRoleId(Integer roleId) {
        return tUserMapper.findPermsListByRoleId(roleId);
    }

    @Override
    public TUser findByUsername(String principal) {
        return tUserMapper.selectOne(new QueryWrapper<TUser>().eq("username", principal));
    }

    @Override
    public RespBean addUser(String username, String password) {
        TUser tUser = new TUser().setUsername(username);

        String salt = String.valueOf(RandomUtil.randomInt(9999));
        Md5Hash md5Hash = new Md5Hash(password, salt, 1024);
        tUser.setPassword(md5Hash.toHex());
        tUser.setSalt(salt);
        int insert = tUserMapper.insert(tUser);

        if (insert < 0) {
            return RespBean.error("error");
        } else {
            return RespBean.success("ok", tUser);
        }
    }

    @Override
    public Set<String> getPermissionsByUsername(String principal) {
        return tUserMapper.getAllPermissionsByUsername(principal);
    }
}
