package com.leetcode.dynamic_planning;

/**
 * ClassName: DynamicPlan_62
 * Description:
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * <p>
 * 问总共有多少条不同的路径？
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：m = 3, n = 7
 * 输出：28
 * <p>
 * date: 2022/4/5 10:20
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class DynamicPlan_62 {
    public static void main(String[] args) {
        int startM = 1;
        int startN = 1;
        int finalM = 3;
        int finalN = 2;
        System.out.println(new DynamicPlan_62().uniquePaths(startM, startN, finalM, finalN));
        int[][] dp = new int[finalM + 1][finalN + 1];
       // System.out.println(new DynamicPlan_62().uniquePaths(startM, startN, finalM, finalM, dp));
        System.out.println(new DynamicPlan_62().uniquePaths(startM, startN));
    }

    // 暴力递归
    public int uniquePaths(int m, int n, int finalM, int finalN) {
        if (m == finalM && n == finalN) {
            return 1;
        }
        int res = 0;
        if (m == finalM) {
            res += uniquePaths(m, n + 1, finalM, finalN);
        } else if (n == finalN) {
            res += uniquePaths(m + 1, n, finalM, finalN);
        } else {
            res += uniquePaths(m + 1, n, finalM, finalN) + uniquePaths(m, n + 1, finalM, finalN);
        }
        return res;
    }

    // 动态规划
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 1;
                else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    // 记忆搜索化
    public int uniquePaths(int m, int n, int finalM, int finalN, int[][] dp) {
        if (m == finalM && n == finalN) {
            dp[m - 1][n - 1] = 1;
            return dp[m - 1][n - 1];
        }
        if (dp[m - 1][n - 1] != 0) return dp[m - 1][n - 1];
        if (m == finalM) {
            dp[m - 1][n - 1] += uniquePaths(m, n + 1, finalM, finalN, dp);
        } else if (n == finalN) {
            dp[m - 1][n - 1] += uniquePaths(m + 1, n, finalM, finalN, dp);
        } else {
            dp[m - 1][n - 1] += uniquePaths(m + 1, n, finalM, finalN, dp) + uniquePaths(m, n + 1, finalM, finalN, dp);
        }
        return dp[m - 1][n - 1];
    }
//    public int uniquePaths(int m, int n) {
//        int[][] dp = new int[m+1][n+1];
//
//    }
}
