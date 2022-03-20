package com.algorithm.search;

import java.util.ArrayList;

/**
 * ClassName: BinarySearch
 * Description:
 * date: 2022/2/19 12:59
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 5, 5, 5, 5, 5, 6, 7};
        System.out.println(binarySearch(arr, 5, 0, arr.length - 1));
        System.out.println(binarySearchList(arr, 5, 0, arr.length - 1));

    }

    /**
     * 此二分查找 用于 查找数是否在数组中
     */
    public static boolean binarySearch(int[] arr, int target, int left, int right) {
        if (left < 0 || right < 0) { // 输入错误
            throw new RuntimeException("输入索引错误");
        }

        // 表示再数组内
        if (left <= right) {

            // 拿到中点索引和中间值
            int mid = (right - left) / 2 + left;
            int pivot = arr[mid];

            if (pivot == target) { // 如果相等 说明找到
                return true;
            }

            if (target < pivot) { // 小于中间则在中间的左边继续查找
                return binarySearch(arr, target, left, mid - 1);
            } else {// 小于中间则在中间的右边继续查找
                return binarySearch(arr, target, mid + 1, right);
            }
        }
        // 超出数据 退出查找
        return false;
    }

    /**
     * 此二分查找用于查找多个相同数
     */
    public static ArrayList binarySearchList(int[] arr, int target, int left, int right) {
        if (left < 0 || right < 0) { // 输入错误
            throw new RuntimeException("输入索引错误");
        }

        // 表示再数组内
        if (left <= right) {

            // 拿到中点索引和中间值
            int mid = (right - left) / 2 + left;
            int pivot = arr[mid];

            if (pivot == target) { // 如果相等 说明找到
                ArrayList<Integer> integers = new ArrayList<>();
                integers.add(mid);
                // 从 查找值的左边遍历 看其左边是否右相同的值
                for (int i = mid - 1; i >= left && arr[i] == pivot; i--) {
                    integers.add(i);
                }
                // 从 查找值的左边遍历 看其左边是否右相同的值
                for (int i = mid + 1; i <= right && arr[i] == pivot; i++) {
                    integers.add(i);
                }
                return integers;
            }

            if (target < pivot) { // 小于中间则在中间的左边继续查找
                return binarySearchList(arr, target, left, mid - 1);
            } else {// 小于中间则在中间的右边继续查找
                return binarySearchList(arr, target, mid + 1, right);
            }
        }
        // 超出数据 退出查找
        return null;
    }
}
