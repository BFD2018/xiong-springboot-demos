package com.xjt.javase.dataStructure.tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序排序
        int arr[] = {4, 6, 8, 5, 9};

        int[] ret = heapSortFunc(arr);
        System.out.println(Arrays.toString(ret));
    }

    //堆排序方法
    public static int[] heapSortFunc(int arr[]) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] newarr = Arrays.copyOf(arr, arr.length);

        int len = newarr.length;

        for (int i = (int)Math.floor(len / 2); i >=0 ; i--) {
            adjustHeap(newarr,i,len);
        }

        for (int i = len-1; i >0 ; i--) {
            swap(newarr,0,i);
            len--;
            adjustHeap(newarr,0,len);
        }

        return newarr;

    }

    //将一个数组(二叉树), 调整成一个大顶堆
    /**
     * 功能：完成将以 i 为非叶子节点的树调整为大顶堆
     * 举例  int arr[] = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap => 得到 {4, 9, 8, 5, 6}
     * 如果我们再次调用  adjustHeap 传入的是 i = 0 => 得到 {4, 9, 8, 5, 6} => {9,6,8,5, 4}
     * @param arr   待调整的数组
     * @param i 表示非叶子结点在数组中索引
     * @param lenght 表示对多少个元素继续调整， length 是在逐渐的减少
     */
    public  static void adjustHeap(int arr[], int i, int lenght) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int largest = i;

        if(left < lenght && arr[left] > arr[largest]){
            largest = left;
        }

        if(right < lenght && arr[right] > arr[largest]){
            largest = right;
        }

        if(largest != i){
            swap(arr,i,largest);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
