package com.xjt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjt.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
