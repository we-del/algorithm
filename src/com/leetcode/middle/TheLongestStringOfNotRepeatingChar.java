package com.leetcode.middle;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * ClassName: TheMostStringOfNotrepeatingChar
 * Description:  3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * <p>
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * date: 2022/3/10 14:17
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class TheLongestStringOfNotRepeatingChar {
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        String str = "pwwkew";
        ArrayList<String> list = new ArrayList<>();
        int index = 0;
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            if (result.indexOf(str.charAt(i)) == -1) {
                result += str.charAt(i);
            } else {
                list.add(result);
                result = "";
                i--;
            }
            if (i == str.length() - 1) {
                list.add(result);
            }
        }

        result = list.get(0);
        for (String s : list) {
            if (result.length() < s.length()) {
                result = s;
            }
        }
        System.out.println(list);
        System.out.println("最长不重复子序列长度为：" + result.length() + " ; 值为" + result);

    }
}
