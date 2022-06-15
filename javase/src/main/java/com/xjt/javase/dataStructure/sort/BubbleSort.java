package com.xjt.javase.dataStructure.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        //从小到大排序
        int arr[] = {3, 9, -1, 10, 20};

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println("冒泡排序后");
        System.out.println(Arrays.toString(arr));


        /* 思路分析

        //第一次
        for (int i = 0; i < arr.length-1; i++) {
            if(arr[i] > arr[i+1]){
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }

        System.out.println("第一轮遍历后");
        System.out.println(Arrays.toString(arr));

        //第二次
        for (int i = 0; i < arr.length-1-1; i++) {
            if(arr[i] > arr[i+1]){
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }

        System.out.println("第二轮遍历后");
        System.out.println(Arrays.toString(arr));

        //第三次
        for (int i = 0; i < arr.length-1-2; i++) {
            if(arr[i] > arr[i+1]){
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }

        System.out.println("第三轮遍历后");
        System.out.println(Arrays.toString(arr));

        //第四次
        for (int i = 0; i < arr.length-1-3; i++) {
            if(arr[i] > arr[i+1]){
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
            }
        }

        System.out.println("第四轮遍历后");
        System.out.println(Arrays.toString(arr));*/
    }
}
