package com.xjt.service.impl;

import com.xjt.domain.Employees;
import com.xjt.mapper.HelloMapper;
import com.xjt.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jtxiong
 * @version 1.0
 * @description: TODO
 * @date 2024/1/14 15:45
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Autowired
    private HelloMapper helloMapper;

    @Override
    public List<Employees> getAllEmployee() {
        List<Employees> employeeList = helloMapper.selectList(null);

        return employeeList;
    }
}
