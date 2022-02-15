package com.xjt.oss;

import com.xjt.oss.domain.TFile;
import com.xjt.oss.mapper.TFileUploadMapper;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class AliyunOssApplicationTests {
    @Autowired
    private TFileUploadMapper fileUploadMapper;

    @Test
    void contextLoads() {
        fileUploadMapper.insert(new TFile().setId("123adfdasvjl").setFileKey("files/2022/02/15/b64fdeac38924792ae4fs95989ded-爱上.jpg").setFileSize("456112345278").setUpdateTime(new Date()));
    }

}
