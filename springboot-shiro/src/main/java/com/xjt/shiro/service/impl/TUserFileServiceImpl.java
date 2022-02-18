package com.xjt.shiro.service.impl;

import com.xjt.shiro.mapper.TUserFileMapper;
import com.xjt.shiro.service.TUserFileService;
import com.xjt.shiro.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TUserFileServiceImpl implements TUserFileService {
    @Autowired
    private TUserFileMapper userFileMapper;

    @Override
    public RespBean getAllFile() {
        return null;
    }
}
