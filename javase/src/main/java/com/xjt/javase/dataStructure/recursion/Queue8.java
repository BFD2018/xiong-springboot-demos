package com.xjt.javase.dataStructure.recursion;

public class Queue8 {
    //定义一个max表示共有多少个皇后
    private int max = 8;
    //定义数组array, 保存皇后放置位置的结果,比如 arr = {0 , 4, 7, 5, 2, 6, 1, 3}
    int[] array = new int[max];
    private static int count = 0;
    //判断冲突的次数
    private static int judgeCount = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d解法\n", count);
        System.out.printf("一共判断冲突的次数%d次", judgeCount); // 1.5w
    }

    //编写一个方法，放置第n个皇后
    //特别注意： check 是 每一次递归时，进入到check中都有  for(int i = 0; i < max; i++)，因此会有回溯
    private void check(int n){
        if(n == max){   //n = 8 , 其实8个皇后就既然放好
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后 n , 放到该行的第1列
            array[n] = i;

            //判断当放置第n个皇后到第i列时 是否冲突
            if(judge(n)){   //不冲突
                //接着放第n+1个皇后 即开始递归
                check(n+1);
            }
            //如果冲突 继续将第n个皇后移动到本行的下一列
        }
    }

    /**
     * //查看当我们放置第n个皇后, 就去检测该皇后是否和前面已经摆放的皇后冲突
     * @param n 表示第n个皇后
     * @return  false 表示冲突
     */
    private boolean judge(int n){
        judgeCount++;
        for (int i = 0; i < n; i++) {
            /**
             * 1、array[i] == array[n] 表示在同一列
             * 2、Max.abs
             * 3、i=0 放在第一行  i=2 放在第二行  因此行 是不会冲突的
             */
            if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }

    //可以将皇后摆放的位置输出
    public void print(){
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
