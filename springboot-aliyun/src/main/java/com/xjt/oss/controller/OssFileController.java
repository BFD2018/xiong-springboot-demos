package com.xjt.oss.controller;

import com.xjt.oss.domain.RespBean;
import com.xjt.oss.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("aliyun")
public class OssFileController {
    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping("/page/test")
    private String testAliyunPage(){
        return "testAliyun";
    }

    @ResponseBody
    @PostMapping("/upload")
    private RespBean ossFileUpload(MultipartFile file){
        System.out.println(file.getOriginalFilename());
        return fileUploadService.uploadFile(file);
    }
}
