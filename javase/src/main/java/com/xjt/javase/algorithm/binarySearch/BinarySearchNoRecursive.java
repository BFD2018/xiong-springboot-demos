package com.xjt.javase.algorithm.binarySearch;

public class BinarySearchNoRecursive {
    public static void main(String[] args) {
        int[] arr = {1,3, 8, 10, 11, 67, 100};
        int i = binarySearch(arr, -100);
        System.out.println(i);
    }

    /**
     * 二分查找非递归实现方法
     * @param arr  有序的数组
     * @param target 查找目标
     * @return 返回查找到的对象下标，没找到返回 -1
     */
    public static int binarySearch(int[] arr,int target){
        int left = 0;
        int right = arr.length - 1;

        while (left <= right){
            int mid = (left + right) / 2;

            if(arr[mid] == target){
                return mid;
            }else if(arr[mid] > target){
                right = mid - 1;
            }else if(arr[mid] < target){
                left = mid + 1;
            }
        }

        return -1;
    }
}
