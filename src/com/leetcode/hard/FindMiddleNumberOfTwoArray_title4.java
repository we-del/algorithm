package com.leetcode.hard;

/**
 * ClassName: FindMiddleNumberOfTwoArray_title4
 * Description:4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * <p>
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * <p>
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * <p>
 * <p>
 * date: 2022/3/10 14:45
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class FindMiddleNumberOfTwoArray_title4 {
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        int[] arr1 = {1, 3, 5, 6, 7, 8};
        int[] arr2 = {4, 5, 5, 7, 7, 9,10};
        int a1Len = arr1.length;
        int a2Len = arr2.length;
        int[] result = new int[a1Len + a2Len];

        int a1 = 0;
        int a2 = 0;
        int index = 0;
        while (a1 < a1Len && a2 < a2Len) {
            if (arr1[a1] < arr2[a2]) {
                result[index++] = arr1[a1++];
            } else {
                result[index++] = arr2[a2++];
            }
        }
        while (a1 < a1Len) {
            result[index++] = arr1[a1++];
        }
        while (a2 < a2Len) {
            result[index++] = arr2[a2++];
        }
        int mid = result.length / 2;
        if (result.length % 2 == 0) {
            System.out.println("中位数为" + (result[mid] + result[mid - 1]) / 2.0);
        } else {
            System.out.println("中位数为" + (result[mid]));
        }
    }
}
