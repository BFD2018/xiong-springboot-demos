package com.baiduai.face_recognition.service;

import javax.servlet.http.HttpServletRequest;

public interface UserFaceService {
    String registerFace(String s, StringBuffer imagebase64, HttpServletRequest request);

    String loginByFace(String imagebase64);
}
