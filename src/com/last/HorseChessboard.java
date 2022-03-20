package com.last;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * ClassName: HorseChessboard
 * Description:
 * date: 2022/3/9 15:42
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class HorseChessboard {
    private static int X; // 行
    private static int Y; // 列
    // 创建一个数组，标记期盼的各个位置是否被访问过
    private static boolean visited[];
    private static boolean finished;

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        start();
        long t2 = System.currentTimeMillis();
        System.out.println("消耗时间：" + (t2 - t1));
    }

    public static void start() {
        /**
         *  常规算法 非常消耗时间(1min左右)，因为其是常规算法，使用贪心算法可以提高效率
         * */
        // 测试
        X = 7;
        Y = 7;
        
        // 马开始的位置
        int row = 3;
        int col = 3;
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];

        traversalChessboard(chessboard, row - 1, col - 1, 1);

        for (int[] ints : chessboard) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * @param chessboard 棋盘
     * @param row        马的行位置
     * @param col        马的列位置
     * @param step       走的第几步
     */
    public static void traversalChessboard(int[][] chessboard, int row, int col, int step) {
        chessboard[row][col] = step;
        // row * X + col 为一个小算法， 可以定位到指定位置
        visited[row * X + col] = true; // 把当前位置标记为走过
        // 获得当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(col, row));

        sort(ps); // 对ps进行排序，这样可以优先走选择多的步数

        while (!ps.isEmpty()) {
            Point p = ps.remove(0);

            // 如果这一步没有走过。则走
            if (!visited[p.y * X + p.x]) {

                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }

        }

        // 如果没走通，则棋盘重置，开始回溯
        if (step < X * Y && !finished) {
            chessboard[row][col] = 0;
            visited[row * X + col] = false;
        } else {
            finished = true;
        }

    }

    // 获得该马可以走的位置，并存入到集合中
    public static ArrayList<Point> next(Point curPoint) {
        // 创建一个ArrayList
        ArrayList<Point> ps = new ArrayList<>();

        Point p1 = new Point();
        // 假设此时马再中间。此时可以走8各位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    // 使用贪心算法优化
    // 根据当前这异步的所有下一步的选择位置，进行非递减排序
    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                // 获取到o1的下一步的所有位置个数
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                if (count1 < count1) { // 返回 -1 表示 o2排在o1前面
                    return -1;
                } else if (count1 == count2) { // 返回 0 表示 位置不变
                    return 0;
                } else { // 返回1 表示 o1排再o2前面
                    return 1;
                }
            }
        });
    }
}
