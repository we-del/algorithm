package com.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: LeetCode_169
 * Description:
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3,2,3]
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 * <p>
 * <p>
 * date: 2022/4/8 12:06
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class LeetCode_169 {
    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }

    public static int majorityElement(int[] nums) {
        if (nums.length == 0) return -1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num))
                map.put(num, 1);
            else map.put(num, map.get(num) + 1);
        }
        int maxNumber = -1;
        int maxIndex = -1;
        for (Map.Entry<Integer, Integer> number : map.entrySet()) {
            System.out.println(number.getKey() + " -> " + number.getValue());
            if (number.getValue() > maxNumber) {
                maxNumber = number.getValue();
                maxIndex = number.getKey();
            }
        }
        return maxIndex;
    }
}
