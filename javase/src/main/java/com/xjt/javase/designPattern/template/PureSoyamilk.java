package com.xjt.javase.designPattern.template;

public class PureSoyamilk extends Soyamilk{

    @Override
    void addCondiments() {
        //空实现
    }

    @Override
    void soak() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("浸泡1s...");
    }

    @Override
    boolean isAddCondiments() {
        return false;
    }
}
