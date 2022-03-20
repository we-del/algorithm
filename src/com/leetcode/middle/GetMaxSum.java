package com.leetcode.middle;

/**
 * ClassName: GetMaxSum
 * Description:
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * ❝
 * 输入: [-2,1,-3,4,-1,2,1,-5,4] 输出: 6
 * <p>
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * ❞
 * <p>
 * date: 2022/3/19 10:53
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class GetMaxSum {
    public static void main(String[] args) {
        start(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
    }

    private static void start(int[] arr) {
        // 使用 蛮力法求解 O(n^2)
        int maxNumber = arr[0];
        int[] indexCollect = new int[arr.length + 1];
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if(maxNumber < arr[i]+arr[j]){
                    maxNumber = arr[i]+arr[j];
                }
            }
        }
    }
}
