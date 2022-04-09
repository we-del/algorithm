package com.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: LeetCode_448
 * Description:
 * 448. 找到所有数组中消失的数字
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[5,6]
 * 示例 2：
 * <p>
 * 输入：nums = [1,1]
 * 输出：[2]
 * <p>
 * date: 2022/4/9 11:55
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class LeetCode_448 {
    public static void main(String[] args) {
        List<Integer> disappearedNumbers = findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1});
        for (Integer disappearedNumber : disappearedNumbers) {
            System.out.println(disappearedNumber);
        }
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        boolean[] verify = new boolean[nums.length + 1];
        for (int num : nums) {
            verify[num] = true;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < verify.length; i++) {
            if (!verify[i]) list.add(i);
        }
        return list;
    }
}
