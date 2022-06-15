package com.xjt.javase.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static com.xjt.javase.juc.utils.Sleeper.sleep;

@Slf4j(topic = "c.TestCondition")
public class TestCondition {
    static ReentrantLock lock = new ReentrantLock();
    static Condition waitCigarette = lock.newCondition();
    static Condition waitBreakfast = lock.newCondition();
    static boolean hasCigrette = false;
    static boolean hasBreakfast = false;

    public static void main(String[] args) {
        new Thread(()->{
            lock.lock();
            try {
                while (!hasCigrette){
                    try {
                        waitCigarette.await();
                        log.debug("抽到烟了 开始干活。。。");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally {
                lock.unlock();
            }
        },"抽烟干活").start();

        new Thread(()->{
            lock.lock();
            try {
                while (!hasBreakfast){
                    try {
                        waitBreakfast.await();
                        log.debug("吃到早餐了 开始干活。。。");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally {
                lock.unlock();
            }
        },"吃早餐干活").start();

        sleep(1);
        sendBreakfast();
        sleep(1);
        sendCigarette();
    }

    public static void sendBreakfast(){
        lock.lock();
        try {
            log.debug("送早餐来了");
            hasBreakfast = true;
            waitBreakfast.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void sendCigarette(){
        lock.lock();
        try {
            log.debug("送烟来了");
            hasCigrette = true;
            waitCigarette.signal();
        } finally {
            lock.unlock();
        }
    }
}
