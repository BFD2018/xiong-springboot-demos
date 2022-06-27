package com.baiduai.face_recognition.service;

import com.baiduai.face_recognition.utils.RespBean;

public interface UserFaceService {
    RespBean registerFace(String imagebase64,String userinfo);

    RespBean loginByFace(String imagebase64);
}
