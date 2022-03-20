package com.utils;

import com.algorithm.sort.RandomArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * ClassName: Play
 * Description:
 * date: 2022/3/6 10:35
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class Play {
    public static void main(String[] args) {
//        int[] arr = RandomArray.randomArray(10);
//        heapSort(arr);
//        System.out.println(Arrays.toString(arr));
        int size = 10;
        Graph graph = new Graph(size);
        String[] vertex = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        graph.setVertex(vertex);

        // 建立边关系
        graph.connection(0, 1);
        graph.connection(0, 3);
        graph.connection(0, 4);
        graph.connection(1, 2);
        graph.connection(1, 4);
        graph.connection(2, 3);
        graph.connection(3, 4);
        graph.connection(4, 0);
        graph.connection(4, 2);
        graph.listRelationship();

        System.out.println("深度优先");
        graph.dfs();

        System.out.println("\n广度优先");
        graph.bfs();

    }

    public static void heapSort(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHead(arr, i, arr.length - 1);
        }
        for (int j = arr.length - 1; j > 0; j--) {
            arr[j] ^= arr[0];
            arr[0] ^= arr[j];
            arr[j] ^= arr[0];
            adjustHead(arr, 0, j);
        }

    }

    private static void adjustHead(int[] arr, int i, int length) {
        int place = arr[i]; // 开始移动的位置
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            if (j + 1 < length && arr[j] < arr[j + 1]) {
                j++;
            }

            if (place < arr[j]) {
                arr[i] = arr[j];
                i = j;
            } else {
                break;
            }
        }
        arr[i] = place;
    }
}

class Graph {
    private List<String> vertex;
    private int[][] connect;
    private boolean[] vertexState;

    // 初始化图
    public Graph(int size) {
        vertex = new ArrayList<>(size);
        connect = new int[size][size];
        vertexState = new boolean[size];
    }

    // 找到第一个邻接节点
    private int getFirstVertex(int row) {
        for (int i = 0; i < vertexLength(); i++) {
            if (connect[row][i] > 0) {
                return i; // 找到第一个邻接节点
            }
        }
        return -1;
    }

    // 找到该行其他邻接节点
    private int getNextVertex(int row, int col) {
        for (int i = col + 1; i < vertexLength(); i++) {
            if (connect[row][i] > 0) {
                return i; // 返回下一个邻接节点
            }
        }
        return -1;
    }
    // 深度优先遍历
    // 深度优先遍历又指列优先遍历，每次找到一个邻接节点后，又会以该邻接节点开始进行扫描，
    // 可以看作是一个纵向扫描

    public void dfs(int i) {
        System.out.print(getVertex(i) + " -> ");
        vertexState[i] = true; // 将该顶点置位true
        int w = getFirstVertex(i); // 获得该顶点的第一连接边
        while (w != -1) {  // 该顶点有邻接边
            if (!vertexState[w]) {  // 判断该邻接边是否已经被输出
                dfs(w); // 如果没有输出就将其输出(并跳转到该行，对改行的边继续输出)
            }
            w = getNextVertex(i, w); // 移动到该行的下一个邻接边，继续做判断
        }
    }

    public void dfs() {
        for (int i = 0; i < vertexLength(); i++) {
            vertexState[i] = false;
        }
        for (int i = 0; i < vertexLength(); i++) {
            if (!vertexState[i]) {
                dfs(i);
            }
        }
    }


    // 广度优先遍历
    // 广度优先指 行优先遍历，每次找到一个邻接节点后，会将其入队列用于记录，当该行的顶点遍历完成后，再
    // 跳转执行下一轮
    public void bfs(int i) {
        System.out.print(getVertex(i) + " -> ");
        vertexState[i] = true; // 将改行置为true
        LinkedList<Integer> linkedList = new LinkedList<>(); // 创建一个队列
        int u = 0;
        int a = 0;
        linkedList.addLast(i); // 新增添加到队列尾部
        while (!linkedList.isEmpty()) { // 如果队列不为空就一直遍历
            u = linkedList.removeFirst(); // 移除队列的第一顶点
            a = getFirstVertex(u); // 获得该顶点行的一个临边顶点
            while (a != -1) { // 如果改行有邻接节点 就一直循环
                if (!vertexState[a]) { // 如果改行邻接节点没有被处理接进行处理
                    System.out.print(getVertex(a) + " -> ");
                    vertexState[a] = true;
                    linkedList.addLast(a); // 把该行的邻接节点加入待遍历队列
                }
                a = getNextVertex(u, a); // 得到该行的下一个邻接顶点
            }
        }
    }

    public void bfs() {
        for (int i = 0; i < vertexLength(); i++) {
            vertexState[i] = false;
        }
        for (int i = 0; i < vertexLength(); i++) {
            if (!vertexState[i]) {
                bfs(i); // 遍历所有顶点，防止有边未连接的情况(导致不能正常遍历顶点)
            }
        }
    }


    // 展示连接关系
    public void listRelationship() {
        for (int[] ints : connect) {
            System.out.println(Arrays.toString(ints));
        }
    }

    // 添加连接
    public void connection(int origin, int target) {
        connect[origin][target] = 1;
        connect[target][origin] = 1;
    }

    // 设置顶点
    public void setVertex(String[] vertexes) {
        for (String s : vertexes) {
            vertex.add(s);
        }
    }

    // 得到顶点
    public String getVertex(int i) {
        return vertex.get(i);
    }

    public int vertexLength() {
        return vertex.size();
    }
}