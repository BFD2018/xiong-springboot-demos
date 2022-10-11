package com.xjt.cache.controller;

import com.xjt.cache.domain.Employee;
import com.xjt.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("emp")
public class EmpController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/getEmp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id){
        return employeeService.getEmpById(id);
    }

    @RequestMapping("/updateEmp/{id}")
    public Employee updateEmp(@PathVariable("id") Integer id){
        return employeeService.updateEmp(id);
    }

    @RequestMapping("/delEmp/{id}")
    public void delEmp(@PathVariable("id") Integer id){
        employeeService.delEmp(id);
    }
}
