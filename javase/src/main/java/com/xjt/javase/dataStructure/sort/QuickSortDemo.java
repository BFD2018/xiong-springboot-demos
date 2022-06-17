package com.xjt.javase.dataStructure.sort;

import cn.hutool.core.date.DateTime;

import java.util.Arrays;

public class QuickSortDemo {
    public static void main(String[] args) {
//        int[] arr = {-9,78,0,23,-567,70, -1,900, 4561};
//        quickSort(arr,0,arr.length-1);
//        System.out.println("排序之后的arr:"+ Arrays.toString(arr));

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }

        long start = new DateTime().getTime();

        quickSort(arr,0,arr.length-1);

        long end = new DateTime().getTime();
        System.out.println("执行完80000个数排序耗时："+(end-start));      //50ms
    }

    public static void quickSort(int[] arr,int left,int right){
        //定义两个指针 分别指向数组的第一个 后最后一个位置
        int l = left;
        int r = right;
        int pivot = arr[(left + right) /2];

        //这个while循环的目的是将 比pivot小的值移到到左右 比pivot大的值移动到右边
        //while循环结束后 所有比pivot小的值都在左边，比pivot大的值都在右边
        while (l < r){
            //向右移动指针l ，直到数找到 大于pivot
            while (arr[l] < pivot){
                l++;
            }
            //向左移动指针r ，直到数找到 小于pivot
            while (arr[r] > pivot){
                r--;
            }

            //如果l >= r说明pivot 的左右两的值，已经按照左边全部是
            //小于等于pivot值，右边全部是大于等于pivot值
            if(l >= r){
                break;
            }

            //交换这两个值
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果l指针 遇到值等于pivot 则向右移动一个
            if(arr[l] == pivot){
                r--;
            }

            //如果r指针 遇到值等于pivot 则向左移动一个
            if(arr[r] == pivot){
                l++;
            }
        }
        //当l == r 时 l+1  r-1
        if(l == r){
            l++;
            r--;
        }

        //再对pivot 左右两边的数 继续递归执行
        if(left < r){
            quickSort(arr,left,r);
        }

        if(l < right){
            quickSort(arr,l,right);
        }
    }
}
