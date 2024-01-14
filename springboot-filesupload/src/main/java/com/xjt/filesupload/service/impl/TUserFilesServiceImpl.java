package com.xjt.filesupload.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjt.filesupload.domain.TUserFile;
import com.xjt.filesupload.mapper.TUserFilesMapper;
import com.xjt.filesupload.service.TUserFilesService;
import com.xjt.filesupload.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class TUserFilesServiceImpl implements TUserFilesService {
    @Autowired
    private TUserFilesMapper userFilesMapper;

    @Override
    public Integer uploadFile(TUserFile userFile) {
        return userFilesMapper.insert(userFile);
    }

    @Override
    public RespBean getAllFilesByUserId(String user_id) {
        List<TUserFile> fileList = userFilesMapper.selectList(new QueryWrapper<TUserFile>().eq("user_id", user_id));
        if (ObjectUtils.isEmpty(fileList)) {
            return RespBean.error("没找到文件");
        } else {
            return RespBean.ok("ok", fileList);
        }
    }
}
