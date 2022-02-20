package com.xjt.fdfs.controller;

import cn.hutool.core.io.FileUtil;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(tags = "FastDFS测试")
@RestController
@RequestMapping("/api/fastdfs")
public class FileController {
    @Autowired
    private FastFileStorageClient storageClient;

    public final static String server_host = "http://192.168.157.134:8888/";

    @ResponseBody
    @ApiOperation(value = "上传文件",httpMethod = "POST")
    @PostMapping("/upload")
    public String uploadFile(@ApiParam("文件") MultipartFile file) throws IOException {
        StorePath storePath = storageClient.uploadFile(file.getInputStream(),
                file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);

        String filepath = server_host + storePath.getFullPath();
        return "文件上传成功 地址为："+filepath;
    }

    @ResponseBody
    @ApiOperation(value = "删除文件",httpMethod = "POST")
    @PostMapping("/delete")
    public void deleteFile(String fileUrl){
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @ResponseBody
    @ApiOperation(value = "下载文件",httpMethod = "POST")
    @PostMapping("/download")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response,String fileUrl){
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            System.out.println(storePath.getGroup());
            System.out.println(storePath.getPath());
            byte[] bytes = storageClient.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray());
            response.getOutputStream().write(bytes);

            FileUtil.writeBytes(bytes,FileUtil.file(storePath.getPath()));
        } catch (Exception e) {
            return;
        }
    }
}
