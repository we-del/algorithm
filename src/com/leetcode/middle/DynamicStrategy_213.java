package com.leetcode.middle;

/**
 * ClassName: DynamicStrategy_213
 * Description:
 * 213. 打家劫舍 II
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 3：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：3
 * <p>
 * date: 2022/3/23 11:42
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class DynamicStrategy_213 {
    public static void main(String[] args) {
        System.out.println(new DynamicStrategy_213().rob(new int[]{1, 2, 3, 1}));
    }

    // 环形偷盗 主要思想为 分两条道路比较大小
    public int rob(int[] nums) {
        int len = nums.length;
        if (len < 0) return 0;
        if (len <= 1) return nums[0];
        if (len <= 2) return Math.max(nums[0], nums[1]);
        return Math.max(loop(nums, 0, len - 1), loop(nums, 1, len));
    }

    public int loop(int[] arr, int l, int r) {
        int[] dp = new int[r - l];
        dp[0] = arr[l];
        dp[1] = Math.max(dp[0], arr[l + 1]);
        for (int i = l + 2, j = 2; i < r; i++, j++) {
            dp[j] = Math.max(dp[j - 1], dp[j - 2] + arr[i]);
        }
        return dp[r - l - 1];
    }
}
