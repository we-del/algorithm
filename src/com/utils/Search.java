package com.utils;

import java.util.Arrays;

/**
 * ClassName: Search
 * Description: 此类用于收集常用的数组(顺序)查找方式
 * date: 2022/2/22 13:18
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class Search {



    /**
     * @param arr      为查找的目标数组
     * @param keyValue 为在该数组中查找的目标
     * @return 返回目标所在数组位置
     * @apiNote 顺序查找，通过for循环遍历速度来完成查找
     * @description 此查找方式速度较慢
     */
    public static int orderSearch(int[] arr, int keyValue) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == keyValue) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @param arr      为查找的目标数组
     * @param keyValue 为在该数组中查找的目标
     * @param left     数组左边开始查找索引
     * @param right    数组右边开始查找索引
     * @return 返回目标所在数组位置
     * @apiNote 二分查找，通过根据中间值递归完成查找
     * @description 此查找方式比顺序查找快，但相比于自适应mid查找效率可能较低(如果数组是连续的数字情况)
     */
    public static int binarySearch(int[] arr, int left, int right, int keyValue) {
        if (left < 0 || right < 0) {
            System.out.println("输入错误");
            return -1;
        }
        if (left <= right) {

            int mid = (right - left) / 2 + left;
            if (arr[mid] == keyValue) {
                return mid;
            }
            if (keyValue < arr[mid]) {

                return binarySearch(arr, left, mid - 1, keyValue);
            } else {
                return binarySearch(arr, mid + 1, right, keyValue);
            }
        }
        return -1;
    }

    /**
     * @param arr      为查找的目标数组
     * @param keyValue 为在该数组中查找的目标
     * @param left     数组左边开始查找索引
     * @param right    数组右边开始查找索引
     * @return 返回目标所在数组位置
     * @apiNote 插值查找，通过根据自适应中间值递归完成查找
     * @description 此查找方式在数组索引之间数的差距很小的的情况下(arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 })比二分查找快，
     * 如果数组索引值的差距过大(arr={1,100,200,10000,40000})相比于二分查找效率可能较低(如果数组是连续的数字情况)
     */
    public static int insertValueSearch(int[] arr, int left, int right, int keyValue) {
        if (left < 0 || right < 0) {
            System.out.println("输入错误");
            return -1;
        }
        if (left <= right) {

            int mid = left + (right - left) * (keyValue - arr[left]) / (arr[right] - arr[left]);
            if (arr[mid] == keyValue) {
                return mid;
            }
            if (keyValue < arr[mid]) {

                return binarySearch(arr, left, mid - 1, keyValue);
            } else {
                return binarySearch(arr, mid + 1, right, keyValue);
            }
        }
        return -1;
    }

    /**
     * 黄金分割法有待考证
     * @param a      为查找的目标数组
     * @param key 为在该数组中查找的目标
     * @return 返回目标所在数组位置
     * @apiNote 黄金分隔查找，通过黄金分隔点定位循环完成查找
     * @description 此方式查找速度较快
     */
//    public static int fibonacciSearch(int[] a, int key) {
//        int low = 0;
//        int high = a.length - 1;
//        int k = 0; // 表示斐波那契分隔数值的下标
//        int mid = 0;
//        int[] f = fib(20);
//
//        // 找到斐波那契分隔数值的下标
//        while (high > f[k] - 1) {
//            k++;
//        }
//
//        // 因为f[k] 的值 可能大于 a 的长度  ，因此需要可能一个数组来临时存储数值
//        int[] temp = Arrays.copyOf(a, f[k]);
//        // 如果临时数组长度大于a数组长度，则多余的部分用a最后一个值来填充
//        // 如  1 4 5 6 0 0 0    变为 1 4 5 6 6 6 6
//        // 3*3-2/(2*3+2+(23-1))   3 3 * 2 2 3 * 2 + 23 1 - + / -
//        for (int i = high + 1; i < temp.length; i++) {
//            temp[i] = a[high];
//        }
//
//        while (low <= high) {
//            mid = low + f[k - 1] - 1;
//            if (key < temp[mid]) {
//                high = mid - 1;
//                k--; // 更改分隔点的位置
//            } else if (key > temp[mid]) {
//                low = mid + 1;
//                k -= 2;
//            } else {
//                if (mid <= high) {
//                    return mid;
//                } else {
//                    return high;
//                }
//            }
//        }
//        return 0;
//    }
////    public static int fibonacciSearch(int[] arr, int keyValue) {
////        int low = 0;
////        int high = arr.length - 1;
////        int k = 0;
////        int[] fib = fib(20);
////
////        while (high > fib[k] - 1) {
////            k++;
////        }
////
////        // 根据原数组创建一个黄金分隔数组
////        int[] temp = Arrays.copyOf(arr,k);
////        for (int i = high+1; i < temp.length; i++) {
////            temp[i] = arr[high];
////        }
////        while(low<=high){
////            int mid = low + fib[k-1]-1;
////            if(keyValue < temp[mid]){
////                high = mid-1;
////                k--;
////            }else if(keyValue > temp[mid]){
////                low = mid+1;
////                k-=2;
////            }else{
////                if(mid<=high){
////                    return mid;
////                }else{
////                    return high;
////                }
////            }
////
////        }
////        return -1;
////    }
//
//    private static int[] fib(int length) {
//        int[] fib = new int[length];
//        fib[0] = 1;
//        fib[1] = 1;
//        for (int i = 2; i < fib.length; i++) {
//            fib[i] = fib[i - 1] + fib[i - 2];
//        }
//        return fib;
//    }

}
