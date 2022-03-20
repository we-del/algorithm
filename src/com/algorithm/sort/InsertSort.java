package com.algorithm.sort;

import java.util.Arrays;

/**
 * ClassName: InsertSort
 * Description:
 * date: 2022/2/13 18:19
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class InsertSort {
    public static void main(String[] args) {
        //int[] arr = RandomArray.randomArray(10);
        int[] arr =  {38, 64, 74, 28, 82, 8, 59, 52, 58, 73, 56, 39};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));

        speedTest(RandomArray.randomArray(50000));
    }

    public static void insertSort(int arr[]) { // 设 int[] arr = { 3,4,1,5,2}
        for (int i = 1; i < arr.length; i++) {
            // 3 4 1 5 2|| 3 1 4 5 2 | 1 3 4 5 2 || 1 3 4 2 5 | 1 3 2 4 5 | 1 2 3 4 5
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                arr[j] ^= arr[j - 1];
                arr[j - 1] ^= arr[j];
                arr[j] ^= arr[j - 1];
            }
        }

    }

    private static void speedTest(int[] arr){
        long l = System.currentTimeMillis();
        insertSort(arr);
        System.out.println("消耗:"+(System.currentTimeMillis()-l));
    }
}
