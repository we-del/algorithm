package com.leetcode.middle;

/**
 * ClassName: DynamicStrategy_198
 * Description:
 * 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 * <p>
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * <p>
 * date: 2022/3/20 10:20
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class DynamicStrategy_198 {
    public static void main(String[] args) {
        System.out.println(new DynamicStrategy_198().rob(new int[]{1,2,4,6,7,8,9}));
    }

    /**
     * 解题思路：因为其不能堆相邻的两个数进行动态规划，因此需要奇偶两列来进行（求两个相邻房间最大数）
     */
//    public int rob(int[] nums) {
//        // 长度过小的情况
//        if (nums.length <= 3) {
//            switch (nums.length) {
//                case 3: // 0 1 2
//                    return nums[0] + nums[2] < nums[1] ? nums[1] : nums[0] + nums[2];
//
//                case 2:
//                    return nums[0] < nums[1] ? nums[1] : nums[0];
//                case 1:
//                    return nums[0];
//                case 0:
//                    return 0;
//            }
//        }
//
//        int odd = 0;
//        int even = 0;
//        for (int i = 2, j = 3; i < nums.length && j < nums.length; i += 2, j += 2) {
//            if (odd < nums[i] + nums[i - 2]) odd = nums[i] + nums[i - 2];
//            if (even < nums[j] + nums[j - 2]) odd = nums[i] + nums[i - 2];
//        }
//        return odd < even ? even : odd;
//    }

    // 求奇偶最大数
    public int rob(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return len == 1 ? nums[0] : 0;
        }
        int[] tmp = new int[len];
        tmp[0] = nums[0];
        tmp[1] = Math.max(tmp[0], nums[1]);
        for (int i = 2; i < len; i++) {
            // tmp[i-1] 为当前路径的最大数    tmp[i - 2] + nums[i] 为最新路径的数
            // 将这两个数做比较即可拿到最大的不相连数和
            tmp[i] = Math.max(tmp[i - 1], tmp[i - 2] + nums[i]);
        }
        return tmp[len - 1];  // 返回最大路径数
    }
}
