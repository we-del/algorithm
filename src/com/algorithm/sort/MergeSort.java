package com.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * ClassName: mergeSort
 * Description:
 * date: 2022/2/17 18:41
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class MergeSort {
    public static void main(String[] args) {
        //int[] arr = {38, 64, 74, 28, 82, 8, 59, 52, 58, 73, 56, 39};
//        for (int i = 0; i < 10; i++) {
//
//            int[] arr = RandomArray.randomArray(15);
//            int[] temp = new int[arr.length];
//            mergeSort(arr, 0, arr.length - 1, temp);
//            System.out.println(Arrays.toString(arr));
//        }


//        int[] arr ={8,4,5,7,1,3,6,2};
//        int[] temp = new int[arr.length];
//        mergeSort(arr, 0, arr.length - 1, temp);
//        System.out.println(Arrays.toString(arr));

        int[] arr = RandomArray.randomArray(800000);
        int[] temp = new int[arr.length];
        speedTest(arr,temp);
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) { // 当left 小于 right 表示可以继续二分
            int mid = (right - left) / 2 + left;    // 取本组的中位点
            mergeSort(arr, left, mid, temp); // 以本组中位点开始左边部分二分
            mergeSort(arr, mid + 1, right, temp); // 以本组中位点开始右边二分
            merge(arr, left, mid, right, temp); // 开始对每组的二分进行排序
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int l = left; // 开始排序的左索引
        int r = mid + 1; // 开始排序的右索引
        int t = 0;  // 临时数组索引初始化

        while (l <= mid && r <= right) {
            //  l <= mid && r<=right 表示 左边和右边都有数据没遍历完

            // 两边的数据进行比较 谁小 则将先存放到临时数组中
            if (arr[l] < arr[r]) {
                temp[t++] = arr[l++];
            } else {
                temp[t++] = arr[r++];
            }
        }

        // 再两边数据比较完后 最终会有一边的数据还有数据，一边没有数据，此时我们需要再进行一次判断
        while (l <= mid) {
            temp[t++] = arr[l++];
        }
        while (r <= right) {
            temp[t++] = arr[r++];
        }

        // 此时temp 已经 存储着局部有序的数据，这时即可存入原数据的局部
        t = 0; // 重置 t 到首索引 方便存入数据(此时t为temp的空索引位置)
        
        int cur = left; // cur拿到当前分组(二分)的首位置
        // 当 cur <=right 表示此时 再该分组的有效索引位置上，否则就结束
        while(cur <=right){
            // 将 对arr局部有序的temp数组的数据传入给arr的指定位置
            arr[cur++] = temp[t++];
        }

    }
    
    private static void speedTest(int[] arr,int[] temp){
        long l = System.currentTimeMillis();
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println("消耗:"+(System.currentTimeMillis()-l));
       // System.out.println(Arrays.toString(arr));
    }
}























//    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
//        int i = left;
//        int j = mid + 1;
//        int t = 0;
//        while (i <= mid && j <= right) {
//            if (arr[i] <= arr[j]) {
//                temp[t++] = arr[i++];
//            } else {
//                temp[t++] = arr[j++];
//            }
//        }
//
//        while (i <= mid) {
//            temp[t++] = arr[i++];
//        }
//        while (j <= right) {
//            temp[t++] = arr[j++];
//        }
//
//        t = 0;
//        int moveLeft = left;
//        while (moveLeft <= right) {
//            arr[moveLeft++] = temp[t++];
//        }
//
//    }