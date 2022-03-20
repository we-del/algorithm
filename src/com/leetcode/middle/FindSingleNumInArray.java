package com.leetcode.middle;

/**
 * ClassName: FindSingleNumInArray
 * Description:给定一个整数数组 nums 和一个整数目标值 target，
 * 请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class FindSingleNumInArray {
    public static void main(String[] args) {
        int[] arr = {6, 6, 6, 1, 1, 2, 3, 3, 2, 1, 2, 1, 2, 1, 4, 4, 5, 5};
        int n = 0;
        for (int i : arr) {
            n ^= i;
        }
        System.out.println(n);
        int t = n & (~n + 1);
        int n2 = 0;
        for (int i : arr) {
            if ((t & i) == 0) {
                n2 ^= i;
            }
        }
        System.out.println(n2);
//        n2 ^= n;
//        n ^= n2;
//        n2 ^= n;
        System.out.println((n2 ^ n) + "_" + n2 + "_" );
    }
}
