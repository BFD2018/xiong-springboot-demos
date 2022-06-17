package com.xjt.javase.designPattern.template;

public class PeanutSoyamilk extends Soyamilk{

    @Override
    void addCondiments() {
        System.out.println("添加花生");
    }

    @Override
    void soak() {
        System.out.println("浸泡3s");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
