package com.leetcode.dynamic_planning;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: DynamicPlan_5
 * Description:
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 * <p>
 * 输入：s = "cbbd"
 * 输出："bb"
 * <p>
 * date: 2022/4/6 10:58
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class DynamicPlan_5 {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
    }

    //    public static String longestPalindrome(String s) {
//        String res = "";
//        for (int i = 0; i < s.length(); i++) {
//            res = process(s, i, s.charAt(i), "" + s.charAt(i), new ArrayList<String>());
//        }
//        return res;
//    }
    public static String longestPalindrome(String s) {
        if (s.length() < 1) return "";
        if(s.length() == 1) return s.charAt(1)+"";
        String max = "";
        List<String> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            max += s.charAt(i);
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) != s.charAt(j)) {
                    max += s.charAt(j);
                } else {
                    max += s.charAt(j);
                    list.add(max);
                }
            }
            max = "";
        }
        String res = "";
        if (list.size() > 0) {

            res = list.get(0);
            for (String s1 : list) {
                res = res.length() < s1.length() ? s1 : res;
            }
        }
        return res;
    }

    public static String process(String s, int index, char startS, String maxString, List<String> list) {
        if (s.length() <= index) {
            String max = "";
            for (String s1 : list) {
                max = max.length() < s1.length() ? s1 : max;
            }
            return max;
        }
        String res = "";
        if (s.charAt(index) != startS) {
            maxString += s.charAt(index);
            res = process(s, index + 1, startS, maxString, list);
        } else {
            list.add(maxString + s.charAt(index));
            res = process(s, index + 2, s.charAt(index + 1), "", list);

        }
        return res;
    }
}
