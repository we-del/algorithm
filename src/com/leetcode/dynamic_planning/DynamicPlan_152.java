package com.leetcode.dynamic_planning;

/**
 * ClassName: DynamicPlan_152
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
 * date: 2022/3/31 9:57
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class DynamicPlan_152 {
    public static void main(String[] args) {
        System.out.println(process(new int[]{2, 3, -2, 4}));
        System.out.println(process(new int[]{-2,0,-1}));
    }

    public static int process(int[] arr) {
        if(arr.length == 1) return  arr[0];
        int cur = 1;
        int max = -1;
        int res = 0;
        for (int i : arr) {
            if (cur * i > 0) {
                cur *= i;
                max = max > cur ? max : cur;
            } else {
                cur = i;
                res = Math.max(i,res);
            }
        }
        return max > res ? max : res;
    }
}
