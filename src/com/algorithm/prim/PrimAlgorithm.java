package com.algorithm.prim;

import java.util.Arrays;

/**
 * ClassName: PrimAlgorithm
 * Description:
 * date: 2022/3/8 10:18
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertexNUms = data.length;
        int[][] weight = {
                {100, 5, 7, 100, 100, 100, 2},
                {5, 100, 100, 9, 100, 100, 3},
                {7, 100, 100, 100, 8, 100, 100},
                {100, 9, 100, 100, 100, 4, 100},
                {100, 100, 8, 100, 100, 5, 4},
                {100, 100, 100, 4, 5, 100, 6},
                {2, 3, 100, 100, 4, 5, 100},
        };
        MGraph mGraph = new MGraph(vertexNUms);
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, vertexNUms, data, weight);
      //  minTree.showGraph(mGraph);
        minTree.prim(mGraph,0);
    }
}

class MinTree {

    // 创建一个图
    public void createGraph(MGraph graph, int vertexNums, char data[], int[][] weight) {
        for (int i = 0; i < vertexNums; i++) {
            graph.data[i] = data[i];
            for (int j = 0; j < vertexNums; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }

    }

    // 显示图的连接矩阵
    public void showGraph(MGraph graph) {
        for (int[] ints : graph.weight) {
            System.out.println(Arrays.toString(ints));
        }
    }

    // 此方法每次会强制去找一个该连接点的最短路径顶点
    public void prim(MGraph graph, int v) {

        int visited[] = new int[graph.vertexNums]; // 用一个数组标记该路是否联通
        visited[v] = 1; // 将本次顶点进行标记
        int h1 = -1; // 保存本次顶点
        int h2 = -1; // 保存本顶点的路径最短的邻接顶点
        int minWeight = 100; // 用于当顶点间的隔离点
        for (int i = 1; i < graph.vertexNums; i++) { // 因为要找 n-1 次 路径所以1从 1开始循环

            for (int j = 0; j < graph.vertexNums; j++) { // 以第 1 个顶点开始查找
                for (int k = 0; k < graph.vertexNums; k++) { // 找到该顶点的最短路径的邻接顶点
                    // 每次找一个联通的值，且这个值未被连接，同时再此次查找中是最短的一个路径顶点
                    if (visited[j] == 1 && visited[k] == 0
                            && graph.weight[j][k] < minWeight) {
                        minWeight = graph.weight[j][k];
                        h1 = j;
                        h2 = k;
                    }
                }
            }
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] +
                    "> 权值：" + minWeight);
            visited[h2] = 1; // 找到最短路径的邻接顶点，将其点置为 走过
            minWeight = 100; // 恢复初始值
        }
    }
}

class MGraph {
    int vertexNums;
    char[] data; // 存放节点数据
    int[][] weight; // 村方便

    // 初始化图
    public MGraph(int size) {
        vertexNums = size;
        data = new char[size];
        weight = new int[size][size];
    }


}
