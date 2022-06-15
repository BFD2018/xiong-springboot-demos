package com.xjt.javase.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "c.CreateThread")
public class CreateThread {
    public static int r = 0;
    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) throws InterruptedException {
        //方式1、继承Thread 重写run方法
        /*Thread t1 = new MyThread("t1");
        Thread t2 = new MyThread();
        t2.setName("t2");

        //设置优先级
        t1.setPriority(10);

        t1.start();
        t2.start();

        System.out.println(Thread.currentThread().getName());*/

        //方式2、实现Runnable接口 重写run方法
        /*MyRunnable mr = new MyRunnable();
        Thread tt = new Thread(mr, "tt");
        tt.start();

        System.out.println(Thread.currentThread().getName());*/


//        testSleepYield();

//        test1();
//        test2();
//        test3();

//        deadLock();

        testWaitSleep();


    }

    public static void sleep(int t){
        try {
            log.info("sleep。。。{}",t);
            TimeUnit.SECONDS.sleep(t);
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void testSleepYield(){
        Runnable task1 = () -> {
            int count = 0;
            for (;;) {
                System.out.println("---->1 " + count++);
            }
        };
        Runnable task2 = () -> {
            int count = 0;
            for (;;) {
//                Thread.yield();
                System.out.println("              ---->2 " + count++);
            }
        };
        Thread t1 = new Thread(task1, "t1");
        Thread t2 = new Thread(task2, "t2");
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
    }

    private static void test1() throws InterruptedException {
        log.debug("开始");
        Thread t1 = new Thread(() -> {
            log.debug("开始");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("结束");
            r = 10;
        });
        t1.start();
        t1.join();      // 等待线程t1的结束
        log.debug("结果为:{}", r);
        log.debug("结束");
    }

    private static void test2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            sleep(1);
            CreateThread.r = 10;
        });
        Thread t2 = new Thread(() -> {
            sleep(2);
            r = 20;
        });
        long start = System.currentTimeMillis();
        t2.start();
        t1.start();
        t2.join();
        t1.join();

        long end = System.currentTimeMillis();
        log.debug("r: {} cost: {}", r, end - start);
    }

    private static void test3() throws InterruptedException {
        log.info("这是主线程。。。");
        Thread t2 = new Thread(()->{
            while(true) {
                Thread current = Thread.currentThread();
                boolean interrupted = current.isInterrupted();
                if(interrupted) {
                    log.debug(" 打断状态: {}", interrupted);
                    break;
                }
            }
        }, "t2");
        t2.start();
        sleep(1);
        t2.interrupt();
    }

    private static void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    try { Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        System.out.println("1");
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B) {
                    synchronized (A) {
                        System.out.println("2");
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }

    public static void testWaitSleep() throws InterruptedException {
        synchronized(A.getClass()){
            for (int i = 0; i < 5; i++) {
                A.getClass().wait(1000);
                System.out.println(i);
            }
        }

    }
}

class MyThread extends Thread{
    public MyThread() {
    }

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("线程："+Thread.currentThread().getName() + " i=="+i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        System.out.println("run main method...");
    }
}


