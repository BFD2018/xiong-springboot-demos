package com.xjt.javase.api;

import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class Demo01 {
    @Test
    public void test01(){
//        byte[] bytes = new byte[]{'a','b','c'};
//        for (int i = 0; i < bytes.length; i++) {
//            System.out.println(bytes[i]);
//        }

        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2);    //false
        System.out.println(s1 == s5);    //true
        System.out.println(s1 == s6);    //false
        System.out.println(s1 == s6.intern());    //true
        System.out.println(s2 == s2.intern());    //false
    }

    @Test
    public void test02(){
        String s1 = "str01";
        String s2 = new String("str") + new String("01");
        s2.intern();
        System.out.println(s1 == s2);   //false
    }

    @Test
    public void test03(){
        String s2 = new String("str") + new String("01");
        String s1 = "str01";
        s2.intern();
        System.out.println(s1 == s2);       //false
    }

    @Test
    public void test04(){
        String s2 = new String("str") + new String("01");
        s2.intern();
        String s1 = "str01";
        System.out.println(s1 == s2);       //true
    }

    @Test
    public void test05(){
        String s1 = new String("abc");
        String s2 = "abc";
        String s3 = new String("abc");
        System.out.println(s1 == s2.intern());  //false
        System.out.println(s1 == s3.intern());  //false
        System.out.println(s2 == s3.intern());  //true
    }

    @Test
    public void test06(){
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.YEAR));
        System.out.println(cal.get(Calendar.MONTH)); // 0 - 11
        System.out.println(cal.get(Calendar.DATE));
        System.out.println(cal.get(Calendar.HOUR_OF_DAY));
        System.out.println(cal.get(Calendar.MINUTE));
        System.out.println(cal.get(Calendar.SECOND));

        // Java 8
        LocalDateTime dt = LocalDateTime.now();
        System.out.println(dt.getYear());
        System.out.println(dt.getMonthValue()); // 1 - 12
        System.out.println(dt.getDayOfMonth());
        System.out.println(dt.getHour());
        System.out.println(dt.getMinute());
        System.out.println(dt.getSecond());
    }

    @Test
    public void test07(){
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));    //2022/4/11 下午5:41
        System.out.println(dateFormat2.format(date));    //2022-04-11 05:43:19
    }

    @Test
    public void test08(){
        byte[] bytes = {'a', 'b', 'c', 'd', 'e', 'f'};

        System.out.println(new String(bytes, 0, 3));
        System.out.println(new String(bytes, 2, 3));

    }

    /** 字节流：写文档
     * 向 ./test/mydoc.txt 写入 "我是中华人民共和国公民 热爱党 热爱中国 热爱人民！"
     */
    @Test
    public void test09() throws IOException {
        File file = new File("./test/mydoc.txt");
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        byte[] bytes = "我是中华人民共和国公民 热爱党 热爱中国 热爱人民！".getBytes();
        fos.write(bytes);

        //刷新
        fos.flush();
        //关闭
        fos.close();
    }

    /**
     * 写 二进制文件
     * 拷贝d盘 视频到 本地 /test 目录
     * @throws IOException
     */
    @Test
    public void test09_1() throws IOException {
        File f1 = new File("D:\\视频\\111.flv");
        File f2 = new File("./test/222.flv");
        if(!f2.getParentFile().exists()){
            f2.getParentFile().mkdirs();
        }
        if(!f2.exists()){
            f2.createNewFile();
        }
        FileInputStream fis = new FileInputStream(f1);
        FileOutputStream fos = new FileOutputStream(f2);
        int len = 0;
        byte[] bytes = new byte[1024];
        while ((len = fis.read(bytes))!= -1){
            fos.write(bytes);
        }

        //刷新
        fos.flush();
        //关闭
        fis.close();
        fos.close();
    }

    /**
     * 字节流：读文档
     * @throws IOException
     */
    @Test
    public void test10() throws IOException {
        FileInputStream fis = new FileInputStream(new File("./test/mydoc.txt"));
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = fis.read(bytes))!= -1){
            System.out.println(new String(bytes, 0, len));
        }

        fis.close();
    }

    /**
     * 字符流：读文档
     */
    @Test
    public void test11() throws IOException {
        FileReader fr = new FileReader(new File("./test/mydoc.txt"), Charset.defaultCharset());
        char[] temp = new char[1024];
        int len=0;
        while ((len = fr.read(temp)) != -1 ){
            System.out.println(new String(temp,0,len));
        }

        fr.close();

    }

    /**
     * 字符流：写文档
     */
    @Test
    public void test12() throws IOException {
        FileWriter fw = new FileWriter(new File("./test/mydoc.txt"), Charset.defaultCharset(),true);
        fw.write("hello world!");
        fw.write("java is the best programming language");

        fw.close();
    }


}
