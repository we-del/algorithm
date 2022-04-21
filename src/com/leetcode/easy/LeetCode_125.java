package com.leetcode.easy;

import java.util.Scanner;

/**
 * ClassName: LeetCode_125
 * Description:
 * date: 2022/4/20 9:13
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class LeetCode_125 {
    public static void main(String[] args) {
        System.out.println(isPalindrome("race a car"));
        Scanner scanner = new Scanner(System.in);
    }

    public static boolean isPalindrome(String s) {
        String str = "";
        s = s.toLowerCase();
        System.out.println(s);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            System.out.println(c);
            if (c >= 'a' && c <= 'z' || c>='0' && c<='9') {
                str += c + "";
            }
        }
        System.out.println(str);
        for (int i = 0, j = str.length() - 1; i <= j; i++, j--) {
            if (str.charAt(i) != str.charAt(j)) return false;
        }
        return true;
    }
}
