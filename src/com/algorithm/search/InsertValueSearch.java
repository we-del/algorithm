package com.algorithm.search;

/**
 * ClassName: InsertValueSearch
 * Description:
 * date: 2022/2/19 14:56
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 5, 5, 5, 6, 7};
        System.out.println(insertValueSearch(arr, 0, arr.length - 1, 2));
        System.out.println(insertValueSearch(arr, 0, arr.length - 1, 5));
    }

    public static boolean insertValueSearch(int[] arr, int left, int right, int keyValue) {
        if (left > right || keyValue < arr[0] || keyValue > arr[arr.length - 1]) {
            return false;
        }

        // 自适应中点公式
        int mid = left + (right - left) * (keyValue - arr[left]) / (arr[right] - arr[left]);
        if (keyValue == arr[mid]) { // 相等 ，说明此自适应中点就是查找的值
            return true;
        }
        if (keyValue > arr[mid]) { // 向 右自适应
            return insertValueSearch(arr, mid + 1, right, keyValue);
        } else { // 向左 自适应查找中点
            return insertValueSearch(arr, left, mid - 1, keyValue);
        }
    }
}
