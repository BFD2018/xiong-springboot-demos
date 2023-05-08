package org.xjt.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xjt.bean.Information;
import org.xjt.bean.InformationVO;
import org.xjt.mapper.InformationMapper;
import org.xjt.service.InformationService;
import org.xjt.utils.R;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InformationServiceImpl implements InformationService {

    private final InformationMapper informationMapper;

    public InformationServiceImpl(InformationMapper informationMapper) {
        this.informationMapper = informationMapper;
    }


    @Override
    public R list(JSONObject jsonObject) throws ExecutionException, InterruptedException {
        CompletableFuture<List<InformationVO>> list = CompletableFuture.supplyAsync(
                () -> informationMapper.list(jsonObject).stream().map(o -> {
                    InformationVO informationVO = new InformationVO();
                    informationVO.setId(o.getId());
                    informationVO.setDataInfo(JSON.parseObject(o.getDataInfo()));
                    informationVO.setCreateTime(o.getCreateTime());
                    informationVO.setUpdateTime(o.getUpdateTime());
                    return informationVO;
                }).collect(Collectors.toList()));
        List<InformationVO> informations = list.get();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("ok",new JSONObject().put("informations", informations));

        return R.ok(hashMap);
    }

    @Transactional
    @Override
    public int insert(InformationVO informationVO) {
        log.info("informationVO :{}",informationVO);
        return informationMapper.insert(new Information(informationVO));
    }

    @Transactional
    @Override
    public int update(InformationVO informationVO) {
        return informationMapper.update(new Information(informationVO));
    }

    @Transactional
    @Override
    public int delete(List<Integer> ids) {
        return informationMapper.delete(ids);
    }
}
