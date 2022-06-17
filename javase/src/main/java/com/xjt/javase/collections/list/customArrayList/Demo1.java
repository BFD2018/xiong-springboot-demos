package com.xjt.javase.collections.list.customArrayList;

import org.junit.Test;

public class Demo1 {
    @Test
    public void test01(){
        MyArrayList<String> list = new MyArrayList<>();
        list.add("xiong");
        list.add("zhang");
        list.add("wang");
        System.out.println(list);
        System.out.println(list.contains("wang"));
        System.out.println(list.getSize());
        list.remove(1);
        System.out.println(list);

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
    }
}
