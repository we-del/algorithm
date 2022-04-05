package com.leetcode.dynamic_planning;

/**
 * ClassName: DynamicPlan_91
 * Description:
 * 91. 解码方法
 * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 * <p>
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 * <p>
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 * <p>
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 * <p>
 * 题目数据保证答案肯定是一个 32 位 的整数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "12"
 * 输出：2
 * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2：
 * <p>
 * 输入：s = "226"
 * 输出：3
 * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * 示例 3：
 * <p>
 * 输入：s = "0"
 * 输出：0
 * 解释：没有字符映射到以 0 开头的数字。
 * 含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
 * 由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
 * <p>
 * date: 2022/4/4 11:09
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class DynamicPlan_91 {
    public static void main(String[] args) {
        // 1 12 | 11 2 | 1 1 2
        System.out.println(new DynamicPlan_91().numDecodings("1122", 0));
        System.out.println(new DynamicPlan_91().numDecodings("1122"));
    }

    public int numDecodings(String s, int index) {
        if (index >= s.length()) return 1;
        if (s.charAt(index) == '0') return 0;
        int res = 0;
        if ('1' == s.charAt(index)) {
            res += numDecodings(s, index + 1);
            res += numDecodings(s, index + 2);
        } else if ('2' == s.charAt(index)) {
            res += numDecodings(s, index + 1);
            if (index + 1 < s.length() && s.charAt(index + 1) >= '0' && s.charAt(index + 1) <= '6') {
                res += numDecodings(s, index + 2);
            }
        } else {
            res += numDecodings(s, index + 1);
        }
        return res;
    }

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {

            if (str[i] == '0') {
                dp[i] = 0;
            } else if (str[i] == '1') {
                dp[i] = dp[i + 1];
                if (i + 1 < str.length) {
                    dp[i] += dp[i + 2];
                }
            } else if (str[i] == '2') {
                dp[i] = dp[i + 1];
                if (i + 1 < str.length && str[i + 1] >= '0' && str[i + 1] <= '6') {
                    dp[i] += dp[i + 2];
                }
            } else {
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }
}
