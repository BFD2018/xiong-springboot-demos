package com.xjt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xjt.domain.Employees;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HelloMapper extends BaseMapper<Employees> {
    // List<Employees> getAllEmployees();
}
