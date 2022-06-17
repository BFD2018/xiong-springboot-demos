package com.xjt.javase.juc;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.LockSupport;

import static com.xjt.javase.juc.utils.MySleeper.sleep;

@Slf4j(topic = "c.Sync")
public class CreateThread {
    private static int R = 0;

    public static void main(String[] args) throws InterruptedException {
        /*Thread t1 = new Thread() {
            public void run() {
                System.out.println("hello thread");
            }
        };

        t1.start();
        System.out.println(t1.getName());
        System.out.println("当前主线程是："+ Thread.currentThread().getName());*/

        /*Thread t1 = new Thread(() -> {
            log.debug("开始");
            sleep(1);
            log.debug("结束");
            R = 10;
        },"t1");
        t1.start();

        t1.join();
        log.debug(Thread.currentThread().getName());*/


        /*Runnable runnable = new Runnable() {
            @Override
            public void run(){
                // 要执行的任务
                System.out.println("第二种方式创建线程：Runnable接口");
            }
        };
        // 创建线程对象
        Thread t = new Thread( runnable );
        // 启动线程
        t.start();*/

        /*FutureTask futureTask = new FutureTask<Integer>(() ->{
            System.out.println("执行线程任务");
            return 100;
        });
        try {
            Thread t3 = new Thread(futureTask, "t3");
            t3.start();

            Object o = futureTask.get();
            System.out.println(o);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/


        //test01();

//        testJoin();

//        testInterruptSleep();

//        testInteruptPark();

//        testDaemon();

        testSellTicket();
    }

    public static void test01() {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("running...");
                File file = new File("D:\\CodeLearning\\JavaLearning\\JavaProjects\\xjt-spring-framework\\javase\\src\\main\\resources\\logback.xml");
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader(file));
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
                    String str;
                    StringBuffer Str = new StringBuffer("");//创建可变字符串的格式,不可以直接声明,直接声明会产生空指针异常;
                    while ((str = br.readLine()) != null) {
                        Str.append(str);//将每次读取的内容添加到可变字符串的后面;
                    }
                    Str.reverse();      //降可变字符串逆值;
                    str = Str.toString();     //将可变字符串转换成普通字符串,因write()只能输出字符串类型的参数
                    bw.write(str);
                    bw.flush();
                    bw.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        };

        t1.start();
        log.debug("do other things...");
    }

    public static void testJoin() throws InterruptedException {
        log.debug("开始");

        Thread t1 = new Thread(() -> {
            log.debug("开始");
            sleep(1);
            log.debug("结束");
            R = 10;
        });
        t1.start();
//        sleep(2);         //如果主线程sleep时间大于 t1线程 下面那行打印的R 是 10
        //sleep(0.2);     //否则 下面那行打印的R 是 0

        t1.join();      //等t1线程结束后 主线程再往下执行
        log.debug("结果为:{}", R);
        log.debug("结束");
    }

    public static void testInterruptSleep() {
        Thread t1 = new Thread(() -> {
            log.debug(" sleep前-当前线程状态: {}", Thread.currentThread().getState());
            sleep(1);
            log.debug(" sleep后-当前线程状态: {}", Thread.currentThread().getState());

        }, "t1");
        t1.start();

        sleep(0.5);
        t1.interrupt();
        log.debug(" 打断状态: {}", t1.isInterrupted());
    }

    private static void testInteruptPark() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("park...");
            LockSupport.park();
            log.debug("unpark...");
            log.debug("打断状态：{}", Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();

        sleep(0.5);
        t1.interrupt();
    }

    private static void testDaemon() throws InterruptedException {
        /*需求：刘关张桃园三结义：不求同年同月同日生但求同年同月同日死，刘备作为大哥 积劳而死，
         作为他的结拜兄弟 关张二人也要践行诺言 一起陪大哥上路（虽然他们阳寿还未尽）*/
        Thread liubei = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                log.debug("刘备的寿命还有i=" + i);
            }
        }, "刘备");

        Thread guanyu = new Thread(() -> {
            for (int i = 0; i < 80; i++) {
                log.debug("关羽的寿命还有i=" + i);
            }
        }, "关羽");

        Thread zhangfei = new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                log.debug("张飞的寿命还有i=" + i);
            }
        }, "张飞");

        guanyu.setDaemon(true);
        zhangfei.setDaemon(true);
        liubei.start();
        guanyu.start();
        zhangfei.start();

        liubei.join();
        log.debug("主线程结束...");
    }

    private static void testSellTicket() {
        /*MyThread2 mt=new MyThread2();
        new Thread(mt).start();
        new Thread(mt).start();*/

        MyThread3 mt=new MyThread3();
        new Thread(mt).start();
        new Thread(mt).start();

    }

    static class MyThread3 implements Runnable {
        private int ticket = 5;

        public void run() {
            while (true) {
                if (ticket < 0) {
                    break;
                }
                log.debug("Runnable ticket = " + ticket--);
            }
        }
    }
}

@Slf4j(topic = "c.MyThread2")
class MyThread2 implements Runnable {
    private int ticket = 5;

    public void run() {
        while (true) {
            log.debug("Runnable ticket = " + ticket--);
            if (ticket < 0) {
                break;
            }
        }
    }
}
