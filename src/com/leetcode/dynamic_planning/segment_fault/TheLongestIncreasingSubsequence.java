package com.leetcode.dynamic_planning.segment_fault;

import java.util.Arrays;

/**
 * ClassName: TheLongestIncreasingSubsequence
 * Description:
 * date: 2022/4/16 10:32
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class TheLongestIncreasingSubsequence {
    public static void main(String[] args) {
        start(new int[]{0, 1, 0, 3, 2, 3});
        // start(new int[]{5,7,-24,12,13,2,3,12,5,6,35});
        // 5 7
    }

    public static void start(int[] arr) {
        int[] value = new int[arr.length];
        int index = 0;
        value[index++] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > value[i - 1]) {
                value[index++] = arr[i];
            } else {
                int left = 0;
                int right = index - 1;
                while (left <= right) {
                    int mid = (right - left) / 2 + left;
                    if (value[mid] == arr[i]) value[mid] = arr[i];
                    if (value[mid] < arr[i]) {
                        left = mid + 1;
                    }
                    if (value[mid] > arr[i]) {
                        right = mid - 1;
                    }

                }
            }
        }
    }
    // O(n^2)解法
//    public static void start(int[] arr) {
//        int len = arr.length;
//        int[] dp = new int[len];
//        int[][] values = new int[len][len];
//        int[] finalLen = new int[len];
//        int cur = Integer.MIN_VALUE;
//        int pre = cur;
//        int index = 0;
//        for (int i = 0; i < len; i++) {
//            index = 0;
//            for (int j = i; j < len; j++) {
//                if (j == i) {
//                    values[i][index++] = arr[j];
//                    cur = arr[j];
//                } else {
//
//                    cur = Math.max(cur, arr[j]);
//
//                    // 说明没有当前值比前一个值小
//                    if (pre == cur) {
//
//                        if (index - 2 >= 0 && values[i][index - 2] < arr[j]) { // 替换当前最大值
//                            cur = arr[j];
//                            values[i][index-1] = arr[j];
//                        }
//                    } else { // 添加当前最大值
//                        values[i][index++] = arr[j];
//                    }
//                }
//                pre = cur;
//                finalLen[i] = index;
//            }
//        }
//
//        int n = 0;
//        for (int[] value : values) {
//            System.out.println(Arrays.toString(value));
//        }
//        for (int i : finalLen) {
//            n = Math.max(i, n);
//        }
//        System.out.println(n);
//    }
}
