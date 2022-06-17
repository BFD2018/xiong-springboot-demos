package com.xjt.javase.dataStructure.sort;

import cn.hutool.core.date.DateTime;

import java.util.Arrays;

public class InsertSortDemo {
    public static void main(String[] args) {
        //int[] arr = {101, 34, 119, 1, -1, 89};

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }

        long start = new DateTime().getTime();

        insertSort(arr);

        long end = new DateTime().getTime();
        System.out.println("执行完80000个数排序耗时："+(end-start));      //878ms

        //System.out.println("排序之后的arr:"+ Arrays.toString(arr));
    }

    public static void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int idx = i-1;
            int val = arr[i];
            while (idx >=0 && arr[idx] > val){
                arr[idx + 1] = arr[idx];
                idx--;
            }
            if((idx + 1) != i ){
                arr[idx + 1] = val;
            }
        }
    }
}
