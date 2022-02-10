package com.xjt.oss.service;

import com.xjt.oss.domain.RespBean;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    RespBean uploadFile(MultipartFile file);
}
