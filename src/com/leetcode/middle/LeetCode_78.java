package com.leetcode.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: LeetCode_78
 * Description:
 * 78. 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * date: 2022/4/24 10:15
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class LeetCode_78 {
    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1, 2, 3}));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int j = i; j < nums.length; j++) {
                arrayList.add(nums[j]);
            }
            list.add(arrayList);
        }
        return list;
    }
}
