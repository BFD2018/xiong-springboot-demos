package com.xjt.javase.juc;

import com.xjt.javase.juc.utils.Sleeper;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static com.xjt.javase.juc.utils.Sleeper.sleep;

@Slf4j(topic = "c.TestReentrantLock")
public class TestReentrantLock {
    public static void main(String[] args) {
//        testTryLock01();

        testPhilosopherEatting();
    }

    public static void testTryLock01(){
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            log.debug("启动。。。");
            try {
                if (!lock.tryLock(3, TimeUnit.SECONDS)) {
                    log.debug("获取等待 3s 后失败，返回");
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                log.debug("获得了锁");
            } finally {
                lock.unlock();
            }
        }, "t1");

        lock.lock();
        log.debug("获得了锁");
        t1.start();
        try {
            sleep(2);
            log.debug("释放了锁");
        } finally {
            lock.unlock();
        }

    }

    //解决哲学家就餐问题
    public static void testPhilosopherEatting(){
        MyChopstick c1 = new MyChopstick("1");
        MyChopstick c2 = new MyChopstick("2");
        MyChopstick c3 = new MyChopstick("3");
        MyChopstick c4 = new MyChopstick("4");
        MyChopstick c5 = new MyChopstick("5");
        new PhilosopherEat("苏格拉底", c1, c2).start();
        new PhilosopherEat("柏拉图", c2, c3).start();
        new PhilosopherEat("亚里士多德", c3, c4).start();
        new PhilosopherEat("赫拉克利特", c4, c5).start();
        new PhilosopherEat("阿基米德", c1, c5).start();
    }




}

class MyChopstick extends ReentrantLock{
    String name;

    public MyChopstick(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "筷子{" + name + '}';
    }
}
//哲学家---线程 ，筷子---共享资源
@Slf4j(topic = "c.PhilosopherEat")
class PhilosopherEat extends Thread{
    MyChopstick left;
    MyChopstick right;

    public PhilosopherEat(String name,MyChopstick left, MyChopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    private void eat() {
        log.debug("eating...");
        Sleeper.sleep(1);
    }

    @Override
    public void run() {
        while (true){
            //尝试获得左手筷子
            if(left.tryLock()){
                try{
                    if(right.tryLock()){
                        try {
                            eat();
                        }finally {
                            right.unlock();
                        }
                    }
                }finally {
                    left.unlock();
                }
            }
        }
    }
}
