package com.baiduai.face_recognition.service;

import com.baiduai.face_recognition.utils.BaiduAiUtils;
import com.baiduai.face_recognition.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFaceServiceImpl implements UserFaceService {
    @Autowired
    private BaiduAiUtils baiduAiUtils;

    @Override
    public RespBean registerFace(String imagebase64, String userinfo) {
        RespBean respBean = baiduAiUtils.faceRegister(imagebase64, userinfo);
        System.out.println("=====================>" + respBean.toString());

        return respBean;
    }

    @Override
    public RespBean loginByFace(String imagebase64) {
        RespBean respBean = baiduAiUtils.faceSearch(imagebase64);

        return respBean;
    }
}
