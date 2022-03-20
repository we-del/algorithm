package com.utils;

import com.algorithm.sort.RandomArray;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * ClassName: Sort
 * Description:  此类中 我将整理我学过的所有排序算法，以便我在使用时快速调用
 * date: 2022/2/18 20:33
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class Sort {

    public static void main(String[] args) {

        // 冒泡排序测试
//        int[] ints = {4, 5, 1, 2, 3};
//        bubblingSort(ints, true);
//        int[] ints1 = {4, 5, 1, 2, 3};
//        bubblingSort(ints1, false);
//        System.out.println("大到小的顺序:"+Arrays.toString(ints));
//        System.out.println("小到大的顺序:"+Arrays.toString(ints1));

        // 插入排序测试
//        int[] ints = {4, 5, 1, 2, 3};
//        insertSort(ints, true);
//        int[] ints1 = {4, 5, 1, 2, 3};
//        insertSort(ints1, false);
//        System.out.println("大到小的顺序:"+Arrays.toString(ints));
//        System.out.println("小到大的顺序:"+Arrays.toString(ints1));

        // 选择排序
//        int[] ints = {4, 5, 1, 2, 3};
//        selectSort(ints, true);
//        int[] ints1 = {4, 5, 1, 2, 3};
//        selectSort(ints1, false);
//        System.out.println("大到小的顺序:"+Arrays.toString(ints));
//        System.out.println("小到大的顺序:"+Arrays.toString(ints1));

        // 希尔排序
//        int[] ints = {4, 5, 1, 2, 3};
//        shellSort(ints, true);
//        int[] ints1 = {4, 5, 1, 2, 3};
//        shellSort(ints1, false);
//        System.out.println("大到小的顺序:" + Arrays.toString(ints));
//        System.out.println("小到大的顺序:" + Arrays.toString(ints1));

        // 快速排序
//        int[] ints = {4, 5, 1, 2, 3};
//        quickSort(ints, 0, ints.length - 1, true);
//        int[] ints1 = {4, 5, 1, 2, 3};
//        quickSort(ints1, 0, ints1.length - 1, false);
//        System.out.println("大到小的顺序:" + Arrays.toString(ints));
//        System.out.println("小到大的顺序:" + Arrays.toString(ints1));

        // 归并排序
//        int[] ints = {4, 5, 1, 2, 3};
//        mergeSort(ints, 0, ints.length - 1, true);
//        int[] ints1 = {4, 5, 1, 2, 3};
//        mergeSort(ints1, 0, ints1.length - 1, false);
//        System.out.println("大到小的顺序:" + Arrays.toString(ints));
//        System.out.println("小到大的顺序:" + Arrays.toString(ints1));

        // 堆排序
        int[] ints = RandomArray.randomArray(10);
        int[] ints1 = Arrays.copyOf(ints, ints.length);
        System.out.println("改变前" + Arrays.toString(ints));
        System.out.println("改变前:" + Arrays.toString(ints1));
        heapSort(ints, true);
        heapSort(ints1);
        System.out.println("大到小的顺序:" + Arrays.toString(ints));
        System.out.println("小到大的顺序:" + Arrays.toString(ints1));
    }

    /**
     * @param arr  为被排序的数组
     * @param flag 如果为true表示从大到小排列，如果为flag表示从小到达排列
     * @apiNote 冒泡排序
     * @description 这是一个 时间复杂度为O(n^2)的算法
     */
    public static void bubblingSort(int[] arr, boolean flag) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (flag) {
                    if (arr[j] < arr[j + 1]) {
                        arr[j] ^= arr[j + 1];
                        arr[j + 1] ^= arr[j];
                        arr[j] ^= arr[j + 1];
                    }
                } else {
                    if (arr[j] > arr[j + 1]) {
                        arr[j] ^= arr[j + 1];
                        arr[j + 1] ^= arr[j];
                        arr[j] ^= arr[j + 1];
                    }
                }

            }
        }
    }

    // 重载bubbling方法 当传入一个数组时，默认从小到大排序
    public static void bubblingSort(int[] arr) {
        bubblingSort(arr, false);
    }

    /**
     * @param arr  为被排序的数组
     * @param flag 如果为true表示从大到小排列，如果为flag表示从小到达排列
     * @apiNote 插入排序
     * @description 这是一个 时间复杂度为O(n^2)的算法
     */
    public static void insertSort(int[] arr, boolean flag) {
        for (int i = 1; i < arr.length; i++) {
            if (flag) {
                for (int j = i; j - 1 >= 0 && arr[j] > arr[j - 1]; j--) {

                    arr[j] ^= arr[j - 1];
                    arr[j - 1] ^= arr[j];
                    arr[j] ^= arr[j - 1];
                }
            } else {
                for (int j = i; j - 1 >= 0 && arr[j] < arr[j - 1]; j--) {

                    arr[j] ^= arr[j - 1];
                    arr[j - 1] ^= arr[j];
                    arr[j] ^= arr[j - 1];
                }
            }

        }
    }

    public static void insertSort(int[] arr) {
        insertSort(arr, false);
    }

    /**
     * @param arr  为被排序的数组
     * @param flag 如果为true表示从大到小排列，如果为flag表示从小到达排列
     * @apiNote 选择排序
     * @description 这是一个 时间复杂度为O(n^2)的算法
     */
    public static void selectSort(int[] arr, boolean flag) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (flag) {
                    if (arr[i] < arr[j]) {
                        arr[i] ^= arr[j];
                        arr[j] ^= arr[i];
                        arr[i] ^= arr[j];
                    }
                } else {
                    if (arr[i] > arr[j]) {
                        arr[i] ^= arr[j];
                        arr[j] ^= arr[i];
                        arr[i] ^= arr[j];
                    }
                }
            }

        }
    }

    public static void selectSort(int[] arr) {
        selectSort(arr, false);
    }

    /**
     * @param arr  为被排序的数组
     * @param flag 如果为true表示从大到小排列，如果为flag表示从小到达排列
     * @apiNote 希尔排序
     * @description 这是一个 时间复杂度为O(nlog(2)n)的算法
     */
    public static void shellSort(int[] arr, boolean flag) {
        for (int len = arr.length / 2; len > 0; len /= 2) {
            for (int i = len; i < arr.length; i++) {
                int j = i;
                int valueCompared = arr[j];
                if (flag) {
                    if (arr[j] > arr[j - len]) {
                        while (j - len >= 0 && valueCompared > arr[j - len]) {
                            arr[j] = arr[j - len];
                            j -= len;
                        }
                        arr[j] = valueCompared;
                    }
                } else {
                    if (arr[j] < arr[j - len]) {
                        while (j - len >= 0 && valueCompared < arr[j - len]) {
                            arr[j] = arr[j - len];
                            j -= len;
                        }
                        arr[j] = valueCompared;
                    }
                }
            }
        }
    }

    public static void shellSort(int[] arr) {
        shellSort(arr, false);
    }

    /**
     * @param arr   为被排序的数组
     * @param flag  如果为true表示从大到小排列，如果为flag表示从小到达排列
     * @param left  为数组的左边开始查找索引
     * @param right 为数组的右边结束查找索引
     * @apiNote 快速排序
     * @description 这是一个 时间复杂度为O(nlog(2)n)的算法
     */
    public static void quickSort(int[] arr, int left, int right, boolean flag) {

        if (left < right) {
            int pivot = arr[right];
            int l = left;
            int r = right;
            if (flag) {
                while (true) {
                    while (arr[l] >= pivot && l < r) {
                        l++;
                    }
                    while (arr[r] <= pivot && l < r) {
                        r--;
                    }
                    if (l == r) {
                        break;
                    }
                    int temp = arr[l];
                    arr[l] = arr[r];
                    arr[r] = temp;

                }
                arr[right] = arr[l];
                arr[r] = pivot;
                quickSort(arr, left, l - 1, true);
                quickSort(arr, r + 1, right, true);

            } else {

                while (true) {
                    while (arr[l] <= pivot && l < r) {
                        l++;
                    }
                    while (arr[r] >= pivot && l < r) {
                        r--;
                    }
                    if (l == r) {
                        break;
                    }
                    int temp = arr[l];
                    arr[l] = arr[r];
                    arr[r] = temp;

                }
                arr[right] = arr[l];
                arr[r] = pivot;
                quickSort(arr, left, l - 1, false);
                quickSort(arr, r + 1, right, false);
            }

        }
    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1, false);
    }

    public static void quickSort(int[] arr, boolean flag) {
        quickSort(arr, 0, arr.length - 1, flag);
    }

    /**
     * @param arr   为被排序的数组
     * @param flag  如果为true表示从大到小排列，如果为flag表示从小到达排列
     * @param left  为数组的左边开始查找索引
     * @param right 为数组的右边结束查找索引
     * @apiNote 归并排序 用到 mergeSort和merge方法
     * @description 这是一个 时间复杂度为O(nlog(2)n)的算法
     */
    public static void mergeSort(int[] arr, int left, int right, boolean flag) {
        if (left != right) {
            int mid = (right - left) / 2 + left;
            mergeSort(arr, left, mid, flag);
            mergeSort(arr, mid + 1, right, flag);
            merge(arr, left, mid, right, flag);
        }
    }

    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1, false);
    }

    public static void mergeSort(int[] arr, boolean flag) {
        mergeSort(arr, 0, arr.length - 1, flag);
    }

    private static void merge(int[] arr, int left, int mid, int right, boolean flag) {
        int l = left;
        int r = mid + 1;
        int[] temp = new int[arr.length];
        int t = 0;
        if (flag) {

            while (l <= mid && r <= right) {
                if (arr[l] > arr[r]) {
                    temp[t++] = arr[l++];
                } else {
                    temp[t++] = arr[r++];
                }
            }

            while (l <= mid) {
                temp[t++] = arr[l++];
            }
            while (r <= right) {
                temp[t++] = arr[r++];
            }

            t = 0;
            int index = left;
            while (index <= right) {
                arr[index++] = temp[t++];
            }

        } else {
            while (l <= mid && r <= right) {
                if (arr[l] < arr[r]) {
                    temp[t++] = arr[l++];
                } else {
                    temp[t++] = arr[r++];
                }
            }

            while (l <= mid) {
                temp[t++] = arr[l++];
            }
            while (r <= right) {
                temp[t++] = arr[r++];
            }

            t = 0;
            int index = left;
            while (index <= right) {
                arr[index++] = temp[t++];
            }
        }
    }

    /**
     * @param arr 为被排序的数组
     * @apiNote 基数排序
     * @description 这是一个 时间复杂度为O(n*k)的算法 k 为桶数 , 再所有数都为整数的情况下再使用基数排序，因此该排序没有处理负数
     */
    public static void radixSort(int[] arr, boolean flag) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) max = arr[i];
        }

        int[][] bucket = new int[10][arr.length];
        int[] bucketCounts = new int[10];
        int maxDigitalLength = (max + "").length();
        for (int i = 0, n = 1; i < maxDigitalLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int bucketIndex = arr[j] / n % 10;
                bucket[bucketIndex][bucketCounts[bucketIndex]++] = arr[j];
            }
            int index = 0;
            for (int j = 0; j < bucketCounts.length; j++) {
                if (bucketCounts[j] > 0) {
                    for (int k = 0; k < bucketCounts[j]; k++) {
                        arr[index++] = bucket[j][k];
                    }
                    bucketCounts[j] = 0;
                }
            }
            if (flag) {
                for (int j = arr.length - 1, e = 0; e <= j && j != e; j--, e++) {
                    arr[j] ^= arr[e];
                    arr[e] ^= arr[j];
                    arr[j] ^= arr[e];
                }
            }
        }
    }

    public static void radixSort(int[] arr) {
        radixSort(arr, false);
    }

    /**
     * @param arr  为被排序的数组
     * @param flag 为 true表示表示从大到小，为false表示从小到大，默认 false
     * @apiNote 堆排序
     * @description 这是一个 时间复杂度为O(nlog(2)n)的算法 , 每次会找出范围内的最大(小)值放到大堆顶，然后在将该值放到数组尾部即可
     */
    public static void heapSort(int[] arr, boolean flag) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length, flag);
        }
        for (int j = arr.length - 1; j > 0; j--) {
            arr[j] ^= arr[0];
            arr[0] ^= arr[j];
            arr[j] ^= arr[0];
            adjustHeap(arr, 0, j, flag);
        }
    }

    public static void heapSort(int[] arr) {
        heapSort(arr, false);
    }

    private static void adjustHeap(int[] arr, int i, int length, boolean flag) {
        int temp = arr[i];
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            if (flag) {
                if (j + 1 < length && arr[j] > arr[j + 1]) {
                    j++;
                }
                if (temp > arr[j]) {
                    arr[i] = arr[j];
                    i = j;
                } else {
                    break;
                }
            } else {
                if (j + 1 < length && arr[j] < arr[j + 1]) {
                    j++;
                }
                if (temp < arr[j]) {
                    arr[i] = arr[j];
                    i = j;
                } else {
                    break;
                }
            }

        }
        arr[i] = temp;
    }
}
