package com.leetcode.middle;

/**
 * ClassName: SearchInTwoDimensionArray
 * Description:
 * 剑指 Offer 04. 二维数组中的查找
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 现有矩阵 matrix 如下：
 * <p>
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * <p>
 * <p>
 * date: 2022/3/19 11:07
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class SearchInTwoDimensionArray {
    public static void main(String[] args) {
        SearchInTwoDimensionArray s = new SearchInTwoDimensionArray();
        System.out.println(s.findNumberIn2DArray(
                new int[][]{
                        {1, 4, 7, 11, 15},
                        {2, 5, 8, 12, 19},
                        {3, 6, 9, 16, 22},
                        {10, 13, 14, 17, 24},
                        {18, 21, 23, 26, 30}
                }, 18));
    }

    // 蛮力法求解
//    public boolean findNumberIn2DArray(int[][] matrix, int target) {
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length; j++) {
//
//                if (target == matrix[i][j]) {
//                    return true;
//                }
//                if (target < matrix[i][j]) break;
//            }
//        }
//        return false;
//    }
    // 二分法求解
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {

            int left = 0;
            int right = matrix[i].length - 1;
            while (left <= right) {
                int mid = (right - left) / 2 + left;
                if (matrix[i][mid] == target) {
                    return true;
                }
                if (target < matrix[i][mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

            }
        }
        return false;
    }
}
