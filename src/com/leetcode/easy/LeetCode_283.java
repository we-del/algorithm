package com.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ClassName: LeetCode_283
 * Description:
 * 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 示例 2:
 * <p>
 * 输入: nums = [0]
 * <p>
 * date: 2022/4/9 10:45
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class LeetCode_283 {
    public static void main(String[] args) {
        moveZeroes(new int[]{0, 1, 0, 3, 12});
    }

    public static void moveZeroes(int[] nums) {
        if (nums.length <= 0) return;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) nums[index++] = nums[i];
        }
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
    // 使用归并排序
//    public static void moveZeroes(int[] nums) {
//        int max = 0;
//        final int N = 1;
//        for (int num : nums) {
//            max = Math.max(num, max);
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] == 0) nums[i] += max + N;
//        }
//        System.out.println(Arrays.toString(nums));
//        sort(nums, 0, nums.length - 1);
//        System.out.println(Arrays.toString(nums));
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] == max + N) nums[i] = 0;
//        }
//        System.out.println(Arrays.toString(nums));
//    }
//
//    public static void sort(int[] arr, int left, int right) {
//        if (left >= right) return;
//        int mid = (right - left) / 2 + left;
//        sort(arr, left, mid);
//        sort(arr, mid + 1, right);
//        merge(arr, left, mid, right);
//    }
//
//    public static void merge(int[] arr, int left, int mid, int right) {
//        int[] tmp = new int[right - left + 1];
//        int l = left;
//        int r = mid + 1;
//        int index = 0;
//        while (l <= mid && r <= right) {
//            if (arr[l] < arr[r]) tmp[index++] = arr[l++];
//            else tmp[index++] = arr[r++];
//        }
//        while (l <= mid) tmp[index++] = arr[l++];
//        while (r <= right) tmp[index++] = arr[r++];
//        index = 0;
//        int cur = left;
//        while (cur <= right) arr[cur++] = tmp[index++];
//    }
}
