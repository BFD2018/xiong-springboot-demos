package com.xjt.javase.myUtils.apacheCommon;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MyDemo01 {
    public static void main(String[] args) {
//        testArrayUtils();

        testCollections();


    }

    public static void testArrayUtils(){
        String[] strings = {"song","zhu"};
        String[] strings1 = ArrayUtils.addAll(strings, "zhang", "wang", "li", "zhao", "xiong");
        boolean flag = ArrayUtils.isEmpty(strings1);

        int li = ArrayUtils.indexOf(strings1, "li");
        System.out.println(li);
        System.out.println(flag);
        System.out.println(ArrayUtils.toString(strings1));
    }

    public static void testCollections(){
        ArrayList<String> arr = new ArrayList<>();
        CollectionUtils.addAll(arr,"china","japan","usa","uk");
        System.out.println(arr);
    }

    @Test
    public void testCodec() throws UnsupportedEncodingException, DecoderException {
        System.out.println("==============Base64================");
        byte[] data = "jianggujin".getBytes();
        Base64 base64 = new Base64();
        String encode = base64.encodeAsString(data);
        System.out.println(encode);
        System.out.println(new String(base64.decode(encode)));

        System.out.println("==============MD5================");
        String result = DigestUtils.md5Hex("jianggujin");
        System.out.println(result);

        System.out.println("==============URLCodec================");
        URLCodec codec = new URLCodec();
        String url = "https://pdai.tech/md/interview/x-interview.html#1-java-%E5%9F%BA%E7%A1%80";
        String encodeUrl = codec.encode(url, "UTF-8");
        System.out.println(encodeUrl);
        System.out.println(codec.decode(encodeUrl, "UTF-8"));
    }

    @Test
    public void testMath3(){
        
    }
}
