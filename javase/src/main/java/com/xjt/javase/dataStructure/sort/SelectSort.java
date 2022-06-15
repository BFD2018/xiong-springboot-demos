package com.xjt.javase.dataStructure.sort;

import cn.hutool.core.date.DateTime;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        //从小到大排序
//        int arr[] = {3, 9, -1, 10, 20};
        //int arr[] = {1,3,2,8,7,4,6,5};

        //测试8w个数据的排序用时
        int[] ints = new int[80000];
        for (int i = 0; i < 80000; i++) {
            ints[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }
        long start = new DateTime().getTime();      //毫秒

        SelectSortFunc1(ints);        //80000个数排序耗时 15922ms
//        SelectSortFunc2(ints);      //80000个数排序耗时 5502ms

        long end = new DateTime().getTime();      //毫秒
        System.out.println("选择排序消耗的时间为："+(end-start));

    }


    public static void SelectSortFunc1(int arr[]){
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j] < min){
                    min = arr[j];

                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    //优化
    public static void SelectSortFunc2(int arr[]){
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j] < min){
                    min = arr[j];
                    minIndex = j;
                }
            }

            //一轮遍历后将最小值 与i位置数 交换位置
            if(minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
