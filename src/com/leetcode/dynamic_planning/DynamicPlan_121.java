package com.leetcode.dynamic_planning;

/**
 * ClassName: DynamicPlan_121
 * Description:
 * 121. 买卖股票的最佳时机
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * <p>
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2：
 * <p>
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * date: 2022/4/1 10:40
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class DynamicPlan_121 {
    public static void main(String[] args) {
        DynamicPlan_121 d = new DynamicPlan_121();
        System.out.println(d.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(d.process(new int[]{7, 1, 5, 3, 6, 4}, 0, 0, 0));
    }

    // 动态规划 ，解决买股票最佳时机
    public int maxProfit(int[] prices) {
        if(prices.length <= 1)
            return 0;
        int min = prices[0], max = 0;
        for(int i = 1; i < prices.length; i++) {
            max = Math.max(max, prices[i] - min); // 每次拿前一次最大的值和当前值比较，取本次最大值
            min = Math.min(min, prices[i]); // 每次拿前一次最小值和当前值比较，拿到最小值
        }
        return max;
    }


    // 蛮力法
//    public int maxProfit(int[] prices) {
//        int min = prices[0];
//        int index = 0;
//        for (int i = 0; i < prices.length; i++) {
//            if (min > prices[i]) {
//                min = prices[i];
//                index = i;
//            }
//        }
//        int max = prices[index];
//        for (int i = index; i < prices.length; i++) {
//            if (max < prices[i]) max = prices[i];
//        }
//        return max - min;
//    }


    // 暴力递归
    public int process(int[] prices, int index, int max, int min) {
        if (index == prices.length) {
            return max - min;
        }
        int res = 0;
        for (int i = index; i < prices.length; i++) {
            if (i + 1 < prices.length && prices[index] < prices[i + 1]) {
                max = prices[i + 1];
                min = prices[i];
                res += Math.max(process(prices, i + 1, max, min), res);
            }
        }
        return res;
    }
}
