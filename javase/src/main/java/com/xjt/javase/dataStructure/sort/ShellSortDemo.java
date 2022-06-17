package com.xjt.javase.dataStructure.sort;

import cn.hutool.core.date.DateTime;

import java.util.Arrays;

public class ShellSortDemo {
    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//
//        shellSort1(arr);
//
//        System.out.println(Arrays.toString(arr));


        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }

        long start = new DateTime().getTime();

        shellSort1(arr);

        long end = new DateTime().getTime();
        System.out.println("执行完80000个数排序耗时："+(end-start));      //32ms
    }

    // 使用逐步推导的方式来编写希尔排序
    // 希尔排序时， 对有序序列在插入时采用交换法,
    // 思路(算法) ===> 代码
    public static void shellSort(int[] arr) {
        int temp = 0;
        // 根据前面的逐步分析，使用循环处理
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                // 遍历各组中所有的元素(共gap组，每组有个元素), 步长gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素大于加上步长后的那个元素，说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }


    //对交换式的希尔排序进行优化->移位法
    public static void shellSort1(int[] arr) {
        int len = arr.length;

        for (int gap = len / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < len; i++) {
                int temp = arr[i];
                int j = i;
                if (arr[j - gap] > temp) {
                    while ((j - gap) >= 0 && (arr[j - gap] > temp)) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }

                    if (j != i) {
                        arr[j] = temp;
                    }
                }
            }
        }
    }
}
