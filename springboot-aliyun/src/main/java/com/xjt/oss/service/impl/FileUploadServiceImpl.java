package com.xjt.oss.service.impl;

import cn.hutool.core.io.FileUtil;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.xjt.oss.config.AliyunOssConfig;
import com.xjt.oss.domain.RespBean;
import com.xjt.oss.domain.TFile;
import com.xjt.oss.mapper.TFileUploadMapper;
import com.xjt.oss.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
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

    @Autowired
    private TFileUploadMapper fileUploadMapper;

    @Override
    public RespBean uploadImage(MultipartFile file) {
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

        String[] IMAGE_TYPE = new String[]{".bmp", ".jpg", ".jpeg", ".gif", ".png"};

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
        String newFileName = UUID.randomUUID().toString().replace("-","") + fileType;

        // 构建日期路径, 例如：OSS目标文件夹  /2022/10/文件名
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

        try {
            if(!ossClient.doesBucketExist(bucketName)){
                ossClient.createBucket(bucketName);
                CreateBucketRequest bucketRequest = new CreateBucketRequest(bucketName);
                bucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(bucketRequest);
            }
            //文件上传至阿里云OSS
            ossClient.putObject(bucketName, uploadImgeUrl, inputStream, meta);
        } catch (OSSException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        /**
         * 注意：在实际项目中，文件上传成功后，数据库中存储文件地址
         */
        // 获取文件上传后的图片返回地址
        returnImgeUrl = "http://" + bucketName + "." + endpoint + "/" + uploadImgeUrl;

        return RespBean.ok("ok",returnImgeUrl);
    }

    @Override
    public RespBean listAllOssFileByBucket() {
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

        ObjectListing objectListing = ossClient.listObjects(bucketName);

        return RespBean.ok("ok",objectListing);

    }

    @Override
    public RespBean uploadFile(MultipartFile file) {
        // 获取oss的Bucket名称
        String bucketName = aliyunOssConfig.getBucketName();
        // 获取oss的地域节点
        String endpoint = aliyunOssConfig.getEndPoint();
        // 获取oss目标文件夹
        String filehost = aliyunOssConfig.getFileHost();

        // 获取文件原名称
        String originalFilename = file.getOriginalFilename();
        // 新文件名称
        String newFileName = UUID.randomUUID().toString().replace("-","") + "-" + originalFilename;

        // 构建日期路径, 例如：OSS目标文件夹  /2022/10/文件名
        Date date = new Date();
        String filePath = new SimpleDateFormat("yyyy/MM/dd").format(date);
        // 文件上传的路径地址
        String uploadUrl = filehost + "/" + filePath + "/" + newFileName;

        // 获取文件输入流
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PutObjectResult putObjectResult = null;
        try {
            if(!ossClient.doesBucketExist(bucketName)){
                ossClient.createBucket(bucketName);
                CreateBucketRequest bucketRequest = new CreateBucketRequest(bucketName);
                bucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(bucketRequest);
            }
            //1、文件上传至阿里云OSS
            putObjectResult = ossClient.putObject(bucketName, uploadUrl, inputStream);

            //2、存入数据库
            // 获取文件上传后的返回地址
            String returnUrl = "http://" + bucketName + "." + endpoint + "/" + uploadUrl;
            String eTag = putObjectResult.getETag();
            TFile tFile = new TFile().setId(eTag).setFileKey(returnUrl).setFileSize(String.valueOf(file.getSize())).setUpdateTime(date);
            int insert = fileUploadMapper.insert(tFile);

            if(putObjectResult.getResponse().isSuccessful() && insert>0){
                return RespBean.ok("ok",returnUrl);
            }else{
                return RespBean.error("error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("error",e.getMessage());
        }
    }


    @Override
    public RespBean deleteFile(String filekey) {
        String bucketName = aliyunOssConfig.getBucketName();
        try {
            ossClient.deleteObject(bucketName, filekey);
            return RespBean.ok("ok");
        } catch (OSSException e) {
            e.printStackTrace();
            return RespBean.error("error",e.getMessage());
        } catch (ClientException e) {
            e.printStackTrace();
            return RespBean.error("error",e.getMessage());
        }
    }

    @Override
    public void downloadOssFile(HttpServletResponse response, String objectName) {
        String bucketName = aliyunOssConfig.getBucketName();

        try {
            // 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
            OSSObject ossObject = ossClient.getObject(bucketName, objectName);
            // 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
            InputStream content = ossObject.getObjectContent();
            if (content != null) {
                FileUtil.writeFromStream(content,new File(objectName));

                // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
                content.close();
            }
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException | IOException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
