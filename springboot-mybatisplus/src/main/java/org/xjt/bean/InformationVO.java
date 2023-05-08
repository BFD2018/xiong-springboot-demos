package org.xjt.bean;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class InformationVO {

    // 资讯id
    private Integer id;

    //资讯信息
    private JSONObject dataInfo;

    //资讯类型
    private Integer type;

    //创建时间
    private String createTime;

    //修改时间
    private String updateTime;

}