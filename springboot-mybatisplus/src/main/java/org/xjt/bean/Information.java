package org.xjt.bean;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Information {

    // 资讯id
    private Integer id;

    //资讯信息
    private String dataInfo;

    //资讯类型
    private Integer type;

    //创建时间
    private String createTime;

    //修改时间
    private String updateTime;

    //InformationVO转Information
    public Information(InformationVO informationVO){
        this.id = informationVO.getId();
        this.dataInfo = JSON.toJSONString(informationVO.getDataInfo());
        this.type = informationVO.getType();
        this.createTime = informationVO.getCreateTime();
        this.updateTime = informationVO.getUpdateTime();
    }
}