package com.leetcode.easy;

/**
 * ClassName: LeetCode_35
 * Description:
 * date: 2022/4/20 8:54
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class LeetCode_35 {
    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 2));
    }

    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == target) return mid;
            if (target > nums[mid]) left = mid + 1;
            else right = mid - 1;
        }
        int i = 0;
        for (; i < nums.length; i++) {
            if (target < nums[i]) return i;
        }
        return i;
    }
}
