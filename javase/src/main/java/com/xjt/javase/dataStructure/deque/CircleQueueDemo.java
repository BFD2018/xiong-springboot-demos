package com.xjt.javase.dataStructure.deque;

import org.junit.Test;

import java.util.Scanner;

public class CircleQueueDemo {
    public static void main(String[] args) {
        //测试一把
        System.out.println("测试数组模拟环形队列的案例~~~");

        // 创建一个环形队列
        CircleQueue queue = new CircleQueue(4);     //说明设置4, 其队列的有效数据最大是3
        char key = ' '; // 接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        // 输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);// 接收一个字符
            switch (key) {
                case 's':
                    try {
                        queue.showQueue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    try {
                        queue.addQueue(value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'g': // 取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': // 查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': // 退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }

    @Test
    public void test01(){
        System.out.println(16%8);
    }
}

class CircleQueue{
    private int maxSize; // 表示数组的最大容量
    private int front;  // 指向队列的第一个元素 初始值 = 0
    //rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
    private int rear;   // 队列尾
    private int[] arr; // 该数据用于存放数据, 模拟队列

    public CircleQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    // 判断队列是否满
    public boolean isFull(){
        return size() == (maxSize-1);
    }

    // 判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    // 添加数据到队列
    public void addQueue(int n){
        if(isFull()){
            throw new RuntimeException("队列已满~~~");
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    // 获取队列的数据, 出队列
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("当前队列为空~~~");
        }
        // 这里需要分析出 front是指向队列的第一个元素
        // 1. 先把 front 对应的值保留到一个临时变量
        // 2. 将 front 后移, 考虑取模
        // 3. 将临时保存的变量返回
        int value = arr[front];
        front = (front + 1)%maxSize;
        return value;
    }

    // 显示队列的所有数据
    public void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("当前队列为空~~~");
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }

    // 求出当前队列有效数据的个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    // 显示队列的头数据， 注意不是取出数据
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("当前队列为空~~~");
        }
        return arr[front];
    }
}
