package com.leetcode.middle;

import java.util.Arrays;

/**
 * ClassName: LeetCode_322
 * Description:
 * 322. 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * <p>
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 * <p>
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：coins = [1], amount = 0
 * 输出：0
 * <p>
 * date: 2022/4/10 10:47
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class LeetCode_322 {
    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{186, 419, 83, 408}, 6249));
    }


    // 动态规划求解
    public static int coinChange(int[] coins, int amount) {
        int max = Integer.MAX_VALUE;
        int[] dp = new int[amount + 1];
        //初始化dp数组为最大值
        for (int j = 0; j < dp.length; j++) {
            dp[j] = max;
        }
        //当金额为0时需要的硬币数目为0
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            //正序遍历：完全背包每个硬币可以选择多次
            for (int j = coins[i]; j <= amount; j++) {
                //只有dp[j-coins[i]]不是初始最大值时，该位才有选择的必要
                if (dp[j - coins[i]] != max) {
                    //选择硬币数目最小的情况
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        return dp[amount] == max ? -1 : dp[amount];
    }


    /**
     * @description: 一下为贪心算法求解
     * */
//    public static int coinChange(int[] coins, int amount) {
//        // 对coins进行排序
//        sort(coins, 0, coins.length - 1);
//        System.out.println(Arrays.toString(coins));
//        return process(coins, amount, 0, Integer.MAX_VALUE);
//    }
//
//    public static void sort(int[] arr, int left, int right) {
//        if (left >= right) return;
//        int l = left;
//        int r = right;
//        int pivot = arr[left];
//        while (true) {
//
//            while (arr[r] >= pivot && l < r) {
//                r--;
//            }
//            while (arr[l] <= pivot && l < r) {
//                l++;
//            }
//            if (r == l) {
//                arr[left] = arr[r];
//                arr[r] = pivot;
//                break;
//            }
//            arr[r] ^= arr[l];
//            arr[l] ^= arr[r];
//            arr[r] ^= arr[l];
//        }
//        sort(arr, left, l - 1);
//        sort(arr, l + 1, right);
//
//    }
//
//    // 从大到小匹配一定匹配的是最小的硬币树
//    public static int process(int[] coins, int rest, int coinNums, int min) {
//        if (rest == 0) {
//            min = Math.min(min, coinNums);
//            return min;
//        }
//        int coinsLen = coins.length - 1;
//        for (int i = coinsLen; i >= 0; i--) {
//            if (rest >= coins[i]) {
//                int res = process(coins, rest - coins[i], coinNums + 1, min);
//                // 找到立刻返回不做其他运算
//                if (res != -1) { // 过滤掉没找到的情况
//                    return res;
//                }
//            }
//        }
//        return -1;
//    }
}
