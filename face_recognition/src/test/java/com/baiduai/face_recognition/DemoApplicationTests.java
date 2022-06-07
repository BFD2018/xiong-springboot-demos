package com.baiduai.face_recognition;

import com.baiduai.face_recognition.config.BaiduFaceAIConfig;
import com.baiduai.face_recognition.utils.BaiduAiUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    public BaiduFaceAIConfig baiduFaceAIConfig;

    @Test
    void testGetAuth(){
        new BaiduAiUtils().getAuth();
    }

}
