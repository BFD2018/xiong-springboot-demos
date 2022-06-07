package com.baiduai.face_recognition.service;

import com.baiduai.face_recognition.utils.BaiduAiUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserFaceServiceImpl implements UserFaceService {

    @Override
    public String registerFace(String s, StringBuffer imagebase64, HttpServletRequest request) {
        String image = imagebase64.substring(imagebase64.indexOf(",") + 1, imagebase64.length());
        String ret = new BaiduAiUtils().faceRegister(image);
        System.out.println("=====================>");
        System.out.println(ret);

        return ret;
    }

    @Override
    public String loginByFace(String imagebase64) {
        String image = imagebase64.substring(imagebase64.indexOf(",") + 1, imagebase64.length());
        String ret = new BaiduAiUtils().faceSearch(image);

        return ret;
    }
}
