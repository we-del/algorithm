package com.algorithm.sort;

import java.util.Arrays;

/**
 * ClassName: RadixSort
 * Description:
 * date: 2022/2/18 12:09
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {923, 123, 4, 5, 643522423, 13, 12412412};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
        int[] arr1 = {38, 64, 74, 28, 82, 8, 59, 52, 58, 73, 56, 39};
        sort(arr1);
        System.out.println(Arrays.toString(arr1));
        speedTest(RandomArray.randomArray(40000000));
    }

    /**
     * 基数排序是经典的空间换时间算法
     */
    public static void radixSort(int[] arr) {
        // 定义一个二维数组 表示10个桶 [] 表示每个桶(0-9) ,[][] 表示每个桶(0-9)里的数据
        // 然后每个桶的容量为 arr.length ,因为其内容可能有多个相同的值，要避免最坏的情况发生（多个数位上的值相同）
        int[][] bucket = new int[10][arr.length];

        // 为了记录每个桶中，实际存放的数据，需要定义一个一位数组来记录各个桶每次存放的数据
        int[] bucketElementCounts = new int[10]; // 0 索引存储着 0 桶的数据个数 ； 1 索引存储着 1 桶的 数据个数...

        // 得到该数组中最大的数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }

        int maxLength = (max + "").length(); // 得到最大数的长度


        // p为需要比较的轮数.n为对应比较位上的步长 第一轮为 1 ，第二轮为 10，第三轮为 100 ...
        for (int p = 0, n = 1; p < maxLength; p++, n *= 10) {

            // 进行 数位上的排列
            for (int i = 0; i < arr.length; i++) {
                int bucketIndex = arr[i] / n % 10; // 拿到n位数，并放到对应的桶中
                // 放到对于桶的对于索引上 ，bucketElementCounts[bucketIndex] 为当前桶的元素个数
                // bucketElementCounts[bucketIndex]++ 为记录当前桶的元素个数
                bucket[bucketIndex][bucketElementCounts[bucketIndex]++] = arr[i];
            }

            int index = 0;
            // 将 数位上排列后的值反馈给arr
            for (int k = 0; k < bucketElementCounts.length; k++) {
                if (bucketElementCounts[k] > 0) { // 如果当前桶的数据个数大于0 说明有数据
                    // 遍历拿出数据 到arr 中，此时 arr数据里所有数的个位数就有序了
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                    bucketElementCounts[k] = 0; // 重置当前桶的个数(桶中的数据还在，我们指向重置了索引，以便覆盖元素)
                }
            }
        }
    }

    public static void speedTest(int[] arr) {
        long l = System.currentTimeMillis();
        radixSort(arr);
        System.out.println(System.currentTimeMillis() - l);
    }

    public static void sort(int[] arr) {
        int[][] bucket = new int[10][arr.length];
        int[] counts = new int[10];

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) max = arr[i];
        }

        int maxDigit = (max + "").length();
        for (int i = 0, n = 1; i < maxDigit; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int bucketIndex = arr[j] / n % 10;
                bucket[bucketIndex][counts[bucketIndex]++] = arr[j];
            }
            int index = 0;
            for (int j = 0; j < counts.length; j++) {
                if (counts[j] > 0) {
                    for (int k = 0; k < counts[j]; k++) {
                        arr[index++] = bucket[j][k];
                    }
                    counts[j] = 0;
                }
            }
        }

    }
}
