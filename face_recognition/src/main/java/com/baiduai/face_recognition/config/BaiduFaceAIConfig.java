package com.baiduai.face_recognition.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BaiduFaceAIConfig {
    @Value("${baiduai.face.appId}")
    public String appId;

    @Value("${baiduai.face.apiKey}")
    public String apiKey;

    @Value("${baiduai.face.secretKey}")
    public String secretKey;

    @Value("${baiduai.face.imageType}")
    public String imageType;

    @Value("${baiduai.face.groupId}")
    public String groupId;

}
