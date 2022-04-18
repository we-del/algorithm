package com.algorithm.kmp;


/**
 * ClassName: KMP
 * Description:
 * date: 2022/3/7 18:51
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class KMP {
    public static void main(String[] args) {
        System.out.println(violentSearch("mississippi", "issip"));
        System.out.println(kmp("mississippi", "issip"));
        System.out.println("mississippi".indexOf("issip"));
        // =================================================================================
        int[] next = getNext("BAAA");
        System.out.println(kmpSearch("mississippi", "issip", next));
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



    public static int kmpSearch(String s1, String s2, int[] next) {
        for (int i = 0, j = 0; i < s1.length(); i++) {

            // 使用kmp查找公式
            while (j > 0 && s1.charAt(i) != s2.charAt(j)) {
                j = next[j - 1];
            }

            if (s1.charAt(i) == s2.charAt(j)) {
                j++;
            }
            if (j == s2.length()) {
                return i - j + 1;
            }
        }

        return -1;
    }

    public static int violentSearch(String s1, String s2) {
        int j = 0;
        for (int i = 0; i < s1.length(); i++) {

            // 如果相等代表匹配 ，此时 j++
            if (s1.charAt(i) == s2.charAt(j)) {
                j++;
            } else { // 如果不等 则需要重新进行匹配 j 置为 0

                if(j>0) i--;
                j = 0;
            }

            // 如果连续几个字符都相等，且j等于查找字符串的长度，说明已经找到返回即可
            if (j == s2.length()) {
                return i - j + 1;
            }

        }
        return -1;
    }

    public static int[] getNext(String str1) {
        int strLen = str1.length();
        int[] next = new int[strLen];

        // 第一个索引位置一定为0 因为其没有前缀和后缀
        next[0] = 0;
        for (int i = 1, j = 0; i < strLen; i++) {

            // 如果 j 位置的值和j位置的值不相等，则j获取next表中前一个位置的值，直到该位置的值相等或j为0时退出
            // kmp核心算法
            while (j > 0 && str1.charAt(i) != str1.charAt(j)) {
                j = next[j - 1];
            }

            //  如果条件满足 就说明 有一个相同的值,即存在匹配值(前缀&后缀)
            if (str1.charAt(i) == str1.charAt(j)) {
                j++;
            }
            next[i] = j; // 将j的值复制给i索引位置
        }
        return next;

    }
}