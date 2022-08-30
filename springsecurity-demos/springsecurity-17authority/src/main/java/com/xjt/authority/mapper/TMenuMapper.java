package com.xjt.authority.mapper;

import com.xjt.authority.entity.TMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TMenuMapper {
    List<TMenu> getAllMenu();
}
