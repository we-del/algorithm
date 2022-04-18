package com.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * ClassName: LeetCode_1
 * Description:
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 * <p>
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 * <p>
 * date: 2022/4/10 18:22
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class LeetCode_1 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{-1, -2, -3, -4, -5}, -8)));
    }


    /**
     * @param nums   被查找数组
     * @param target 被查找数据目标和
     * @description: 得到一个数组中不同数字的和
     * @return: 所有满足条件的不相等的和
     */
//    public static List<List<Integer>> twoSum(int[] nums, int target) {
//        heapSort(nums);
//        System.out.println(Arrays.toString(nums));
//        int left = 0;
//        int right = nums.length - 1;
//        List<List<Integer>> list = new ArrayList<>();
//        while (left < right) {
//            left = filterSameNums(nums, left, false);
//            right = filterSameNums(nums, right, true);
//            if (nums[left] + nums[right] == target) {
//                ArrayList<Integer> integers = new ArrayList<>();
//                integers.add(nums[left]);
//                integers.add(nums[right]);
//                list.add(integers);
//                left++;
//                continue;
//            }
//            if (nums[left] + nums[right] < target) left++;
//            else right--;
//
//        }
//        System.out.println(list);
//        return null;
//    }

    /**
     * @param
     * @description:
     * @tips: 需要保留原位置的映射关系
     * @return:
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] ints = Arrays.copyOf(nums, nums.length);
        heapSort(nums);
        int left = 0;
        int right = nums.length - 1;
        int[] res = new int[2];
        for (int i = 0; i < res.length; i++) {
            res[i] = -1;
        }
        while (left < right) {
            // 此时left和right移到了满足位置的下标
            if (nums[left] + nums[right] == target) {
                break;
            }
            if (nums[left] + nums[right] < target) left++;
            else right--;
        }
        for (int i = 0; i < ints.length; i++) {
            if (nums[left] == ints[i]) res[0] = i;
            if (res[0] != -1) break;
        }
        for (int i = 0; i < ints.length; i++) {
            if (nums[right] == ints[i] && res[0] != i) res[1] = i;
            if (res[1] != -1) break;
        }
        return res;
    }

    /**
     * @param arr    判断移动位置的数据
     * @param i      所在的位置
     * @param direct 为true说明从右往左，反之，从左往右
     * @description: boolean 可以同时说明两种状态 , int 可以同时说明n种状态(需配合switch或if)
     * @return: 合适的位置
     */
    public static int filterSameNums(int[] arr, int i, boolean direct) {
        if (direct) { // 从右往左
            while (i - 1 >= 0 && arr[i] == arr[i - 1]) i--;
        } else {
            while (i + 1 <= arr.length - 1 && arr[i] == arr[i + 1]) i++;
        }
        return i;
    }

    public static void heapSort(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjust(arr, i, arr.length);
        }
        for (int i = arr.length - 1; i > 0; i--) {
            arr[0] ^= arr[i];
            arr[i] ^= arr[0];
            arr[0] ^= arr[i];
            adjust(arr, 0, i);
        }
    }

    private static void adjust(int[] arr, int i, int length) {
        int val = arr[i];
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            if (j + 1 < length && arr[j] < arr[j + 1]) j++;
            if (val < arr[j]) {
                arr[i] = arr[j];
                i = j;
            } else break;
        }
        arr[i] = val;
    }

}
