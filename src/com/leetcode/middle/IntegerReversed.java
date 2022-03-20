package com.leetcode.middle;

/**
 * ClassName: IntegerReversed
 * Description:
 * <p>
 * 7. 整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * <p>
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 123
 * 输出：321
 * 示例 2：
 * <p>
 * 输入：x = -123
 * 输出：-321
 * 示例 3：
 * <p>
 * 输入：x = 120
 * 输出：21
 * <p>
 * date: 2022/3/11 19:38
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class IntegerReversed {
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        int num = 123456789;
        int pivot = 0;
        if (num >= 0) {
            pivot = 1;
        } else {
            pivot = -1;
        }
        num *= pivot;
        int len = (num + "").length();
        int n = 1;
        while (len > 1) {
            n *= 10;
            len--;
        }
        int result = 0;
        int step = 1;
        while (n >= 1) {
            result += num / n % 10 * step;
            n /= 10;
            step *= 10;
        }
        result *= pivot;
        System.out.println("反转后的结果为：" + result);

    }
}
