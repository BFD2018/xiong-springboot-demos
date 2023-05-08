package org.xjt.controller;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xjt.bean.InformationVO;
import org.xjt.service.InformationService;
import org.xjt.utils.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping("/information")
public class InformationController {

    private final InformationService informationService;

    public InformationController(InformationService informationService) {
        this.informationService = informationService;
    }

    /**
     * 查询所有信息
     */
    @RequestMapping("/list")
    public R list(@RequestBody JSONObject jsonObject) throws ExecutionException, InterruptedException {
        return informationService.list(jsonObject);
    }

    /**
     * 插入资讯信息
     */
    @RequestMapping("/insert")
    public R insert(@RequestBody InformationVO informationVO) throws ExecutionException, InterruptedException {
        return R.ok(""+informationService.insert(informationVO));
    }

    /**
     * 更新资讯信息
     */
    @RequestMapping("/update")
    public R update(@RequestBody InformationVO informationVO) throws ExecutionException, InterruptedException {
        Assert.notNull(informationVO.getId(),"修改时id不能为空！");
        return R.ok("" + informationService.update(informationVO));
    }

    /**
     * 删除资讯信息
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody List<Integer> ids) {
        log.info("ids:{}",ids);
        Assert.notEmpty(ids,"id不能为空！");
        return R.ok("" + informationService.delete(ids));
    }
}
