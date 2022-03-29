package com.leetcode.middle;

/**
 * ClassName: DynamicStrategy_45
 * Description:
 * 45. 跳跃游戏 II
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * <p>
 * 假设你总是可以到达数组的最后一个位置。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 示例 2:
 * <p>
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 * <p>
 * <p>
 * date: 2022/3/26 12:08
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class DynamicStrategy_45 {
    private int max = 1000;

    public static void main(String[] args) {
        DynamicStrategy_45 d = new DynamicStrategy_45();
        System.out.println(d.jump(new int[]{3, 5, 0, 2, 0, 1, 2, 4}));
    }

    public int jump(int[] nums) {
        /**
         * 参数说明
         *  n 为数组长度
         *  cnt 为走的步数
         *  m1,pl初始值为数组第一个索引
         *  dp 为保存步数的数组
         * */
        int n = nums.length, cnt = 1, ml = nums[0], pl = nums[0];
        if (n == 1) return 0;
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            if (i <= pl) dp[i] = cnt;
            else {
                dp[i] = ++cnt;
                pl = ml;
            }
            ml = Math.max(ml, i + nums[i]);
        }
        return dp[n - 1];
    }


    // 回溯法
//    public int jump(int[] nums) {
//        process(nums, 0, 0);
//        System.out.println(max);
//        return max;
//    }
//
//    // 回溯法
//    public void process(int[] nums, int step, int n) {
//        if (step >= nums.length - 1) {
//            max = max > n ? n : max;
//            System.out.println(max);
//            return;
//        }
//        for (int i = step; i < nums[step]+n && i < nums.length; i++) {
//            if (nums[i] == 0) return;
//            process(nums, nums[i] + i, i + 1);
//        }
//    }
}
