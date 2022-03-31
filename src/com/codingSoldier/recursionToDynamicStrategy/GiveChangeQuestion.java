package com.codingSoldier.recursionToDynamicStrategy;

/**
 * ClassName: GiveChangeQuestion
 * Description:
 * date: 2022/3/29 15:06
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class GiveChangeQuestion {
    public static void main(String[] args) {
        int[] arr = {1, 5, 10, 20, 50, 100};
        int index = 0;
        int rest = 200;
        System.out.println(process1(arr, index, rest));
        int len = arr.length;
        int[][] dp = new int[len + 1][rest + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(process2(arr, index, rest, dp));
        System.out.println(process3(arr, rest));
    }

    /**
     * @param arr   arr中存放着所有面值的钱币
     * @param index 存储着面值的索引
     * @param rest  剩余需要找零的面值
     * @return 为找零的方式
     * @description: 此方法为暴力递归求解
     */
    public static int process1(int[] arr, int index, int rest) {

        // 当index == arr.length时 说明此面值选择已经完成了
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        // 每次对应一个类型组合
        for (int i = 0; i * arr[index] <= rest; i++) {
            ways += process1(arr, index + 1, rest - (i * arr[index]));
        }
        return ways;
    }

    /**
     * @param arr   arr中存放着所有面值的钱币
     * @param index 存储着面值的索引
     * @param rest  剩余需要找零的面值
     * @param dp    缓存每个点位的数据，带递归再走到此位置时直接返回结果
     * @return 为找零的方式
     * @description: 此方法为动态规划-记忆线索化求解
     */
    public static int process2(int[] arr, int index, int rest, int[][] dp) {

        // 当index == arr.length时 说明此面值选择已经完成了
        if (dp[index][rest] != -1) { // 判断这个路线在本次递归是否走过，如果走过直接返回结果
            return dp[index][rest];
        }
        if (index == arr.length) {
            dp[rest][index] = rest == 0 ? 1 : 0; // 记录此路线的解
            return dp[index][rest];
        }

        int ways = 0;
        for (int i = 0; i * arr[index] <= rest; i++) {
            dp[index][rest] = process1(arr, index + 1, rest - (i * arr[index]));
            ways += dp[index][rest];
        }
        return ways;
    }

    /**
     * @param arr arr中存放着所有面值的钱币
     * @param aim 剩余需要找零的面值
     * @return 为找零的方式
     * @description: 此方法为经典动态规划 , 和记忆化搜索的时间复杂度相同 . 它是由暴力递归改写而来
     */
    public static int process3(int[] arr, int aim) {
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                // 有枚举行为的动态规划可以进一步划分
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    ways += dp[index + 1][rest - (zhang * arr[index])];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    /**
     * @param arr arr中存放着所有面值的钱币
     * @param aim 剩余需要找零的面值
     * @return 为找零的方式
     * @description: 此方法为优化枚举版动态规划 ,
     */
    public static int process4(int[] arr, int aim) {
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {

                dp[index][rest] = dp[index + 1][rest]; // 获得前一个货币
                if (rest - arr[index] >= 0) { // 如果不越界 则  获得前二个货币
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

}

