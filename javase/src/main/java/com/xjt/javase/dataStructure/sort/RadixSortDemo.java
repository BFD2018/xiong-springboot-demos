package com.xjt.javase.dataStructure.sort;

import cn.hutool.core.date.DateTime;

import java.util.Arrays;

public class RadixSortDemo {
    public static void main(String[] args) {
//        int arr[] = {53, 3, 542, 748, 14, 214};
////        radixSortDeduce(arr);
//
//        radixSort(arr);
//        System.out.println("排序之后的arr:" + Arrays.toString(arr));

        //8000w个数据验证 基数排序的速度
        // 80000000 * 11 * 4 / 1024 / 1024 / 1024 =3.3G
        // 内存溢出 java.lang.OutOfMemoryError
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000); // 生成一个[0, 8000000) 数
        }
        long start = new DateTime().getTime();
        int temp[] = new int[arr.length];
        radixSort(arr);
        long end = new DateTime().getTime();
        System.out.println("执行完80000个数排序耗时："+(end-start));      //18ms
    }

    public static void radixSort(int[] arr) {
        //1、找到arr中最大数的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();

        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCounts = new int[10];

        for (int k = 0, n = 1; k < maxLength; k++, n *= 10) {
            //2、(针对每个元素的对应位进行排序处理)， 第一次是个位，第二次是十位，第三次是百位..
            //根据位数的值 放入不同的桶中
            for (int i = 0; i < arr.length; i++) {
                //取出每个元素的个位的值
                int ditgitOfElement = arr[i] / n % 10;
                //放入到对应的桶中
                bucket[ditgitOfElement][bucketElementCounts[ditgitOfElement]] = arr[i];
                bucketElementCounts[ditgitOfElement]++;
            }
            //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
            int index = 0;
            //遍历每一个桶，并将桶中是数据，放入到原数组
            for (int i = 0; i < bucketElementCounts.length; i++) {      //遍历10个桶
                //如果桶中，有数据，我们才放入到原数组
                if (bucketElementCounts[i] != 0) {
                    //循环该桶即第i个桶, 放入
                    for (int j = 0; j < bucketElementCounts[i]; j++) {
                        arr[index] = bucket[i][j];
                        index++;
                    }
                }

                //第k+1轮处理后，需要将每个 bucketElementCounts[k] = 0 ！！！！
                bucketElementCounts[i] = 0;
            }

            //System.out.println("第" + (k + 1) + "轮，排序处理 arr =" + Arrays.toString(arr));
        }
    }

    /**
     * 基数排序的推导过程
     *
     * @param arr 要排序的数组
     */
    public static void radixSortDeduce(int[] arr) {
        //定义一个二维数组，表示10个桶, 每个桶就是一个一维数组
        //说明
        //1. 二维数组包含10个一维数组
        //2. 为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为arr.length
        //3. 要明确，基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际存放了多少个数据,我们定义一个一维数组来记录各个桶的每次放入的数据个数
        //可以这里理解
        //比如：bucketElementCounts[0] , 记录的就是  bucket[0] 桶的放入数据个数
        int[] bucketElementCounts = new int[10];

        //第1轮(针对每个元素的个位进行排序处理)
        for (int i = 0; i < arr.length; i++) {
            //取出每个元素的个位的值
            int ditgitOfElement = arr[i] / 1 % 10;
            //放入到对应的桶中
            bucket[ditgitOfElement][bucketElementCounts[ditgitOfElement]] = arr[i];
            bucketElementCounts[ditgitOfElement]++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
        int index = 0;
        //遍历每一桶，并将桶中是数据，放入到原数组
        for (int i = 0; i < bucketElementCounts.length; i++) {      //遍历10个桶
            //如果桶中，有数据，我们才放入到原数组
            if (bucketElementCounts[i] != 0) {
                //循环该桶即第i个桶, 放入
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    arr[index] = bucket[i][j];
                    index++;
                }
            }

            //处理后，需要将每个 bucketElementCounts[i] = 0 ！！！！
            bucketElementCounts[i] = 0;
        }
        System.out.println("第1轮，对个位的排序处理 arr =" + Arrays.toString(arr));


        //第2轮(针对每个元素的个位进行排序处理)
        for (int i = 0; i < arr.length; i++) {
            //取出每个元素的十位的值
            int ditgitOfElement = arr[i] / 10 % 10;
            //放入到对应的桶中
            bucket[ditgitOfElement][bucketElementCounts[ditgitOfElement]] = arr[i];
            bucketElementCounts[ditgitOfElement]++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
        index = 0;
        //遍历每一桶，并将桶中是数据，放入到原数组
        for (int i = 0; i < bucketElementCounts.length; i++) {      //遍历10个桶
            //如果桶中，有数据，我们才放入到原数组
            if (bucketElementCounts[i] != 0) {
                //循环该桶即第i个桶, 放入
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    arr[index] = bucket[i][j];
                    index++;
                }
            }

            //处理后，需要将每个 bucketElementCounts[i] = 0 ！！！！
            bucketElementCounts[i] = 0;
        }
        System.out.println("第2轮，对十位的排序处理 arr =" + Arrays.toString(arr));

        //第3轮(针对每个元素的个位进行排序处理)
        for (int i = 0; i < arr.length; i++) {
            //取出每个元素的百位的值
            int ditgitOfElement = arr[i] / 100 % 10;
            //放入到对应的桶中
            bucket[ditgitOfElement][bucketElementCounts[ditgitOfElement]] = arr[i];
            bucketElementCounts[ditgitOfElement]++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
        index = 0;
        //遍历每一桶，并将桶中是数据，放入到原数组
        for (int i = 0; i < bucketElementCounts.length; i++) {      //遍历10个桶
            //如果桶中，有数据，我们才放入到原数组
            if (bucketElementCounts[i] != 0) {
                //循环该桶即第i个桶, 放入
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    arr[index] = bucket[i][j];
                    index++;
                }
            }

            //处理后，需要将每个 bucketElementCounts[i] = 0 ！！！！
            bucketElementCounts[i] = 0;
        }
        System.out.println("第3轮，对百位的排序处理 arr =" + Arrays.toString(arr));
    }
}
