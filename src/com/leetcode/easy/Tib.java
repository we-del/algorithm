package com.leetcode.easy;

/**
 * ClassName: Tib
 * Description:
 *  1137. 第 N 个泰波那契数
 * 泰波那契序列 Tn 定义如下：
 *
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 *
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 *
 * date: 2022/3/18 10:49
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class Tib {
    public static void main(String[] args){
        // 1 1 2 4 7 13 24
        int start = start(7);
        System.out.println(start);
    }
    public static int start(int n){
        if(n <= 0)return 0;
        int[] arr = new int[n+3];
        arr[0] = 1;
        arr[1] = 1;
        arr[2] = 2;
        for (int i = 3; i < n; i++) {
            arr[i] = arr[i-1]+arr[i-2]+arr[i-3];
        }
        return arr[n-1];

    }
}
