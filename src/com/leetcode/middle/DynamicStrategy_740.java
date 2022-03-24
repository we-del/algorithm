package com.leetcode.middle;

/**
 * ClassName: DynamicStrategy_740
 * Description:
 * 740. 删除并获得点数
 * 给你一个整数数组 nums ，你可以对它进行一些操作。
 * <p>
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。
 * <p>
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,4,2]
 * 输出：6
 * 解释：
 * 删除 4 获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 * 示例 2：
 * <p>
 * 输入：nums = [2,2,3,3,3,4]
 * 输出：9
 * 解释：
 * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 * <p>
 * date: 2022/3/24 10:32
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class DynamicStrategy_740 {
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        System.out.println(deleteAndEarn(new int[]{2, 2, 3, 3, 3, 4}));
    }

    // 此问题可以转换为一个打家劫舍问题,因为相邻的数可以看作是一个相同的邻居，把所有相同的数进行累加即可得到最大值
    public static int deleteAndEarn(int[] nums) {
        int[] trans = new int[10001];
        for (int i = 0; i < nums.length; i++) {
            // trans[2] = 4;
            // 即在索引位置进行累加
            trans[nums[i]] += nums[i];
        }

        int[] dp = new int[10001];

        dp[0] = 0;
        dp[1] = trans[1];
        for (int i = 2; i < trans.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + trans[i]);
        }

        return dp[dp.length - 1];
    }
}
