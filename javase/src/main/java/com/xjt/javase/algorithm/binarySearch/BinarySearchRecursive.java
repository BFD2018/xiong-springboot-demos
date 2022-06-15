package com.xjt.javase.algorithm.binarySearch;

public class BinarySearchRecursive {
    public static void main(String[] args) {
        int[] arr = {1,3, 8, 10, 11, 67, 100};
        int i = binarySearchRecur(arr, 0,arr.length-1,11);
        System.out.println(i);
    }


    /**
     * 递归方式实现二分查找
     * @param arr 有序数组
     * @param left 左下标
     * @param right 右下标
     * @param target 目标值
     * @return 找到了返回下标，没找到返回 -1
     */
    public static int binarySearchRecur(int[] arr,int left,int right,int target){
        if(left > right){
            return -1;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if(target == midVal){
            return mid;
        }
        else if(target > midVal){
            return binarySearchRecur(arr,mid + 1,right,target);
        }
        else{
            return binarySearchRecur(arr,left,mid - 1,target);
        }

    }

}
