package com.leetcode.dynamic_planning.segment_fault;

/**
 * ClassName: ContinuousSubstring
 * Description:
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * dp的方法就是O(n)的方法。如果dp[i]表示以第i个结尾的最大序列和，而这个dp的状态方程为：
 * <p>
 * dp[0]=a[0]
 * dp[i]=max(dp[i-1]+a[i],a[i])
 * <p>
 * date: 2022/4/11 11:46
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class ContinuousSubstring {
    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Math.max(arr[i], dp[i - 1] + arr[i]);
        }
        System.out.println(dp[arr.length-1]);
    }
}
