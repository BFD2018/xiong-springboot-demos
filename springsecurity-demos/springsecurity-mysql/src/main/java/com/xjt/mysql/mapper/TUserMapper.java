package com.xjt.mysql.mapper;

import com.xjt.mysql.entity.TRole;
import com.xjt.mysql.entity.TUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-08-23
 */
@Mapper
public interface TUserMapper extends BaseMapper<TUser> {

    TUser loadUserByUsername(String username);

    List<TRole> getRolesByUid(Integer id);

    int updatePasswordByUsername(String username, String newPassword);
}
