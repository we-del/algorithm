package com.leetcode.easy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName: LeetCode_9
 * Description:
 * 9. 回文数
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * <p>
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 例如，121 是回文，而 123 不是。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 121
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3：
 * <p>
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 * <p>
 * date: 2022/4/19 11:22
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class LeetCode_9 {
    public static void main(String[] args) {
        System.out.println(isPalindrome(92392));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        Pattern pattern = Pattern.compile("(\\d+)\\d\\1");
        Matcher matcher = pattern.matcher(x + "");
        return matcher.matches();
    }
}
