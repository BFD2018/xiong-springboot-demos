package com.xjt.fdfs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjt.fdfs.domain.FastdfsFile;
import org.springframework.web.multipart.MultipartFile;

public interface FdfsService extends IService<FastdfsFile> {
    int downloadFileByFileId(String id);

    Object uploadFile(MultipartFile file);

    Object uploadImage(MultipartFile file, String size);
}
