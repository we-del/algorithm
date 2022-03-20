package com.utils;

import java.util.Arrays;

/**
 * ClassName: TT
 * Description:
 * date: 2022/3/7 8:45
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class TT {
    public static void main(String[] args) {
        //求出1 - 1/2 + 1/3 - 1/4 + 1/5 - 1/6 .... 1/100
        //思路分析:
        //求和 sum 初始值等于0
        //定义 自增变量sum1 从2 - 100
        //for  循环输出 2-100
        //定义 控符变量sum2 (sum1%2)为不为0,从而控制符号

        process(new int[]{4, 2, 1, 3, 5, 9, 8, 7});
    }

    public static int[] process(int[] arr) {
        String odd = "";
        String even = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 1) {
                odd += arr[i] + " ";
            } else {
                even += arr[i] + " ";
            }
        }
        String[] s = odd.split(" ");
        String[] s1 = even.split(" ");
        System.out.println(Arrays.toString(s) + " "+Arrays.toString(s1));
        return null;
    }
}
