package org.xjt.mapper;


import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.xjt.bean.Information;

import java.util.List;

@Mapper
public interface InformationMapper {
    /**
     * 查询所有的信息
     */
    List<Information> list(JSONObject jsonObject);

    /**
     * 插入资讯信息
     */
    int insert(Information information);

    /**
     * 更新资讯信息
     */
    int update(Information information);

    /**
     * 删除资讯信息
     *
     * @param ids
     */
    int delete(@Param("ids") List<Integer> ids);
}
