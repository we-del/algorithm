package com.data_structure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * ClassName: MyPractice
 * Description:
 * date: 2022/3/3 15:56
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class Graph {
    /**
     * @description: 使用 ArrayList保存每个顶点，使用 edges[][] 来保存边与边的联系，1为连接，0为不连接
     * ArrayList中索引的关系和edges的索引关系对应，如ArrayList中 0 位置为 "A",则edges中0位置也表示为 "A"
     */
    public static void main(String[] args) {
        String[] vertex = {"A", "B", "C", "D", "E"};
        int n = 5;
        Graph graph = new Graph(n);
        for (String s : vertex) {
            graph.insertVertex(s);
        }

        // 建立边
        graph.buildEdge(0, 1);
        graph.buildEdge(0, 2);
        graph.buildEdge(1, 2);
        graph.buildEdge(2, 3);
        graph.buildEdge(2, 4);
        graph.buildEdge(3, 1);
        graph.buildEdge(3, 4);
        //  myPractice.list();
        graph.dfs();
        System.out.println();
        graph.bfs();
    }

    private ArrayList<String> vertex; // 保存每个顶点
    private int[][] edges; // 和ArrayList中的索引对应，表示边和边的连接关系
    private int edgesNum; // 边的数量
    private boolean[] isVisited; // 记录某个节点是否被访问

    public Graph(int n) {
        vertex = new ArrayList<>(n);
        edges = new int[n][n];
        isVisited = new boolean[n];
    }

    // 得到第一个邻接节点的下标
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertex.size(); i++) {
            if (edges[index][i] > 0) {
                return i; // 返回被连接的节点下标
            }
        }
        return -1; // 该顶点没有连接的顶点
    }

    // 根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertex.size(); i++) {
            if (edges[v1][i] > 0) { // 从本顶点的下一个节点开始查找
                return i;
            }
        }
        return -1;
    }

    // 得到所有端点长度
    public int vertxLength() {
        return vertex.size();
    }

    // 深度优先遍历算法
    public void dfs(boolean[] isVisited, int i) {
        System.out.print(getVertex(i) + " -> "); // 打印该节点
        isVisited[i] = true; // 将该节点置为true

        // 查找节点i的第一个邻接节点w
        int w = getFirstNeighbor(i);
        while (w != -1) { // 有邻接节点
            if (!isVisited[w]) {
                // 该节点没有被访问
                dfs(isVisited, w);
            }
            // 如果w节点已经被访问，就找下一个节点
            w = getNextNeighbor(i, w);
        }
    }

    // 堆dfs进行重载，遍历所有节点，并进行dfs
    public void dfs() {
        for (int i = 0; i < vertexNums(); i++) {
            isVisited[i] = false;
        }
        for (int i = 0; i < vertxLength(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }


    // 广度优先遍历算法
    public void bfs(boolean[] isVisited, int i) {
        int u; // 表示列队的头节点对应下标
        int w; // 邻接节点w
        // 队列，记录节点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        // 访问节点，输出节点信息

        System.out.print(getVertex(i) + " -> ");
        isVisited[i] = true;
        queue.addLast(i);

        while (!queue.isEmpty()) {
            u = queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getVertex(w) + " -> ");
                    // 标记已经访问
                    isVisited[w] = true;
                    // 入队列
                    queue.addLast(w);
                }
                // 以u 为前驱点，找w后面的下一个邻接点
                w = getNextNeighbor(u, w);
            }
        }
    }

    // 遍历所有的节点，都进行广度优先搜索
    public void bfs() {
        for (int i = 0; i < vertexNums(); i++) {
            isVisited[i] = false;
        }
        for (int i = 0; i < vertexNums(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    // 展示连接关系
    public void list() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    // 返回总定点数
    public int vertexNums() {
        return vertex.size();
    }

    // 返回指定顶点的权
    public String getVertex(int n) {
        return vertex.get(n);
    }

    // 得到顶点数目
    public int getEdgesNum() {
        return edgesNum;
    }

    // 添加顶点
    public void insertVertex(String value) {
        vertex.add(value);
    }

    // 建立边
    public void buildEdge(int v1, int v2) {
        edges[v1][v2] = 1; // 互相为边
        edges[v2][v1] = 1;
        edgesNum++;
    }

}
