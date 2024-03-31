package com.xiong.mybatis.mapper;

import com.xiong.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author jtxiong
 * @version 1.0
 * @description: TODO
 * @date 2024/3/31 11:35
 */
@Mapper
public interface IUserMapper {

    List<User> findList();

    int saveUser(User user);

    int update(User user);

    int deleteById(Long id);

    int deleteByIds(Long[] ids);

    User findById(Long id);
}
