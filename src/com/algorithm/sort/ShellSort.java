package com.algorithm.sort;

import java.util.Arrays;

/**
 * ClassName: ShellSort
 * Description:
 * date: 2022/2/14 15:34
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class ShellSort {
    public static void main(String[] args) {
//        int[] ints = RandomArray.randomArray(8);
//        int[] arr =  {38, 64, 74, 28, 82, 8, 59, 52, 58, 73, 56, 39};
//        //    shellSortOfExchange(arr);
//        shellSortOfMove(arr);
//       // sortDemo(ints);
//        System.out.println(Arrays.toString(ints));
        long total = 0;
        for (int i = 0; i < 100; i++) {
            int[] arr = RandomArray.randomArray(800000);
            total += speedTest(arr);
        }
        System.out.println("平均虚度："+total/100);

    }

    public static void shellSortOfExchange(int[] arr) { // 设 int[] arr = {8,9,1,7,2,3,5,4,6,0};
        for (int len = arr.length / 2; len > 0; len /= 2) {
            for (int i = len; i < arr.length; i++) {
                for (int j = i - len; j >= 0; j -= len) {

                    // 正确解法，以下为 将 最大(小)的按数组索引排序
                    if (arr[j + len] < arr[j]) {
                        // [3, 5, 1, 6, 0, 8, 9, 4, 7, 2]
                        // [1, 5, 3, 6, 0, 8, 9, 4, 7, 2]
                        // [0, 5, 1, 6, 3, 8, 9, 4, 7, 2]
                        // [0, 4, 1, 5, 3, 6, 9, 8, 7, 2]
                        // [0, 4, 1, 5, 3, 6, 7, 8, 9, 2]
                        // [0, 4, 1, 5, 3, 6, 7, 2, 9, 8]
                        // [0, 4, 1, 5, 3, 2, 7, 6, 9, 8]
                        // [0, 4, 1, 2, 3, 5, 7, 6, 9, 8]
                        // [0, 2, 1, 4, 3, 5, 7, 6, 9, 8]


                        arr[j + len] ^= arr[j];
                        arr[j] ^= arr[j + len];
                        arr[j + len] ^= arr[j];
                    }

                    // 错误解法 ，以下为 将 arr[i-n] 位置上 的最大值放到i位置上
//                   if(arr[i]<arr[j]){
//                       // [3, 5, 1, 6, 0, 8, 9, 4, 7, 2]
//                       // [1, 5, 3, 6, 0, 8, 9, 4, 7, 2]
//                       // [1, 5, 0, 6, 3, 8, 9, 4, 7, 2]
//                       // [1, 5, 0, 6, 3, 4, 9, 8, 7, 2]
//                       // [1, 5, 0, 6, 3, 4, 7, 8, 9, 2]
//                       // [1, 5, 0, 6, 3, 4, 7, 2, 9, 8]
//                       arr[i]^=arr[j];
//                       arr[j]^=arr[i];
//                       arr[i]^=arr[j];
                }
            }
        }

        System.out.println(Arrays.toString(arr));
    }

    public static void shellSortOfMove(int[] arr) {
        // 设 int[] arr = {8,9,1,7,2,3,5,4,6,0};
        for (int len = arr.length / 2; len > 0; len /= 2) { // 把数组分成 len组，方便局部排序
            for (int i = len; i < arr.length; i++) { // 从当前分组的首元素 开始遍历
                int j = i; // 记录当前分组的索引
                int temp = arr[j]; // 记录当前分组的值
                if (arr[j] < arr[j - len]) { // 如果当前分组的索引 小于 当前的分组的前一个值
                    while (j - len >= 0 && temp < arr[j - len]) {
                        // j - len >= 0 表示当前j的值必须再数组索引上
                        //  temp < arr[j - len] 当前位置 小于 其组内上一个索引位置的值时 就继续以下操作
                        arr[j] = arr[j - len]; // 当前位置(索引)拿到上一个索引位置的值
                        j -= len; // j减去分组步长
                    }
                    arr[j] = temp; // 把 temp(开始位置的值) 插入到 最后一次移动值的位置 ，完成插入
                }
            }
        }
    }


    private static long speedTest(int[] arr) {
        long l = System.currentTimeMillis();
        shellSortOfMove(arr);
        System.out.println("消耗:" + (System.currentTimeMillis() - l));
        return System.currentTimeMillis() - l;
        // System.out.println(Arrays.toString(arr));
    }
}
