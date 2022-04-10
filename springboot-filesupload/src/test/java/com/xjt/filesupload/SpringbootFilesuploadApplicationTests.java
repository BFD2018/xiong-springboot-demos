package com.xjt.filesupload;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

@SpringBootTest
class SpringbootFilesuploadApplicationTests {

    @Test
    void contextLoads() throws FileNotFoundException {
        String filePath = System.getProperty("user.dir") + "/src/main/resources/static/files/";
        System.out.println(filePath);

        System.out.println(ResourceUtils.getURL("classpath").getPath());

        System.out.println(ResourceUtils.getURL("/").getPath());

        System.out.println(this.getClass().getResource("/").toString());

        String classFullPath = this.getClass().getResource("").toString();
        System.out.println(classFullPath);

        System.out.println("======================================>");

        System.out.println(SpringbootFilesuploadApplicationTests.class.getResource("/").getPath());
        System.out.println(new File("").getPath());
        System.out.println(new File("").getAbsolutePath());
    }

}
