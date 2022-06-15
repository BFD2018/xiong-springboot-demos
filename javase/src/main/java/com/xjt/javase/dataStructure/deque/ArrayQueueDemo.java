package com.xjt.javase.dataStructure.deque;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");

            key = scanner.next().charAt(0);     //接收一个字符
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~");
    }
}

class ArrayQueue{
    private int maxSize;    // 表示数组的最大容量
    private int front; // 队列头
    private int rear; // 队列尾
    private int[] arr; // 用于存放数据, 模拟队列

    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        this.front = -1;
        this.rear = -1;
        arr = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull(){
        return rear == maxSize - 1;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n){
        if(isFull()){
            throw new RuntimeException("队列已满 不能添加了。。。");
        }
        rear++;
        arr[rear] = n;
    }

    //出队列
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空的~~~");
        }
        front++;
        return arr[front];
    }

    //显示队列所有数据
    public void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空的~~~");
        }
        for (int i = 0; i < maxSize; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    // 显示队列的头数据， 注意不是取出数据
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空的~~~");
        }
        return arr[front+1];
    }
}
