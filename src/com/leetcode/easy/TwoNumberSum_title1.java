package com.leetcode.easy;

import java.util.Arrays;

/**
 * ClassName: TwoNumberSum_title1
 * Description:给定一个整数数组 nums 和一个整数目标值 target，
 * 请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1]
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class TwoNumberSum_title1 {
    public static void main(String[] args) {
        int[] answer = answer();
        System.out.println(Arrays.toString(answer));
    }

    public static int[] answer() {
        int[] nums = {2, 7, 11, 15};
        int target = 26;
        int[] arr = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    arr[0] = i;
                    arr[1] = j;
                    return arr;
                }
            }
        }
        return null;
    }
}
