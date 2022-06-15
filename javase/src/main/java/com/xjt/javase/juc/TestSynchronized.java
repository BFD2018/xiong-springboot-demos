package com.xjt.javase.juc;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TestSynchronized")
public class TestSynchronized {
    static int counter = 0;
    static final Object room1 = new Object();
    static final Object room2 = new Object();

    public static void main(String[] args) throws InterruptedException {
//        test01();

        test02();
    }

    public static void test01() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (room1) {
                for (int i = 0; i < 50; i++) {
                    log.debug("执行i="+i);
                    counter++;
                }
            }

        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (room1) {
                for (int i = 0; i < 50; i++) {
                    log.debug("执行i="+i);
                    counter--;
                }
            }

        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("{}", counter);
    }

    public static void test02() throws InterruptedException {
        Room room = new Room();
        Thread t1 = new Thread(() -> {
            for (int j = 0; j < 5000; j++) {
                room.increment();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int j = 0; j < 5000; j++) {
                room.decrement();
            }
        }, "t2");
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        log.debug("count: {}", room.get());
    }
}

class Room {
    int value = 0;

    public void increment() {
        synchronized (this) {
            value++;
        }
    }

    public void decrement() {
        synchronized (this) {
            value--;
        }
    }

    public int get() {
        synchronized (this) {
            return value;
        }
    }
}
