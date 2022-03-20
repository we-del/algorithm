package com.utils;

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
        //输出
        int sum1 = 0;
        double constant = 1.0;
        double count = 0.0;
        for (sum1 = 1; sum1 <= 100; sum1++) {
            count += constant / sum1;
            constant *= -constant;
        }
        System.out.println(count);
    }
}
