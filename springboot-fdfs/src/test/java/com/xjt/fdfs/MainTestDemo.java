package com.xjt.fdfs;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootTest
public class MainTestDemo {
    @Resource
    private FastFileStorageClient ffsc;

    @Test
    public void test01() throws IOException {
        //读取图片
        FileInputStream fis = new FileInputStream("D:\\图片\\deepin壁纸\\abc-123.jpg");
        //进行图片上传
        StorePath sp = ffsc.uploadFile(fis, fis.available(), "jpg", null);
        //打印访问后的地址
        System.out.println(sp.getGroup());      //group1
        System.out.println(sp.getPath());       //M00/00/00/wKidgWRXfHWASurlABEQNJY9yAI936.jpg
        System.out.println(sp.getFullPath());       //group1/M00/00/00/wKidgWRXfHWASurlABEQNJY9yAI936.jpg
    }
}
