package com.example.notwebapp.service.impl;

import com.example.notwebapp.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public String sayHello() {
        return "2022 say hello shenzhen";
    }
}
