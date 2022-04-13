package com.leetcode.middle;

import java.util.Arrays;

/**
 * ClassName: LeetCode_56
 * Description:
 * 56. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 * <p>
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * <p>
 * <p>
 * date: 2022/4/11 10:34
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class LeetCode_56 {
    public static void main(String[] args) {
        merge(new int[][]{
                {1,3},{2,4},{5,7},{9,15},{8,13}
        });
    }

    public static int[][] merge(int[][] intervals) {

        // 完成每个索引上数组的排序
//        for (int[] interval : intervals) {
//            sort(interval);
//        }

        // 对每个二维数组进行排序，拿到每个数组最左边的一个值
        for (int[] interval : intervals) {
            System.out.println(Arrays.toString(interval));
        }

        // 创建每个数组的一个对应位置上的数据,并将每个数组的首索引收集到该数组中
        int[] arr = new int[intervals.length];
        int index = 0;
        for (int[] interval : intervals) {
            arr[index++] = interval[0];
        }
        // 对该首索引进行排序，此时即把每个二维数组放到对应的位置上
        sort(arr);
        for (int i = 0; i < arr.length; i++) {
        }
        System.out.println(Arrays.toString(arr));
        return null;
    }

    public static void sort(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            process(arr, i, arr.length - 1);
        }
        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] ^= arr[0];
            arr[0] ^= arr[i];
            arr[i] ^= arr[0];
            process(arr, 0, i);
        }
    }

    public static void process(int[] arr, int i, int length) {
        int tmp = arr[i];
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            if (j + 1 < length && arr[j] < arr[j + 1]) j++;
            if (tmp < arr[j]) {
                arr[i] = arr[j];
                i = j;
            } else {
                break;
            }
        }
        arr[i] = tmp;
    }
}
