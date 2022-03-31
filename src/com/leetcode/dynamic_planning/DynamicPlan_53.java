package com.leetcode.dynamic_planning;

/**
 * ClassName: DynamicPlan_53
 * Description:
 * 53. 最大子数组和
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 子数组 是数组中的一个连续部分。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 * <p>
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 * <p>
 * date: 2022/3/30 11:58
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class DynamicPlan_53 {
    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
       // System.out.println(maxSubArray(arr));
        System.out.println(process(arr));
    }

    public static int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0)
                sum += num;
            else
                sum = num;
            res = Math.max(res, sum);
        }
        return res;
    }

    public static int process(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int t = 0;
            for (int j = i; j < arr.length; j++) {
                if (max < t + arr[j]) {
                    max = t + arr[j];
                    t += arr[j];
                }else{
                    break;
                }
            }
        }
        System.out.println(max);
        return max;
    }

    // 蛮力 --> 暴力递归 --> 动态规划
    public static int process(int[] arr, int index, int curMax) {
        if (index == arr.length) {
            return curMax;
        }
        int res = 0;
        for (int i = index; i < arr.length; i++) {
            if (curMax < curMax + arr[i]) {
                res = process(arr, i + 2, curMax + arr[i]);
            }
        }
        return res;
    }
}
