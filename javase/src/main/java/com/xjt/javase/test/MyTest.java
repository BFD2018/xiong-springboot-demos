package com.xjt.javase.test;

import java.math.BigDecimal;

public class MyTest {
    public static void main(String[] args) {
//        DataObject obj = new DataObject();
//        obj.setWord("123");
//        obj.setI(2);
//
//        String jsonString = JSON.toJSONString(obj);
//
//        DataObject dataObject = JSON.parseObject(jsonString, DataObject.class);
//        System.out.println(dataObject.getWord());
//        System.out.println(dataObject.getI());

//        String  a = "hello2";
//        final String b = "hello";
//        String  d = "hello";
//        String  c = b + 2;
//        String  e = d + 2;
//        System.out.println(a == c);
//        System.out.println(a == e);

        System.out.println(new BigDecimal(1.24));
        System.out.println(new BigDecimal("1.24"));
        System.out.println(new BigDecimal(Double.valueOf(1.24)));
        System.out.println(BigDecimal.valueOf(1.24));
    }
}
