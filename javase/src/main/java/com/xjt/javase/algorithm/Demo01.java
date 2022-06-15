package com.xjt.javase.algorithm;

import org.junit.Test;

public class Demo01 {
    //替换空格
    @Test
    public void test01(){
        String s = "We are happy.";
        String s1 = s.replaceAll(" ", "%20");
        System.out.println(s);      //We are happy.
        System.out.println(s1);     //We%20are%20happy.
    }
}
