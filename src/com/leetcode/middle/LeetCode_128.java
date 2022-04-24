package com.leetcode.middle;

import java.util.Arrays;

/**
 * ClassName: LeetCode_128
 * Description:
 * date: 2022/4/24 10:32
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class LeetCode_128 {
    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        System.out.println(longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
        System.out.println(longestConsecutive(new int[]{1, 2, 0, 1}));
        System.out.println(longestConsecutive(new int[]{-7,7,-6,6,-1,2,5,-5,-2,8}));
    }

    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        sort(nums);
        System.out.println(Arrays.toString(nums));
        int max = 0;
        int pre = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 < nums.length && nums[i] + 1 == nums[i + 1]) max++;
            else if (i + 1 < nums.length && nums[i] == nums[i + 1]) continue;
            else {
                pre = Math.max(max, pre);
                max = 0;
            }
        }
        return pre + 1;
    }

    public static void sort(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjust(arr, i, arr.length );
        }
        for (int i = arr.length - 1; i > 0; i--) {
            arr[0] ^= arr[i];
            arr[i] ^= arr[0];
            arr[0] ^= arr[i];
            adjust(arr, 0, i);
        }
    }

    public static void adjust(int[] arr, int i, int length) {
        int tmp = arr[i];
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            if (j + 1 < length && arr[j] < arr[j + 1]) {
                j++;
            }
            if (tmp < arr[j]) {
                arr[i] = arr[j];
                i = j;
            } else {
                break;
            }
        }
        arr[i] = tmp;

    }
}
