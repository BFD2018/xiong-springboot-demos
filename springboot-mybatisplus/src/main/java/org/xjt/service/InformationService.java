package org.xjt.service;

import com.alibaba.fastjson.JSONObject;
import org.xjt.bean.InformationVO;
import org.xjt.utils.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface InformationService {


    /**
     * 查询所有的信息
     */
    R list(JSONObject jsonObject) throws ExecutionException, InterruptedException;

    /**
     * 插入资讯信息
     */
    int insert(InformationVO informationVO);

    /**
     * 更新资讯信息
     */
    int update(InformationVO informationVO);

    /**
     * 删除资讯信息
     * @param ids ids
     */
    int delete(List<Integer> ids);
}
