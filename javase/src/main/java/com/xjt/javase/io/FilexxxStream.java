package com.xjt.javase.io;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FilexxxStream {
    @Test
    public void testFilexxxSteam() throws IOException {
        /*
        * D:\CodeLearning\JavaLearning\JavaProjects\xjt-spring-framework\javase
        * */
        String base = System.getProperty("user.dir");
        String path = base + "\\src\\main\\java\\com\\xjt\\javase\\io\\";
        System.out.println(path);

        File directory = new File("..");
        /*
        * getCanonicalPath
        *   D:\CodeLearning\JavaLearning\JavaProjects\xjt-spring-framework
        * getAbsolutePath
        *   D:\CodeLearning\JavaLearning\JavaProjects\xjt-spring-framework\javase\..
        * */
        System.out.println(directory.getCanonicalPath());
        System.out.println(directory.getAbsolutePath());
        System.out.println(directory.getPath());    // ..

        String filename = "你的答案.mp3";
        File file = new File(path + filename);
        FileInputStream fin = null;
        FileOutputStream fout = null;
        if (file.isFile()) {
            int len = 0;
            fin = new FileInputStream(file);
            fout = new FileOutputStream(path + "new.mp3",true);
            byte[] bytes = new byte[1024];
            while ((len = fin.read(bytes))!=-1){
                fout.write(bytes,0,len);
            }
        }
    }

    @Test
    public void test01(){

    }
}
