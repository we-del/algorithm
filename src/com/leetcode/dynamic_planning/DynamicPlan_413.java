package com.leetcode.dynamic_planning;

/**
 * ClassName: DynamicPlan_413
 * Description:
 * 413. 等差数列划分
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 * <p>
 * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 * <p>
 * 子数组 是数组中的一个连续序列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：3
 * 解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
 * 示例 2：
 * <p>
 * 输入：nums = [1]
 * 输出：0
 * <p>
 * date: 2022/4/4 10:46
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class DynamicPlan_413 {
    public static void main(String[] args) {
        System.out.println(new DynamicPlan_413().numberOfArithmeticSlices(new int[]{1, 2, 3, 4}));
    }

    public int numberOfArithmeticSlices(int[] nums) {
        if (nums.length <= 2) return 0;
        int res = 0;
        int add = 0;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                // 使用 res += ++ add 完成巧妙的完成了dp
                // 如果是连续的则说明有多种 1 2 3 4 为 3 种
                // 如果是连续的则说明有多种 1 2 3 4 5 为 6 种  123|234|345|1234|2345|12345
                // 如果是连续的则说明有多种 1 2 3 4 5 6 为 10 种  123|234|345|456|1234|2345|3456|12345|23456|123456

                res += ++add;
            } else {
                add = 0;
            }
        }
        return res;
    }
}
