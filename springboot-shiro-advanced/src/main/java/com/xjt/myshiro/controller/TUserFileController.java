package com.xjt.myshiro.controller;

import com.xjt.myshiro.service.TUserFileService;
import com.xjt.myshiro.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("file")
public class TUserFileController {
    @Autowired
    private TUserFileService userFileService;

    @GetMapping("all")
    private RespBean getAllFile() {
        return userFileService.getAllFile();
    }
}
