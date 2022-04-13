package com.leetcode.dynamic_planning.segment_fault;

/**
 * ClassName: ContinuousSubstringForMultiple
 * Description:
 * 152. 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * <p>
 * 测试用例的答案是一个 32-位 整数。
 * <p>
 * 子数组 是数组的连续子序列。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * date: 2022/4/12 10:04
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class ContinuousSubstringForMultiple {
    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{2, 3, -2, -4}));
    }

    public static int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        int maxNum = 1, minNum = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                maxNum ^= minNum;
                minNum ^= maxNum;
                maxNum ^= minNum;
            }
            maxNum = Math.max(nums[i] * maxNum, nums[i]);
            minNum = Math.min(nums[i] * minNum, nums[i]);
            max = Math.max(maxNum, max);
        }
        return max;
    }
}
