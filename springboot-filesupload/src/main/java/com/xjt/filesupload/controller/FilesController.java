package com.xjt.filesupload.controller;

import com.xjt.filesupload.domain.TUserFile;
import com.xjt.filesupload.service.TUserFilesService;
import com.xjt.filesupload.utils.RespBean;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("files")
public class FilesController {
    Logger logger = LoggerFactory.getLogger(FilesController.class);

    @Autowired
    private TUserFilesService userFilesService;

    @PostMapping("upload")
    public RespBean uploadFile(MultipartFile file,
                               @RequestParam("user_id") String user_id,
                               HttpServletRequest request) throws IOException {
        if(!StringUtils.hasText(user_id)){
            return RespBean.warn("未获取到用户id");
        }

        // 获取文件的原始名称
        String originalFilename = file.getOriginalFilename();
        // 获取文件的后缀
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        // 获取文件的大小
        long size = file.getSize();

        // 根据日期动态的生成目录
        String uploadDir = System.getProperty("user.dir") + "/springboot-filesupload/src/main/resources/static/filesContainer/" + new Date().getTime();
        System.out.println(new File("").getAbsolutePath());
        System.out.println("uploadDir---------->");
        System.out.println(uploadDir);
        File dateDirPath = new File(uploadDir);
        if (!dateDirPath.exists()) {
            dateDirPath.mkdirs();
        }
        logger.warn("上传文件路径："+dateDirPath.toString());
        //文件新名称
        String newFileName = UUID.randomUUID().toString().replace("-", "") + "-" + originalFilename;

        // 处理文件上传到本地
        file.transferTo(new File(dateDirPath, newFileName));

        // 将文件信息存入数据库中
        TUserFile userFile = new TUserFile();
        userFile.setOldFileName(originalFilename)
                .setNewFileName(newFileName)
                .setExt('.' + extension)
                .setPath(uploadDir + File.separator + newFileName)
                .setSize(""+size)
                .setDownCounts(0)
                .setUserId(Long.valueOf(user_id));

        Integer insert = userFilesService.uploadFile(userFile);
        if(insert >0){
            return RespBean.ok("上传成功",userFile);
        }else{
            return RespBean.error("文件上传失败");
        }

    }

    //获取用户id 获取所有文件
    @GetMapping("/all")
    public RespBean findAll(@RequestParam("user_id") String user_id){
        logger.warn("xjt--->findAll user_id:"+user_id);
        return userFilesService.getAllFilesByUserId(user_id);
    }

}
