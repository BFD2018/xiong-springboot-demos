package com.xjt.oss.controller;

import com.xjt.oss.domain.RespBean;
import com.xjt.oss.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("aliyun")
public class OssFileController {
    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping("/page/test")
    private String testAliyunPage(){
        return "aliyunOss";
    }

    /*上传*/
    @ResponseBody
    @PostMapping("/oss/upload/image")
    private RespBean ossImageUpload(MultipartFile file){
        System.out.println(file.getOriginalFilename());
        return fileUploadService.uploadImage(file);
    }

    /*上传文件*/
    @ResponseBody
    @PostMapping("/oss/upload")
    private RespBean ossFileUpload(MultipartFile file){
        System.out.println(file.getOriginalFilename());
        return fileUploadService.uploadFile(file);
    }

    /*下载*/
    /*上传*/
    @ResponseBody
    @GetMapping("/oss/list")
    private RespBean listAllOssFileByBucket(){
        System.out.println("===>搜索出bucket所有文件");
        return fileUploadService.listAllOssFileByBucket();
    }

    /*删除*/
    @ResponseBody
    @GetMapping("/oss/delete")
    private RespBean ossFileDelete(@RequestParam("filekey") String filekey){
        System.out.println("filekey========>"+filekey);
        return fileUploadService.deleteFile(filekey);
    }
}
