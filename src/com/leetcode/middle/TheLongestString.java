package com.leetcode.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: TheLongestString
 * Description:5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 * <p>
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * <p>
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 * date: 2022/3/11 10:47
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class TheLongestString {
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        String s = "ee1wabcdwabeebabc";
        List<String> list = new ArrayList<>();
        String tmp = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {

                // 如果为-1表示不存在则加入
                if (tmp.indexOf(s.charAt(j)) == -1) {
                    tmp += s.charAt(j);
                } else {
                    // 否则存在 表示字串以找到
                    tmp += s.charAt(j);

                    // 从 0 位置开始截取目标
                    String result = tmp.substring(tmp.indexOf(s.charAt(j)), tmp.length());


                    list.add(result); // 添加到tmp中
                    tmp = ""; // tmp置null
                    break; // 退出这次循环开启下一个点的循环
                }

                // 表示加到了最后一个都没有相同的需要置空
                if (j == s.length() - 1) {
                    tmp = "";
                }
            }
        }
        tmp = list.get(0);
        for (String s1 : list) {
            if (tmp.length() < s1.length()) {
                tmp = s1;
            }
        }
        System.out.println("最长子串为" + tmp);
    }
}
