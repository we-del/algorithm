package com.algorithm.Dijkstra;

import java.util.Arrays;

/**
 * ClassName: DijkstraAlgorithm
 * Description:
 * date: 2022/3/9 11:34
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        /**
         *  创建图  可以使用 一维数组(或集合) + 一个二维数组
         *  一维数组(或集合) 表示个位置上的顶点的值
         *  二维数组表示各顶点和其他顶点的连接关系
         * */
        char[] vertex = {'A', 'B', 'C', 'D', 'F', 'G'};
        final int N = 65535;
        int[][] matrix = {
                {N, 5, 7, N, N, N, 2},
                {5, N, N, 9, N, N, 3},
                {7, N, N, N, 8, N, N},
                {N, 9, N, N, N, 4, N},
                {N, N, 8, N, N, 5, 4},
                {N, N, N, 4, 5, N, 6},
                {2, 3, N, N, 4, 6, N}
        };
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        graph.dsj(6);
    }

}

class Graph {
    private char[] vertex;
    private int[][] matrix;
    private VisitedVertex vv;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showGraph() {
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }

    }


    public void dsj(int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index);
    }

    // 更新index下标顶点到周围顶点的距离和周围顶点的前驱节点
    private void update(int index) {
        int len = 0;
        for (int i = 0; i < matrix[index].length; i++) {
            // 触发顶点到index顶点的距离+index顶点到j顶点的距离的和
            len = vv.getDis(index) + matrix[index][i];

            if (!vv.in(i) && len < vv.getDis(i)) {
                vv.updatePre(i, index);
                vv.updatedDis(i, len);
            }
        }

    }
}

class VisitedVertex {
    // 记录
    public int[] already_arr;
    public int[] pre_visited;
    public int[] dis;

    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        Arrays.fill(dis, 65535);
        this.dis[index] = 0;
    }

    // 判断index顶点是否被访问
    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    // 更新出发顶点到index顶点的距离
    public void updatedDis(int index, int len) {
        dis[index] = len;
    }

    // 更新顶点的前驱为index节点
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    //返回触发顶点到index顶点的距离
    public int getDis(int index) {
        return dis[index];
    }


}