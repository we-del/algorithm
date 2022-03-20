package com.data_structure.recursion;

/**
 * ClassName: MazeIssue
 * Description:
 * date: 2022/2/12 15:54
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class MazeIssue {
    public static void main(String[] args) {
        int[][] maze = createMaze(10, 10);
        int col = 0; // 起点
        int endCol = 0; // 终点

        // 获得终点坐标
        for (int i = 1; i < maze.length; i++) {
            if (maze[1][i] == 2) {
                col = i;
                break;
            }
        }

        // 获得起点坐标
        for (int i = 1; i < maze.length; i++) {
            if (maze[maze[i].length - 2][i] == 2) {
                endCol = i;
                break;
            }
        }

        // 显示本次地图
        for (int[] ints : maze) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

        outOfMaze(maze, 1, col, endCol, 0, 0);
    }

    public static int demo(int n) {
        if (n == 0) {
            return -1;
        }
        return demo(n - 1);

    }

    public static int[][] createMaze(int row, int col) {
        int[][] maze = new int[row][col];

        // 创建一个正方形的迷宫
        for (int i = 0; i < row; i++) {
            maze[0][i] = 1;
            maze[row - 1][i] = 1;
            maze[i][0] = 1;
            maze[i][col - 1] = 1;
        }

        // 随机生成障碍物
        for (int j = 0; j < 6; j++) {
            int randomRow = (int) (Math.random() * (col - 2)) + 1; // 防止障碍物和迷宫边界重合
            int randomCol = (int) (Math.random() * (col - 2)) + 1;
            maze[randomRow][randomCol] = 1;
        }

        // 随机生成起点
        maze[1][(int) (Math.random() * (col - 2)) + 1] = 2;
        // 随机生成终点
        maze[row - 2][(int) (Math.random() * (col - 2)) + 1] = 2;
        return maze;
    }

    public static int[][] outOfMaze(int[][] maze, int row, int col, int endCol, int oldRow, int oldCol) {
        System.out.println("row:"+row+"\tmaze.length-1："+(maze.length-2)+"\tcol:"+col+"\t endCol:"+endCol);
        // 走出迷宫
        if (row == maze.length - 2 && col == endCol) {
            System.out.println("以走出迷宫，路线为");
            for (int[] ints : maze) {
                for (int anInt : ints) {
                    System.out.print(anInt + " ");
                }
                System.out.println();
            }

            return maze;
        }
        /*
         * 策略 ： 下-->右 -->上 -->左
         */
        if (maze[row + 1][col] != 1) {
            maze[row + 1][col] = 2;
            return outOfMaze(maze, row + 1, col, endCol, 0, 0);
        } else if (maze[row][col + 1] != 1) {
            maze[row][col + 1] = 2;
            return outOfMaze(maze, row, col + 1, endCol, 0, 0);
        } else if (maze[row - 1][col ] != 1) {
            maze[row - 1][col] = 2;
            return outOfMaze(maze, row, col - 1, endCol, 0, 0);
        } else if (maze[row][col- 1] != 1) {
            maze[row][col- 1] = 2;
            return outOfMaze(maze, row - 1, col, endCol, 0, 0);

        } else {
            // 走进了死胡同
            maze[row][col] = 3;
            for (int[] ints : maze) {
                for (int anInt : ints) {
                    System.out.print(anInt + " ");
                }
                System.out.println();
            }
        }
        return null;
    }
}

