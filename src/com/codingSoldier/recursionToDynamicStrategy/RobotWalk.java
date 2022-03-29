package com.codingSoldier.recursionToDynamicStrategy;

/**
 * ClassName: RebotWalk
 * Description:
 * date: 2022/3/28 14:59
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class RobotWalk {
    public static void main(String[] args) {
        int n = 7;
        int cur = 2;
        int rest = 5;
        int p = 3;
        // dp用于收集所有暴力递归不重复的结果  ,
        int[][] dp = new int[n + 1][rest + 1];

        // 将dp初始化
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(process(n, cur, rest, p, dp));
    }

    /**
     * @param n    总共有的位置数
     * @param cur  当前在的位置
     * @param rest 剩余的步数
     * @param p    最总的目标位置
     * @param dp   每次值的缓存
     * @return 返回全部能达到终点的方法
     * @description: 动态规划——记忆搜索化解法
     */
    public static int process(int n, int cur, int rest, int p, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }

        if (rest == 0) { // 当不能走时判断是否达到目标点
            dp[cur][rest] = cur == p ? 1 : 0;
            return dp[cur][rest];
        }
        if (cur == 1) { // 当前来到首位置，此时只能往右走
            dp[cur][rest] = process(n, cur + 1, rest - 1, p, dp);
            return dp[cur][rest];
        }
        if (cur == n) {  // 当来到末尾位置，此时只能往左走
            dp[cur][rest] = process(n, cur - 1, rest, p, dp);
            return dp[cur][rest];
        }

        // 此时在中间位置可以往两边走，因此值可以累加
        dp[cur][rest] = process(n, cur - 1, rest - 1, p, dp) + process(n, cur + 1, rest - 1, p, dp);
        return dp[cur][rest];
    }

//    /**
//     * @param n    总共有的位置数
//     * @param cur  当前在的位置
//     * @param rest 剩余的步数
//     * @param p    最总的目标位置
//     * @return 返回全部能达到终点的方法
//     * @description: 暴力递归解法
//     */
//    public static int process(int n, int cur, int rest, int p) {
//        if (rest == 0) { // 当不能走时判断是否达到目标点
//            return cur == p ? 1 : 0;
//        }
//        if (cur == 1) { // 当前来到首位置，此时只能往右走
//            return process(n, cur + 1, rest - 1, p);
//        }
//        if (cur == n) {  // 当来到末尾位置，此时只能往左走
//            return process(n, cur - 1, rest, p);
//        }
//
//        // 此时在中间位置可以往两边走，因此值可以累加
//        return process(n, cur - 1, rest - 1, p) + process(n, cur + 1, rest - 1, p);
//    }

}
