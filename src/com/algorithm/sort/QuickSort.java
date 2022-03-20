package com.algorithm.sort;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

/**
 * ClassName: QuickSort
 * Description:
 * date: 2022/2/15 16:34
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class QuickSort {
    public static void main(String[] args) {
        //int[] arr = {1,2,2,2,2,6,1,3};
        // 该(hsp)快排处理不留此数组 int[] arr = {38, 64, 74, 28, 82, 8, 59, 52, 58, 73, 56, 39};

        // System.out.println("原始数据:" + Arrays.toString(arr));

        // 有争议的排序
//        int[] arr = {38, 64, 74, 28, 82, 8, 59, 52, 58, 73, 56, 39};
//        int[] arr1 = {38, 64, 74, 28, 82, 8, 59, 52, 58, 73, 56, 39};
//        int[] arr2 = {38, 64, 74, 28, 82, 8, 59, 52, 58, 73, 56, 39};
       // int[] arr = RandomArray.randomArray(10);
        int[] arr1 = {3,3,2,2,5,6,6,54,1,2,3,1,2};
        int[] arr = {3, 5, 4, 0, 4, 6, 7, 2};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
        //3,3,2,2,5,6,6,54,1,2,3,1,2
        //3,3,2,2,2,6,6,54,1,2,3,1,5
        //3,3,2,2,2,1,6,54,1,2,3,6,5
        //3,3,2,2,2,1,2,1,54,6,3,6,5
        //1,1,2,2,2,3,2,3,54,6,3,6,5
        //1,1,2,2,2,3,2,3,54,6,3,6,5
        //1,1,2,2,2,3,2,3,54,6,3,6,5
        //1,1,2,2,2,3,2,3,54,6,3,6,5
        //1,1,2,2,2,2,3,3,54,6,3,6,5

        int[] arr2 = Arrays.copyOf(arr, arr.length);
//        // int[] arrCopy = Arrays.copyOf(arr,arr.length);
//        quickSort(arr, 0, arr.length - 1);
//        System.out.println("hsp：" + Arrays.toString(arr));
       // qs(arr1, 0, arr1.length - 1);
//        quickSort(arr1, 0, arr1.length - 1);
//        System.out.println("cur：" + Arrays.toString(arr1));
//        demo(arr2, 0, arr2.length - 1);
//        System.out.println("teach: " + Arrays.toString(arr2));


        // 测试
        speedTest(RandomArray.randomArray(800000));
    }

    /**
     * 补充：
     * 求中点 公式 (大-小)/2+小(此方法可避免数据溢出) 如 （20-10）/2 + 10 = 15
     */

    /*
    *  process
    *     38, 64, 74, 28, 82, 8, 59, 52, 58, 73, 56, 39 // 参考点为 38
    *     38, 8, 74, 28, 82, 64, 59, 52, 58, 73, 56, 39 // 一轮交换
    *     28, 8, 38, 74, 82, 64, 59, 52, 58, 73, 56, 39  // 此时38 已经排序完毕 ，然后 38 左边(28)和右边(39)继续排序
    *     8, 28, 38, 74, 82, 64, 59, 52, 58, 73, 56, 39 // (38开栈分支(左))此时28 已经排序完毕 ，此时其左右两边已经有序
    *     8, 28, 38, 74, 39, 64, 59, 52, 58, 73, 56, 82 // (38开栈分支(右))以 74 为参考点 ，39和82交换
    *     8, 28, 38, 56, 39, 64, 59, 52, 58, 73, 74, 82 // (38开栈分支)此时 74 已经排序完毕 ，然后 74 左边(64)和右边(82)继续排序
    *     8, 28, 38, 56, 52, 64, 59, 39, 58, 73, 74, 82 // (74开栈分支) 以 56 为参考点,52和39交换
    *     8, 28, 38, 56, 52, 64, 59, 39, 58, 73, 74, 82 // (74开栈分支) 74右边 82已经有序
    *     8, 28, 38, 56, 52, 64, 59, 39, 58, 73, 74, 82 // (74开栈分支) 以从73开始向左查找一个比 56 小的以56开始向右查找·一个比56大的
    *     8, 28, 38, 56, 52, 39, 59, 64, 58, 73, 74, 82 // (74开栈分支) 39和64交换
    *     8, 28, 38, 39, 52, 56, 59, 64, 58, 73, 74, 82 // (74开栈分支) 此时56 已经排序完毕 ，然后 56 左边(39)和右边(73)继续排序
    *     8, 28, 38, 39, 52, 56, 59, 64, 58, 73, 74, 82 // (56开栈分支) 39和52已经有序
    *     8, 28, 38, 39, 52, 56, 59, 64, 58, 73, 74, 82 // (56开栈分支) 以 59 为参照点，继续排序
    *     8, 28, 38, 39, 52, 56, 59, 58, 64, 73, 74, 82 // (56开栈分支) 64 和 58 交换
    *     8, 28, 38, 39, 52, 56, 58, 59, 64, 73, 74, 82 // (56开栈分支) 58 和 59 交换 此时排序完毕 ，一层一层返回栈
    *
    *
     *
    * */
    public static void qs(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int l = left;
        int r = right;
        int pivot = arr[left];
        while (true) {

            // 当参考数为首部索引时, 每次查找 都必须从后往前找(再从前往后找)，这样才能落到正确的点位上
            // （1）因为 如果先从前往后找(按小到大顺序排) 最终会先落到一个较大的数上，然后会将较大的数放到首部，导致数据排序错误
            //      而从后往前找不会，它最终会落到一个较小的数上，然后和首部进行交换，达到有序
            // （2）因为 如果先从前往后找(按大到小顺序排) 最终会先落到一个较小的数上，然后会将较小的数放到首部，导致数组排序错误
            //      而先从后往前找不会，它最终会落到一个较大的数上，然后和首部进行交换，达到有序

            // 当参考数为尾部索引时，每次查找 都必须从前往后找(再从后往前找)，这样才能落在正确的点位上
            // （1）因为 如果先从后往前找(按小到大顺序排) 最终会先落到一个较小的数上，然后会将较小的数放到尾部，导致数据排序错误
            //      而从前往前找不会，它最终会落到一个较大的数上，然后和尾部进行交换
            // （2）因为 如果先从后往前找(按大到小顺序排) 最终会先落到一个较大的数上，然后会将较大的数放到尾部，导致数组排序错误
            //      而先从前往后找不会，它最终会落到一个较小的数上，然后和尾部进行交换

            // 找到一个小于pivot的点位(从后往前找)
            while (arr[r] >= pivot && l < r) {
                r--;
            }

            // 找到一个大于pivot的点位(从前往后找)
            while (arr[l] <= pivot && l < r) {
                l++;
            }





            // 当两个点位在一起时，说明已经局部有序，交换尾部的值
            if (l == r) {
                break;
            }

            // 交换两个值
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

        }

        // 和临界点位交换，达到局部有序
        arr[left] = arr[l];
        arr[l] = pivot;

        // 此时 指针指向一个点位(索引),该点位已经有序,l-1向左边遍历
        qs(arr, left, l - 1);
        // 此时 指针指向一个点位(索引),该点位已经有序,r+1向右边遍历
        qs(arr, r + 1, right);
    }

    public static void demo(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int base = arr[right];
        int i = left;
        int j = right;
        while (i != j) { // 两个指针再一个点上 退出
            // 38, 64, 74, 28, 82, 8, 59, 52, 58, 73, 56, 39
            // 38, 64, 74, 28, 82, 8, 59, 52, 58, 73, 56, 39


            while (arr[i] >= base && i < j) {
                i++;
            }

            while (arr[j] <= base && i < j) {
                j--;
            }
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        arr[right] = arr[i];
        arr[i] = base;
        demo(arr, left, i - 1);
        demo(arr, j + 1, right);
    }

    public static void quickSort(int[] arr,int left,int right){
        if(left>right){
            return;
        }
        int pivot = arr[left];
        int l = left;
        int r = right;
        while(true){
            while(arr[r] >= arr[left]&& l<r){
                r--;
            }
            while(arr[l]<= arr[left]&& l < r){
                l++;
            }
            if(r == l){
                break;
            }

            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
        }
        arr[left] = arr[l];
        arr[l] = pivot;
        quickSort(arr,left,l-1);
        quickSort(arr,r+1,right);

    }
    
    private static void speedTest(int [] arr){
        long l = System.currentTimeMillis();
        quickSort(arr,0,arr.length-1);
        System.out.println("消耗时间："+(System.currentTimeMillis()-l));
     //   System.out.println(Arrays.toString(arr));
    }
}
