package com.leetcode.dynamic_planning;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: DynamicPlan_139
 * Description:
 * 139. 单词拆分
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 * <p>
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 * 示例 2：
 * <p>
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 * 注意，你可以重复使用字典中的单词。
 * 示例 3：
 * <p>
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 * <p>
 * date: 2022/4/2 9:37
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class DynamicPlan_139 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        String[] arr = new String[]{"car","ca","rs"};
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        System.out.println(wordBreak("cars", list));
    }

    // 暴力递归
    public static boolean wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0) {
            return true;
        }
        boolean flag = false;
        for (int i = 0; i < wordDict.size(); i++) {
            int patternLen = wordDict.get(i).length();
            if (patternLen <= s.length()) {

                String sub = s.substring(0, patternLen);

                if (sub.equals(wordDict.get(i))) {
                    if (!flag)
                        flag = wordBreak(s.substring(patternLen, s.length()), wordDict);
                    else{
                        return true;
                    }
                }
            }
        }
        return flag;
    }

    public static boolean process(String str, String[] pattern) {
        if (str.length() == 0) {
            return true;
        }
        boolean flag = false;
        for (int i = 0; i < pattern.length; i++) {
            String sub = str.substring(0, pattern[i].length());
            if (sub.equals(pattern[i])) {
                flag = process(str.substring(pattern[i].length(), str.length()), pattern);
            }
        }
        return flag;
    }
}
