package com.xjt.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ObjectMetadata;
import com.xjt.oss.config.AliyunOssConfig;
import com.xjt.oss.domain.RespBean;
import com.xjt.oss.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private OSS ossClient;          // 注入阿里云oss文件服务器客户端

    @Autowired
    private AliyunOssConfig aliyunOssConfig;// 注入阿里云OSS基本配置类

    // 允许上传文件(图片)的格式
    private static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg", ".jpeg", ".gif", ".png"};

    @Override
    public RespBean uploadFile(MultipartFile file) {
        // 获取oss的Bucket名称
        String bucketName = aliyunOssConfig.getBucketName();
        // 获取oss的地域节点
        String endpoint = aliyunOssConfig.getEndPoint();
        // 获取oss的AccessKeySecret
        String accessKeySecret = aliyunOssConfig.getAccessKeySecret();
        // 获取oss的AccessKeyId
        String accessKeyId = aliyunOssConfig.getAccessKeyId();
        // 获取oss目标文件夹
        String filehost = aliyunOssConfig.getFileHost();

        // 返回图片上传后返回的url
        String returnImgeUrl = "";

        // 校验图片格式
        boolean isLegal = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(file.getOriginalFilename(), type)) {
                isLegal = true;
                break;
            }
        }
        if (!isLegal) {// 如果图片格式不合法
            return RespBean.error("图片格式不合法");
        }
        // 获取文件原名称
        String originalFilename = file.getOriginalFilename();
        // 获取文件类型
        String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 新文件名称
        String newFileName = UUID.randomUUID().toString() + fileType;
        // 构建日期路径, 例如：OSS目标文件夹/2020/10/31/文件名
        String filePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        // 文件上传的路径地址
        String uploadImgeUrl = filehost + "/" + filePath + "/" + newFileName;

        // 获取文件输入流
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * 下面两行代码是重点坑：
         * 现在阿里云OSS 默认图片上传ContentType是image/jpeg
         * 也就是说，获取图片链接后，图片是下载链接，而并非在线浏览链接，
         * 因此，这里在上传的时候要解决ContentType的问题，将其改为image/jpg
         */
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType("image/jpg");

        //文件上传至阿里云OSS
        ossClient.putObject(bucketName, uploadImgeUrl, inputStream, meta);
        /**
         * 注意：在实际项目中，文件上传成功后，数据库中存储文件地址
         */
        // 获取文件上传后的图片返回地址
        returnImgeUrl = "http://" + bucketName + "." + endpoint + "/" + uploadImgeUrl;

        return RespBean.ok("ok",returnImgeUrl);
    }


/*
    @Override
    public String deleteFile(String fileName) {
        // 获取oss的Bucket名称
        String bucketName = aliyunOssConfig.getBucketName();
        // 获取oss的地域节点
        String endpoint = aliyunOssConfig.getEndPoint();
        // 获取oss的AccessKeySecret
        String accessKeySecret = aliyunOssConfig.getAccessKeySecret();
        // 获取oss的AccessKeyId
        String accessKeyId = aliyunOssConfig.getAccessKeyId();
        // 获取oss目标文件夹
        String filehost = aliyunOssConfig.getFileHost();
        // 日期目录
        // 注意，这里虽然写成这种固定获取日期目录的形式，逻辑上确实存在问题，但是实际上，filePath的日期目录应该是从数据库查询的
        String filePath = new DateTime().toString("yyyy/MM/dd");

        try {
            *//**
             * 注意：在实际项目中，不需要删除OSS文件服务器中的文件，
             * 只需要删除数据库存储的文件路径即可！
             *//*
            // 建议在方法中创建OSSClient 而不是使用@Bean注入，不然容易出现Connection pool shut down
            OSSClient ossClient = new OSSClient(endpoint,
                    accessKeyId, accessKeySecret);
            // 根据BucketName,filetName删除文件
            // 删除目录中的文件，如果是最后一个文件fileoath目录会被删除。
            String fileKey = filehost + "/" + filePath + "/" + fileName;
            ossClient.deleteObject(bucketName, fileKey);

            try {
            } finally {
                ossClient.shutdown();
            }
            System.out.println("文件删除！");
            return StatusCode.SUCCESS.getMsg();
        } catch (Exception e) {
            e.printStackTrace();
            return StatusCode.ERROR.getMsg();
        }
    }*/
}
