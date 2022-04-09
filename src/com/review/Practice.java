package com.review;

import com.algorithm.sort.RandomArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * ClassName: Practice
 * Description:
 * date: 2022/3/7 21:07
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class Practice {
    public static void main(String[] args) {
        // graphTest(); // 图的测试
        // KMPTest();
        //startRadixSort();
        startMergeSort();
        //startHeapSort(); // 推排序测试
       // startQuickSort();
    }

    public static void graphTest() {
        int size = 5;
        Graph graph = new Graph(size);
        String[] s = {"A", "B", "C", "D", "E"};
        graph.setVertex(s);
        graph.setEdge(0, 3);
        graph.setEdge(0, 4);
        graph.setEdge(3, 1);
        graph.setEdge(1, 4);
        graph.showGraph();
        System.out.println("深度优先");
        graph.dfs();
        System.out.println("\n广度优先");
        graph.bfs();
    }

    public static void KMPTest() {
        String s1 = "ABC";
        KMP kmp = new KMP();
        int[] next = kmp.getNext(s1);
        String s2 = "AABBABAAABC";
        int i = kmp.KMPSearch(s2, s1, next);
        System.out.println(i);
    }

    public static void startRadixSort() {
        int[] ints = RandomArray.randomArray(10);
        RadixSort radixSort = new RadixSort();
        radixSort.radixSort(ints);
        System.out.println(Arrays.toString(ints));
    }

    public static void startMergeSort() {
        int[] arr = {5, 4, 6, 3, 1, 2};
        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void startHeapSort() {
        HeapSort heapSort = new HeapSort();
        int[] arr = {4, 5, 3, 6, 2, 1};
        heapSort.heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void startQuickSort() {
        int[] arr = {3, 5, 4, 0, 4, 6, 7, 2};
        //int[] arr = {3, 5, 4, 6, 7, 2};
        QuickSort.quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}

class RadixSort {
    public static void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) max = arr[i];
        }
        int maxNums = (max + "").length();

        int[][] bucket = new int[10][arr.length];
        int[] bucketEleCounts = new int[10];

        for (int i = 0, j = 1; i < maxNums; i++, j *= 10) {
            for (int k = 0; k < arr.length; k++) {
                int n = arr[k] / j % 10;
                bucket[n][bucketEleCounts[n]++] = arr[k];
            }

            int index = 0;
            for (int k = 0; k < bucket.length; k++) {
                if (bucketEleCounts[k] > 0) {
                    for (int l = 0; l < bucketEleCounts[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                    bucketEleCounts[k] = 0;
                }
            }
        }

    }
}

class MergeSort {

    public void mergeSort(int[] arr) {

        mergeSort(arr, 0, arr.length - 1);
    }

    public void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = (right - left) / 2 + left;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    public void merge(int[] arr, int left, int mid, int right) {
        int[] tmpArr = new int[right - left + 1];
        int l = left;
        int r = mid + 1;
        int index = 0;
        while (l <= mid && r <= right) {
            if (arr[l] < arr[r]) tmpArr[index++] = arr[l++];
            else tmpArr[index++] = arr[r++];
        }
        while (l <= mid) tmpArr[index++] = arr[l++];
        while (r <= right) tmpArr[index++] = arr[r++];
        index = 0;
        int cur = left;
        while (cur <= right) {
            arr[cur++] = tmpArr[index++];
        }
    }


//    private void mergeSort(int[] arr, int left, int right) {
//
//
//        if (left != right) {
//            int mid = (right - left) / 2 + left;
//            mergeSort(arr, left, mid);
//            mergeSort(arr, mid + 1, right);
//            merge(arr, left, mid, right);
//
//        }
//    }
//
//    private void merge(int[] arr, int left, int mid, int right) {
//        int[] tmp = new int[right - left + 1]; // 开辟最小的数组空间，每次值开动态的值
//        int l = left;
//        int r = mid + 1;
//        int index = 0;
//        while (l <= mid && r <= right) {
//            if (arr[l] < arr[r]) {
//                tmp[index++] = arr[l++];
//            } else {
//                tmp[index++] = arr[r++];
//            }
//        }
//        while (l <= mid) {
//            tmp[index++] = arr[l++];
//        }
//        while (r <= right) {
//            tmp[index++] = arr[r++];
//        }
//
//        index = 0;
//        l = left;
//        r = right;
//        while (l <= r) {
//            arr[l++] = tmp[index++];
//        }
//
//    }
}

class Graph {
    private List<String> vertexes;
    private int[][] vertexConnect;
    private boolean[] connectState;

    // 初始化图
    public Graph(int size) {
        vertexes = new ArrayList<>(size);
        vertexConnect = new int[size][size];
        connectState = new boolean[size];
    }

    // 添加顶点
    public void setVertex(String[] vertex) {
        for (String s : vertex) {
            vertexes.add(s);
        }
    }

    // 设置顶点邻边
    public void setEdge(int e1, int e2) {
        vertexConnect[e1][e2] = 1;
        vertexConnect[e2][e1] = 1;
    }

    // 得到顶点大小
    public int getVertexLength() {
        return vertexes.size();
    }

    // 展示图的连接关系
    public void showGraph() {
        for (int[] ints : vertexConnect) {
            System.out.println(Arrays.toString(ints));
        }
    }

    // 获得该行第一个邻接顶点
    private int getFirstVertex(int r) {
        for (int i = 0; i < getVertexLength(); i++) {
            if (vertexConnect[r][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    // 获得该行上一个顶点的下一个邻边顶点
    private int getNextVertex(int r, int c) {
        for (int i = c + 1; i < getVertexLength(); i++) {
            if (vertexConnect[r][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    // 深度优先算法（纵向遍历）
    private void dfs(int p) {
        System.out.print(vertexes.get(p) + " =>");
        connectState[p] = true;
        int w = getFirstVertex(p);
        while (w != -1) {
            if (!connectState[w]) {
                dfs(w);
            }
            w = getNextVertex(p, w);
        }
    }

    public void dfs() {
        for (int i = 0; i < connectState.length; i++) {
            connectState[i] = false;
        }
        for (int i = 0; i < getVertexLength(); i++) {
            if (!connectState[i]) {
                dfs(i);
            }
        }
    }


    // 广度优先算法(横向遍历)
    private void bfs(int p) {
        System.out.print(vertexes.get(p) + " => ");
        connectState[p] = true;
        LinkedList<Integer> queen = new LinkedList<>();
        queen.addLast(p);
        while (!queen.isEmpty()) {
            int r = queen.removeFirst();
            int w = getFirstVertex(r);
            while (w != -1) {
                if (!connectState[w]) {
                    System.out.print(vertexes.get(w) + " => ");
                    connectState[w] = true;
                    queen.addLast(w);
                }
                w = getNextVertex(r, w);
            }
        }
    }

    public void bfs() {
        for (int i = 0; i < connectState.length; i++) {
            connectState[i] = false;
        }
        for (int i = 0; i < getVertexLength(); i++) {
            if (!connectState[i]) {
                bfs(i);
            }
        }
    }
}

class KMP {

    public int KMPSearch(String s1, String s2, int[] next) {
        for (int i = 0, j = 0; i < s1.length(); i++) {
            while (j > 0 && s1.charAt(i) != s2.charAt(j)) {
                j = next[j - 1];
            }
            if (s1.charAt(i) == s2.charAt(j)) {
                j++;
            }
            if (j == s2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    public int[] getNext(String s1) {
        int[] next = new int[s1.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < next.length; i++) {
            while (j > 0 && s1.charAt(j) != s1.charAt(i)) {
                j = next[j - 1];
            }
            if (s1.charAt(j) == s1.charAt(i)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}

class HeapSort {
    public void heapSort(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapAdjust(arr, i, arr.length);
        }
        for (int i = arr.length - 1; i > 0; i--) {
            arr[0] ^= arr[i];
            arr[i] ^= arr[0];
            arr[0] ^= arr[i];
            heapAdjust(arr, 0, i);
        }
    }

    private void heapAdjust(int[] arr, int i, int length) {
        int val = arr[i];
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            if (j + 1 < length && arr[j] < arr[j + 1]) j++;
            if (arr[j] > val) {
                arr[i] = arr[j];
                i = j;
            } else {
                break;
            }
        }
        arr[i] = val;
    }
}

class QuickSort {
    public static void quickSort(int[] arr, int left, int right) {
        if (left > right) return;
        int l = left;
        int r = right;
        int pivot = arr[left];
        while (true) {

            while (l != r && arr[r] >= pivot) {
                r--;
            }
            while (l != r && arr[l] <= pivot) {
                l++;
            }
            if (l == r) {
                arr[left] = arr[r];
                arr[r] = pivot;
                break;
            }
            int tmp = arr[r];
            arr[r] = arr[l];
            arr[l] = tmp;
        }
        quickSort(arr, left, l - 1);
        quickSort(arr, l + 1, right);
    }
}

