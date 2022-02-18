package com.xjt.controller;

import com.xjt.domain.RespBean;
import com.xjt.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("all")
    private RespBean getAllStudent(){
        return studentService.getAllStudent();
    }
}
