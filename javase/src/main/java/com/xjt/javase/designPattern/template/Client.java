package com.xjt.javase.designPattern.template;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("制作红豆豆浆---》");
        Soyamilk redBeanSoyamilk = new RedBeanSoyamilk();
        redBeanSoyamilk.make();

        System.out.println("制作花生豆浆---》");
        Soyamilk peanutSoyamilk = new PeanutSoyamilk();
        peanutSoyamilk.make();

        System.out.println("制作纯豆浆---》");
        Soyamilk pureSoyamilk = new PureSoyamilk();
        pureSoyamilk.make();
    }
}
