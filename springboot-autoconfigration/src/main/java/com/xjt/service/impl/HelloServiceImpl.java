package com.xjt.service.impl;

import com.xjt.domain.RespBean;
import com.xjt.domain.TCart;
import com.xjt.mapper.HelloMapper;
import com.xjt.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class HelloServiceImpl implements HelloService {
    @Autowired
    private HelloMapper helloMapper;

    @Override
    public RespBean testShiwu() {
        TCart cart = new TCart().setId(1).setName("小米8手机").setNum(1).setPrice(1999.9);
        int i = helloMapper.updateById(cart);

        System.out.println("更新结果：" + i);

        //在此构造一个除数为0的异常，测试事务是否起作用
        int a = 10/0;

        return RespBean.ok("ok",i);
    }
}
