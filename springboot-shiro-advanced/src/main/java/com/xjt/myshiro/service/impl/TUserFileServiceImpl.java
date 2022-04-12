package com.xjt.myshiro.service.impl;

import com.xjt.myshiro.mapper.TUserFileMapper;
import com.xjt.myshiro.service.TUserFileService;
import com.xjt.myshiro.utils.RespBean;
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
