package com.xjt.cache.service;

import com.xjt.cache.domain.Employee;

public interface EmployeeService {
    Employee getEmpById(Integer id);

    Employee updateEmp(int id);

    void delEmp(Integer id);
}
