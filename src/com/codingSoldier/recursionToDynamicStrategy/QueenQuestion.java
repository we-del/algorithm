package com.codingSoldier.recursionToDynamicStrategy;

import java.util.Arrays;

/**
 * ClassName: QueenQuestion
 * Description:
 * date: 2022/3/28 12:41
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class QueenQuestion {
    public static void main(String[] args) {
        int n = 8;
        int[] arr = new int[n];
        System.out.println(process(arr, 0, n));
    }

    /**
     * @param arr 存储数列得数组
     * @param i 为当前在第几行开始摆放皇后
     * @param n 为最后得行数
     * */
    public static int process(int[] arr, int i, int n) {
        if (i == n) { // 此时摆放成功了
            System.out.println(Arrays.toString(arr));
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isLegal(arr, i, j)) { // 每次判断该值是否合法
                arr[i] = j;
                res += process(arr, i + 1, n); //每成功摆放一组，返回结果到此行
            }
        }
        return res; // 最终统计最后结果返回给调用者
    }

    /**
     * @param arr 已摆放位置得数组
     * @param i 当前准备摆放得行
     * @param j 当前准备方法行上得列位置
     * */
    public static boolean isLegal(int[] arr, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (arr[k] == j || Math.abs(i - k) == Math.abs(j - arr[k])) {
                //  arr[k] == j 说明 在同一列
                //  Math.abs(i - k) == Math.abs(j - arr[k]) 说明 在同一斜线
                return false;
            }
        }
        // 全部不冲突返回true
        return true;
    }

}
