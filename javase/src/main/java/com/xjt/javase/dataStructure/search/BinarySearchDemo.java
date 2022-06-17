package com.xjt.javase.dataStructure.search;

import java.util.ArrayList;
import java.util.List;

//注意：使用二分查找的前提是 该数组是有序的.
public class BinarySearchDemo {
    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 , 11, 12, 13,14,15,16,17,18,19,20 };
        int i = binarySearchOne(arr, 0, arr.length - 1, 5);
        System.out.println(i);

        int[] ls = {1,8, 10, 89, 1000, 1000,1000,1234};
        List<Integer> integers = binarySearchAll(ls, 0, ls.length - 1, 1000);
        System.out.println("数组中找到所有目标值："+ integers.toString());
    }

    /**
     * 在数组arr中找指定的值findVal  只找一个
     * @param arr 数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal   要查找的值
     * @return 如果找到就返回下标，如果没有找到，就返回 -1
     */
    public static int binarySearchOne(int[] arr,int left,int right,int findVal){
        // 当 left > right 时，说明递归整个数组，但是没有找到
        if(left > right){
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if(findVal > midVal){
            return binarySearchOne(arr,mid+1,right,findVal);
        }else if(findVal < midVal){
            return binarySearchOne(arr,left,mid-1,findVal);
        }else{
            return mid;
        }
    }

    /**
     * 课后思考题： {1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中，
     * 	    有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000
     * 思路分析
     * 	 * 1. 在找到mid 索引值，不要马上返回
     * 	 * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
     * 	 * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
     * 	 * 4. 将Arraylist返回
     * @param arr 数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal   要查找的值
     * @return 返回所有找到的值索引组成的集合
     */
    public static List<Integer> binarySearchAll(int[] arr,int left,int right,int findVal){
// 当 left > right 时，说明递归整个数组，但是没有找到
        if(left > right){
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if(findVal > midVal){
            return binarySearchAll(arr,mid+1,right,findVal);
        }else if(findVal < midVal){
            return binarySearchAll(arr,left,mid-1,findVal);
        }else{
            List<Integer> indexList = new ArrayList<Integer>();

            //向mid 索引值的左边扫描，将所有等于findVal的元素的下标，加入到集合ArrayList
            int temp = mid -1;
            while (true){
                if(temp < 0 || arr[temp] != findVal){
                    break;
                }else{
                    indexList.add(temp);
                    temp--;
                }
            }

            //记得把当前的位置放入集合中
            indexList.add(mid);

            //向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            temp = mid + 1;
            while (true){
                if(temp > arr.length-1 || arr[temp] != findVal){
                    break;
                }else{
                    indexList.add(temp);
                    temp++;
                }
            }

            return indexList;
        }
    }
}
