package com.leetcode.easy;

import java.util.LinkedList;

/**
 * ClassName: leetcode_20
 * Description:
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：s = "(]"
 * 输出：false
 * 示例 4：
 * <p>
 * 输入：s = "([)]"
 * 输出：false
 * 示例 5：
 * <p>
 * 输入：s = "{[]}"
 * 输出：true
 * <p>
 * date: 2022/4/6 12:07
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class leetcode_20 {
    public static void main(String[] args) {
        System.out.println(isValid("([)]"));
    }

    public static boolean isValid(String s) {
        if(s.length() <= 1) return false;
        switch (s.charAt(0)){
            case '}':
            case ']':
            case ')':
                return  false;
        }


        LinkedList<Character> list = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                case '[':
                case '{':
                    list.addFirst(s.charAt(i));
            }
            switch (s.charAt(i)) {
                case '}':
                    if (list.peek() == '{') {
                        list.pop();
                    } else {
                        return false;
                    }
                    break;
                case ']':
                    if (list.peek() == '[') {
                        list.pop();
                    } else {
                        return false;
                    }
                    break;
                case ')':
                    if (list.peek() == '(') {
                        list.pop();
                    } else {
                        return false;
                    }
                    break;
            }

        }
        return list.size() == 0;
    }
}
