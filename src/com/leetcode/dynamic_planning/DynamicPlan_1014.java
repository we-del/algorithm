package com.leetcode.dynamic_planning;

/**
 * ClassName: DynamicPlan_1014
 * Description:
 * 1014. 最佳观光组合
 * 给你一个正整数数组 values，其中 values[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的 距离 为 j - i。
 * <p>
 * 一对景点（i < j）组成的观光组合的得分为 values[i] + values[j] + i - j ，也就是景点的评分之和 减去 它们两者之间的距离。
 * <p>
 * 返回一对观光景点能取得的最高分。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：values = [8,1,5,2,6]
 * 输出：11
 * 解释：i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
 * 示例 2：
 * <p>
 * 输入：values = [1,2]
 * 输出：2
 * <p>
 * date: 2022/3/31 10:37
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class DynamicPlan_1014 {
    public static void main(String[] args) {
//        System.out.println(process(new int[]{1, 2}));
//        System.out.println(process(new int[]{1, 2}, 0, Integer.MIN_VALUE));
//        System.out.println(process(new int[]{1, 3, 5}));
        System.out.println(maxScoreSightseeingPair(new int[]{1, 3, 5}));
    }

    // 枚举法
    public static int process(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                max = Math.max(max, arr[i] + arr[j] + i - j);
            }
        }
        return max;
    }

    public static int maxScoreSightseeingPair(int[] A) {

        int left = A[0], res = Integer.MIN_VALUE;
        for (int j = 1; j < A.length; j++) {

            res = Math.max(res, left + A[j] - j);
            left = Math.max(left, A[j] + j);
        }
        return res;
    }

}
