package com.leetcode.easy;

/**
 * ClassName: NumberToSum
 * Description:
 * 求出 1+(1+2)+(1+2+3)
 * date: 2022/3/15 19:23
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class NumberToSum {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= i; j++) {
                sum+=j;
            }
        }
        System.out.println(sum);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
