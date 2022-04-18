package com.leetcode.easy;

import org.junit.Test;

/**
 * ClassName: LeetCode_14
 * Description:
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 * <p>
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 * date: 2022/4/16 11:38
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class LeetCode_14 {
    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
    }

    public static String longestCommonPrefix(String[] strs) {
        int index = 0;
        int minLen = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            minLen = Math.min(minLen, strs[i].length());
        }
        String res = "";
        for (int i = 0; i < minLen; i++) {
            index = 0;
            char c = strs[0].charAt(i);
            for (int j = 0; j < strs.length; j++) {
                if (!(strs[j].charAt(i) == c)) {
                    return res;
                }
            }
            res += c;
        }
        return res;
    }

    @Test
    public void e(){
        String str = "123.zip";
        System.out.println(str.substring(0,str.indexOf(".zip")));
        System.out.println(str.substring(str.indexOf(".zip"),str.length()));
    }
}
