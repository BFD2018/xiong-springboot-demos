package com.xjt.fdfs.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.xjt.fdfs.domain.FastdfsFile;
import com.xjt.fdfs.mapper.FdfsMapper;
import com.xjt.fdfs.service.FdfsService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class FdfsServiceImpl extends ServiceImpl<FdfsMapper,FastdfsFile> implements FdfsService {

    @Autowired
    private FdfsMapper fdfsMapper;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private FastFileStorageClient storageClient;

    @Value("${fdfs.web-server-url}")
    private String server_host;

    @Override
    public int downloadFileByFileId(String id) {
        FastdfsFile fastdfsFile = fdfsMapper.selectById(id);

        try {
            StorePath storePath = StorePath.parseFromUrl(fastdfsFile.getUrl());

            byte[] bytes = storageClient.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray());
            response.getOutputStream().write(bytes);

            FileUtil.writeBytes(bytes,FileUtil.file(storePath.getPath()));

            return 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Object uploadFile(MultipartFile file) {
        StorePath storePath = null;
        try {
            storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()),null);
            String group = storePath.getGroup();
            String fullPath = storePath.getFullPath();
            String path = storePath.getPath();

            FastdfsFile fastdfsFile = new FastdfsFile();
            fastdfsFile.setId(IdUtil.createSnowflake(1, 1).nextId());
            fastdfsFile.setOriginalName(file.getOriginalFilename());
            fastdfsFile.setGroupName(group);
            fastdfsFile.setStatus(0);
            fastdfsFile.setUrl(fullPath);
            fastdfsFile.setFilesize(file.getSize());
            fastdfsFile.setNewName(path.substring(path.lastIndexOf("/")));
            fastdfsFile.setExt(FileUtil.getSuffix(file.getOriginalFilename()));
            fdfsMapper.insert(fastdfsFile);

            return fastdfsFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object uploadImage(MultipartFile file, String size) {
        StorePath storePath;
        FastdfsFile fastdfsFile = new FastdfsFile();
        if(StringUtils.hasLength(size)){
            try {
                storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), FileUtil.getSuffix(file.getOriginalFilename()), null);

                String group = storePath.getGroup();
                String fullPath = storePath.getFullPath();
                String path = storePath.getPath();

                fastdfsFile.setId(IdUtil.createSnowflake(1, 1).nextId());
                fastdfsFile.setOriginalName(file.getOriginalFilename());
                fastdfsFile.setGroupName(group);
                fastdfsFile.setStatus(0);
                fastdfsFile.setUrl(fullPath);
                fastdfsFile.setFilesize(file.getSize());
                fastdfsFile.setNewName(path.substring(path.lastIndexOf("/")));
                fastdfsFile.setExt(FileUtil.getSuffix(file.getOriginalFilename()));
                fdfsMapper.insert(fastdfsFile);

                return fastdfsFile;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
