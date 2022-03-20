package com.algorithm.kruskal;

import com.sun.javafx.geom.Edge;

import java.util.Arrays;

/**
 * ClassName: KruskalALgorithm
 * Description:
 * date: 2022/3/8 15:35
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class KruskalAlgorithm {
    private int edgeNum;
    private char[] vertexes;
    private int[][] matrix;
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, INF, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };
        KruskalAlgorithm kruskal = new KruskalAlgorithm(vertexes, matrix);
        EData eData1 = new EData('A', 'B', 3);
        EData eData2 = new EData('B', 'A', 2);
        EData eData3 = new EData('C', 'A', 4);
        EData eData4 = new EData('B', 'E', 6);
        EData eData5 = new EData('Q', 'W', 5);
        EData eData6 = new EData('F', 'E', 1);
      //  EData[] eData = {eData1, eData2, eData3, eData4, eData5, eData6};
        kruskal.print();
        kruskal.kruskal();
    }

    public KruskalAlgorithm(char[] vertexes, int[][] matrix) {


        // 拷贝原数组里的数据到指定数组
        int vLen = vertexes.length;
        this.vertexes = new char[vLen];
        for (int i = 0; i < vLen; i++) {
            this.vertexes[i] = vertexes[i];
        }

        this.matrix = new int[vLen][vLen];
        for (int i = 0; i < vLen; i++) {
            for (int j = 0; j < vLen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        // 统计边的数量
        for (int i = 0; i < vLen; i++) {
            for (int j = 0; j < vLen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    // 打印
    public void print() {
        System.out.println("邻接矩阵");
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = 0; j < vertexes.length; j++) {
                System.out.printf("%12d", matrix[i][j]);

            }
        }
    }

    private int getPosition(char ch) {
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    // 获取图中边，放到EData[] 属猪中
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = i + 1; j < vertexes.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexes[i], vertexes[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    public void kruskal() {
        int index = 0;
        int[] ends = new int[edgeNum]; // 保存已有最小生成数
        // 创建结果数组，保存最小生成树
        EData[] rets = new EData[edgeNum];

        EData[] edges = getEdges();
        System.out.println("图的边的集合" + Arrays.toString(edges) + "共" + edges.length);

        sortEdges(edges, 0, edges.length-1);

        for (int i = 0; i < edgeNum; i++) {
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);

            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);

            if (m != n) {
                ends[m] = n;
                rets[index++] = edges[i];
            }
        }

        System.out.println("最小生成树为" + Arrays.toString(rets));
    }


    // 对边进行排序处理，快排
    public void sortEdges(EData[] edges, int l, int r) {
        if (l < r) {
            int left = l;
            int right = r;

            EData pivot = edges[left];
            while (true) {
                while (l != r && edges[r].weight >= pivot.weight) {
                    r--;
                }
                while (l != r && edges[l].weight <= pivot.weight) {
                    l++;
                }
                EData tmp = null;
                if (l == r) {
                    tmp = pivot;
                    edges[left] = edges[l];
                    edges[l] = tmp;
                    break;
                }
                tmp = edges[l];
                edges[l] = edges[r];
                edges[r] = tmp;
            }
            sortEdges(edges, left, l - 1);
            sortEdges(edges, l + 1, right);
        }
    }
}

class EData {
    public char start;
    public char end;
    public int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }


    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }

}
