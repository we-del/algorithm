package com.leetcode.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: LeetCode_17
 * Description:
 *
 *  17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例 1：
 *
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 *
 * 输入：digits = ""
 * 输出：[]
 * 示例 3：
 *
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *
 * date: 2022/4/10 10:06
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class LeetCode_17 {
    public static void main(String[] args){
        System.out.println(letterCombinations("29"));
    }
    public static  List<String> letterCombinations(String digits) {

        char[] c = digits.toCharArray();
        String[] sAll = new String[c.length];
        for (int i = 0; i < c.length; i++) {
            if(!(c[i]>=50 && c[i]<=57)) return null;
            String s ="";
            switch (c[i]){
                case '2':
                    s = "abc";
                    break;
                case '3':
                    s = "def";
                    break;
                case '4':
                    s = "ghi";
                    break;
                case '5':
                    s = "jkl";
                    break;
                case '6':
                    s = "mno";
                    break;
                case '7':
                    s = "pqrs";
                    break;
                case '8':
                    s = "tuv";
                    break;
                case '9':
                    s = "wxyz";
                    break;
            }
            sAll[i] = s;
        }

        System.out.println(Arrays.toString(sAll));
        List<String> list = new ArrayList<>();
        // 只有一个数字
        if(sAll.length== 1) {
            for (int i = 0; i < sAll[0].length(); i++) {
                list.add(sAll[0].charAt(i)+"");
            }
            return list;
        }
        for (int i = 1; i < sAll.length; i++) {
            for (int j = 0; j <sAll[i].length(); j++) {
                list.add(sAll[0].charAt(0)+""+sAll[i].charAt(j));
                list.add(sAll[0].charAt(1)+""+sAll[i].charAt(j));
                list.add(sAll[0].charAt(2)+""+sAll[i].charAt(j));
            }
        }
//        for (String s : list) {
//            System.out.println(s);
//        }
        return  list;
    }

    public static List<String> process(String[] str){
        return null;
    }
}
