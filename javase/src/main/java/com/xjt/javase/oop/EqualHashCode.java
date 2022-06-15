package com.xjt.javase.oop;

import org.junit.Test;

import java.util.ArrayList;

public class EqualHashCode {
    @Test
    public void test01(){
        String s1 = new String("xiong");
        String s2 = new String("xiong");
        System.out.println(s1 == s2);   //false
        System.out.println(s1.equals(s2));  //true
        System.out.println(s1.hashCode());  //114060759
        System.out.println(s2.hashCode());  //114060759

        Object o1 = new Object();
        Object o2 = new Object();
        System.out.println(o1);     //java.lang.Object@7d4793a8
        System.out.println(o2);     //java.lang.Object@449b2d27
        System.out.println(o1 == o2);   //false
        System.out.println(o1.hashCode());  //2101842856
        System.out.println(o2.hashCode());  //1151020327
        System.out.println(o2.equals(o1));  //false
    }

    @Test
    public void test02(){
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("xiong");
        list1.add("wang");

        ArrayList<String> list2 = new ArrayList<>();
        list2.add("xiong");
        list2.add("wang");
        list2.add("zhao");
        list2.remove(2);

        System.out.println(list1 == list2);     //false
        System.out.println(list1.equals(list2));        //true
        System.out.println(list1.hashCode());   //-755440947
        System.out.println(list2.hashCode());   //-755440947
    }

    @Test
    public void test03(){

    }
}
