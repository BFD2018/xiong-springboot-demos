package com.xjt.cache.service.impl;

import com.xjt.cache.domain.Employee;
import com.xjt.cache.mapper.EmployeeMapper;
import com.xjt.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Cacheable(cacheNames = {"emp"})        //先从缓存中拿数据 如果有就不执行目标方法
    @Override
    public Employee getEmpById(Integer id) {
        return employeeMapper.getEmpById(id);
    }

    @CachePut(value = "emp",key = "#id")            //先调用目标方法，将目标方法的结果缓存起来
    @Override
    public Employee updateEmp(int id){
        //先通过id查询
        Employee employee = getEmpById(id);

        //重新设置
        employee.setLastName("zhangsansan");
        employeeMapper.updateEmp(employee);
        return employee;
    }

    @CacheEvict(value = "emp",key = "#id",beforeInvocation = true)      //在目标方法执行前清除缓存
    @Override
    public void delEmp(Integer id){
        employeeMapper.deleteEmpById(id);
    }
}
