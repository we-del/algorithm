package com.leetcode.dynamic_planning;

/**
 * ClassName: Dynamic_64
 * Description:
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * <p>
 * date: 2022/4/5 11:38
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class Dynamic_64 {
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        minPathSum(grid);
    }

    public static int minPathSum(int[][] grid) {
        int res = process(0, 0, grid.length - 1, grid[0].length - 1, grid, 0);
        System.out.println(res);
        return res;

    }

    public static int process(int x, int y, int finalX, int finalY, int[][] grid, int value) {
        if (x == finalX && y == finalY) {
            min = Math.min(min, value + grid[x][y]);
            return min;
        }
        int res = Integer.MAX_VALUE;
        if (x == finalX) {
            res = Math.min(res, process(x, y + 1, finalX, finalY, grid, value + grid[x][y]));
        } else if (y == finalY) {
            res = Math.min(res, process(x + 1, y, finalX, finalY, grid, value + grid[x][y]));
        } else {
            res = Math.min(res, process(x + 1, y, finalX, finalY, grid, value + grid[x ][y]) +
                    process(x, y + 1, finalX, finalY, grid, value + grid[x][y]));
        }
        return res;
    }


}
