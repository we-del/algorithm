package com.leetcode.easy;

/**
 * ClassName: LeetCode_28
 * Description:
 * 28. 实现 strStr()
 * 实现 strStr() 函数。
 * <p>
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
 * <p>
 * <p>
 * <p>
 * 说明：
 * <p>
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：haystack = "hello", needle = "ll"
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：haystack = "aaaaa", needle = "bba"
 * 输出：-1
 * <p>
 * date: 2022/4/17 10:31
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class LeetCode_28 {
    public static void main(String[] args) {
        System.out.println(kmp("ll", "hello"));
    }

    private static int kmp(String pattern, String match) {
        int[] next = getNext(pattern);
        for (int i = 0, j = 0; i < match.length(); i++) {
            while (j > 0 && pattern.charAt(j) != match.charAt(i)) j = next[j - 1];
            if (pattern.charAt(j) == match.charAt(i)) j++;
            if (j == pattern.length()) return i - j + 1;
        }
        return -1;
    }

    private static int[] getNext(String pattern) {
        int[] next = new int[pattern.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(j) != pattern.charAt(i)) j = next[j - 1];
            if (pattern.charAt(j) == pattern.charAt(i)) j++;
            next[i] = j;
        }
        return next;
    }
}
