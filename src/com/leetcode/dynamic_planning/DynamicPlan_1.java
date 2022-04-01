package com.leetcode.dynamic_planning;

/**
 * ClassName: DynamicPlan_1
 * Description:
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案。
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
 * <p>
 * date: 2022/3/31 11:56
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class DynamicPlan_1 {
    public static void main(String[] args) {
        process();
    }

    public static int process() {
        int i = 0;
        int[] arr = {1,2,3};
        for (int j = 0; j <arr.length ; j++) {
            System.out.println(arr[j]);
        }
        while(i < arr.length){
            System.out.println(arr[i]);
            i++;
        }
        int j = 0 ;
        System.out.println(j);
        return -1;
    }
}
