package com.algorithm.search;

/**
 * ClassName: BinarySearchNoRecursion
 * Description:
 * date: 2022/3/4 11:37
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class BinarySearchNoRecursion {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int target = 3;
        int i = binarySearch(arr, target);
        System.out.println(i >= 0 ? target + "在数组的" + i + "位置" : "该数不存在");
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (arr[mid] == target) {
                System.out.println("目标以找到");
                return mid;
            }
            if (target < arr[mid]) {
                right = mid - 1;
            }
            if (target > arr[mid]) {
                left = mid + 1;
            }
        }
        return -1;
    }
}
