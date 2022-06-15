package com.xjt.javase.dataStructure.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int arr[] = {1,3,2,8,7,4,6,5};

        InsertSortFunc(arr);



    }

    public static void InsertSortFunc(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if(arr[j] < arr[j-1]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }else{
                    break;
                }
            }

            System.out.printf("第【%d】轮排序后的数组为：%s",i,(Arrays.toString(arr)));
            System.out.println();
        }

        System.out.println("排序后的数组为："+(Arrays.toString(arr)));
    }
}
