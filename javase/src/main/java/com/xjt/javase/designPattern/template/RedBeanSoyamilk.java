package com.xjt.javase.designPattern.template;

public class RedBeanSoyamilk extends Soyamilk{

    @Override
    void addCondiments() {
        System.out.println("添加上好的红豆");
    }

    @Override
    void soak() {
        System.out.println("浸泡5s");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
