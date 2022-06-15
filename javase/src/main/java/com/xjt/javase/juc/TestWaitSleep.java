package com.xjt.javase.juc;

public class TestWaitSleep {
    public static void main(String[] args) {
        MyPrinter pri = new MyPrinter();

        new Thread(() -> {
            while (true) {
                try {
                    pri.print1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                try {
                    pri.print2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();
    }
}

class MyPrinter {
    int flag = 1;

    public void print1() throws InterruptedException {
        synchronized (MyPrinter.class) {
            if (flag != 1) {
                /*
                 * flag=2 print1不执行
                 * wait() 如果使用空参（点死穴） 必须由一个notify方法对其唤醒
                 * */
                MyPrinter.class.wait();
            } else {
                System.out.println("传智播客");
                flag = 2;
                MyPrinter.class.notify();
            }
        }
    }

    public void print2() throws InterruptedException {
        synchronized (MyPrinter.class) {
            if (flag != 2) {
                /*
                 * flag=1 print2不执行
                 * wait() 如果使用空参（点死穴） 必须由一个notify方法对其唤醒
                 * */
                MyPrinter.class.wait();
            } else {
                System.out.println("黑马程序员");
                flag = 1;
                MyPrinter.class.notify();
            }
        }
    }
}
