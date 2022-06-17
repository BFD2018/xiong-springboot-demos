package com.xjt.javase.dataStructure.sort;

import cn.hutool.core.date.DateTime;

import java.util.Arrays;

public class MergeSortDemo {
    public static void main(String[] args) {
//        int arr[] = {8, 4, 5, 7, 1, 3, 6, 2};
//        int temp[] = new int[arr.length]; //归并排序需要一个额外空间
//        mergeSort(arr, 0, arr.length - 1, temp);
//        System.out.println("排序之后的arr:" + Arrays.toString(arr));

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }

        long start = new DateTime().getTime();
        int temp[] = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);

        long end = new DateTime().getTime();
        System.out.println("执行完80000个数排序耗时："+(end-start));      //14ms
    }

    //分+合方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并的方法
     *
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边有序序列的重点索引
     * @param temp  做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;   // 初始化i, 左边有序序列的初始索引
        int j = mid + 1;  //初始化j, 右边有序序列的初始索引
        int t = 0;  // 指向temp数组的当前索引

        //(一)
        //先把左右两边(有序)的数据按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {
            //如果左边有序序列的当前元素小于右边有序序列的当前元素，即将左边的当前元素填充到temp数组索引为t的位置
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {  //如果右边有序序列的当前元素小于左边有序序列的当前元素，即将右边的当前元素填充到temp数组索引为t的位置
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        //(二)
        //当 i>mid 或者 j>right 时，把另一边剩余的元素依次填充到temp中
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }

        //（三）
        //将temp中的元素拷贝到arr
        //注意，并不是每次都拷贝所有
        t = 0;    //将temp当前索引指针 放到初始位置
        int tempLeft = left;
        //第一次合并 tempLeft = 0 , right = 1
        //第二次合并 tempLeft = 2  right = 3
        //第三次合并 tempLeft = 0  right = 3
        // ...
        //最后一次 tempLeft = 0  right = 7
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }

}
