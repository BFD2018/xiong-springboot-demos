package com.xjt.javase.api;

public class MyTest {
    static int value = 9;
    public static void main(String[] args) throws Exception{
        new MyTest().printValue();
    }
    public void printValue(){
        int value = 69;
        System.out.println(this.value);
    }
}
