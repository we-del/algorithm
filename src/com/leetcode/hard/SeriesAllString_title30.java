package com.leetcode.hard;

import java.util.*;

/**
 * ClassName: SeriesAllString_title30
 * Description:
 * 30. 串联所有单词的子串
 * 给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * <p>
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 * <p>
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 * <p>
 * date: 2022/3/15 10:44
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class SeriesAllString_title30 {
    public static void main(String[] args) {
        System.out.println(findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        if (words == null) return null;

        // 使用 need 保存需要匹配字符串的个数
        Map<String, Integer> need = new HashMap<>();

        // 如果该字符串再hashMap中不存在，则记录该字符串为1次，如果存在则再原先记录的基础上+1
        for (String word : words) need.put(word, need.get(word) == null ? 1 : need.get(word) + 1);
        int len = words[0].length(); // 获得需要匹配字符串的长度
        List<Integer> list = new ArrayList<>(); // 用于存储字符开始的位置
        for (int i = 0; i < len; i++) {
            Map<String, Integer> window = new HashMap<>();
            int left = i, right = i, count = 0;
            while (right <= s.length() - len) {
                String str = s.substring(right, right + len);
                right += len;
                if (need.containsKey(str)) {
                    window.put(str, window.get(str) == null ? 1 : window.get(str) + 1);
                    if (need.get(str).equals(window.get(str))) count++;
                }
                // 如果窗口中的字符长度恰好等于所需要的字符长度 则判断是否有满足单词全匹配
                if ((right - left) / len == words.length) {
                    if (count == need.size()) list.add(left);
                    String str1 = s.substring(left, left + len);
                    left += len;
                    if (need.containsKey(str1)) {
                        if (need.get(str1).equals(window.get(str1))) count--;
                        // 删除窗口中的字符串
                        window.put(str1, window.get(str1) - 1);
                    }
                }
            }
        }
        return list;
    }
}
