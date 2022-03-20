package com.leetcode.easy;

/**
 * ClassName: DynamicStrategy
 * Description:
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * 示例 2：
 * <p>
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 * <p>
 * date: 2022/3/19 11:46
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class DynamicStrategy_UpStair {
    public static void main(String[] args) {
        DynamicStrategy_UpStair d = new DynamicStrategy_UpStair();
        System.out.println("d.climbStairs(5) = " + d.climbStairs(5));
    }

    public int climbStairs(int n) {
        int n1 = 1;
        int n2 = 2;
        switch (n) {
            case 1:
                return n1;
            case 2:
                return n2;
            case 0:
                return 0;
        }
        int n3 = 0;
        for (int i = 3; i <= n; i++) {
            n3 = n1 + n2;
            n1 = n2;
            n2 = n3;
        }
        return n3;
    }
}
