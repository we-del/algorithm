package com.review;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * ClassName: Recurrence
 * Description:
 * date: 2022/4/13 16:47
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class Recurrence {
    public static void main(String[] args) {
        // testGraph();
        // testKMP();
        //  testShellSort();
        //testMergeSort();
        testRadixSort();
    }

    private static void testGraph() {
        Graph graph = new Graph(new String[]{"A", "B", "C", "D", "E"});
        System.out.println("==============展示连接==========");
        graph.setConnectInVertex();
        graph.list();
        System.out.println("==============展示深度优先顺序==========");
        graph.dfs();
        System.out.println("==============展示广度优先顺序==========");
        graph.bfs();
    }

    private static void testKMP() {
        KMP kmp = new KMP("AAB", "AACAAWBBAAB");
        System.out.println(kmp.searchForKMP());
    }

    private static void testShellSort() {
        ShellSort shellSort = new ShellSort(new int[]{5, 2, 3, 4, 1});
        shellSort.startSort();
        shellSort.list();
    }

    private static void testMergeSort() {
        mergeSort mergeSort = new mergeSort(new int[]{5, 2, 3, 4, 1});
        mergeSort.startSort();
        mergeSort.list();
    }


    private static void testRadixSort() {
        RadixSort radixSort = new RadixSort(new int[]{-5, 2, -3, 4, 1});
        radixSort.startSort();
        radixSort.list();
    }

    static class Graph {
        private String[] vertex;
        private int[][] connect;
        private boolean[] vertexState;
        private int size;

        public Graph(String[] vertex) {
            this.vertex = vertex;
            this.size = vertex.length;
            this.connect = new int[size][size];
            this.vertexState = new boolean[size];
        }


        /**
         * @description: 建立个顶点的连接关系（随机）
         */
        public void setConnectInVertex() {
            int[] tmp = new int[size]; // 对随机连接边进行标记，禁止出现重复连接边情况
            for (int i = 0; i < size; i++) {
                tmp[i] = -1;
            }
            // 设边长为 5
            for (int i = 0; i < size - 1; ) {
                int randomL = (int) (Math.random() * size);
                int randomR = (int) (Math.random() * size);
                if (randomL != randomR && tmp[randomR] == -1) {
                    tmp[randomR] = randomL;
                    System.out.println(randomR + "_" + randomL);
                    establishConnect(randomR, randomL);
                    i++;
                }
            }
        }

        /**
         * @param r 数组的行
         * @param l 数组的列
         * @description: 实际操作建立边连接
         */
        private void establishConnect(int r, int l) {
            connect[r][l] = 1;
            connect[l][r] = 1;
        }

        /**
         * @param r 当前传入的行数
         * @description: 获得当前行的第一个连接节点
         * @return: 返回找到节点的下标(即所在行)，如果没有找到 返回-1
         */
        private int getFirstConnectInThisRow(int r) {
            for (int i = 0; i < size; i++) {
                if (connect[r][i] != 0) {
                    return i;
                }
            }
            return -1;
        }

        /**
         * @param r 为当前行
         * @param l 为开始查找的节点
         * @description: 获得该行中的下一个节点
         * @return: 返回找到节点的下标(即所在行)，如果没有找到 返回-1
         */
        private int getNextConnectInThisRow(int r, int l) {
            for (int i = l; i < size; i++) {
                if (connect[r][i] > 0) return i;
            }
            return -1;
        }

        /**
         * @param r 当前深度优先搜索到的行
         * @description: 深度优先遍历
         */
        private void dfs(int r) {
            vertexState[r] = true;
            System.out.print(vertex[r] + " -> ");
            int f = getFirstConnectInThisRow(r);
            while (f != -1) {
                if (!vertexState[f]) {
                    dfs(f);
                }
                f = getNextConnectInThisRow(r, f + 1);
            }
        }

        /**
         * @description: 深度优先遍历重载方法，用于开启深度遍历
         */
        public void dfs() {
            for (int i = 0; i < size; i++) {
                vertexState[i] = false;
            }
            for (int i = 0; i < size; i++) {
                if (!vertexState[i]) dfs(i);
            }
            System.out.println();
        }

        /**
         * @param r 当前广度优先遍历的行
         * @description: 完成广度优先遍历
         */
        private void bfs(int r) {
            LinkedList<Integer> linkedList = new LinkedList<>();
            vertexState[r] = true;
            System.out.print(vertex[r] + " -> ");
            linkedList.addLast(r);
            while (!linkedList.isEmpty()) {
                int f = linkedList.removeFirst();
                int w = getFirstConnectInThisRow(f);
                while (w != -1) {
                    if (!vertexState[w]) {
                        linkedList.addLast(w);
                        vertexState[w] = true;
                        System.out.print(vertex[w] + " -> ");
                    }
                    w = getNextConnectInThisRow(f, w + 1);
                }
            }
        }

        /**
         * @description: 深度优先遍历启动方法
         */
        public void bfs() {
            for (int i = 0; i < size; i++) {
                vertexState[i] = false;
            }
            for (int i = 0; i < size; i++) {
                if (!vertexState[i]) bfs(i);
            }
        }

        /**
         * @description: 展示所有端点
         */
        public void list() {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(connect[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    static class KMP {
        int[] next;
        String pattern;
        String target;
        int size;

        public KMP(String pattern, String target) {
            this.pattern = pattern;
            this.target = target;
            this.size = pattern.length();
            this.next = new int[size];
            setTraceToNext();
        }

        /**
         * @description: 向next数组中台南佳匹配线索
         */
        public void setTraceToNext() {
            next[0] = 0;
            for (int i = 1, j = 0; i < size; i++) {

                while (j > 0 && pattern.charAt(j) != pattern.charAt(i)) {
                    j = next[j - 1];
                }

                if (pattern.charAt(j) == pattern.charAt(i)) {
                    j++;
                }
                next[i] = j;
            }
        }

        /**
         * @description: 找出一个字符串中符合要求的子串
         * @return: 返回出现的位置，不存在则返回-1
         */
        public int searchForKMP() {
            for (int i = 0, j = 0; i < target.length(); i++) {
                while (j > 0 && pattern.charAt(j) != target.charAt(i)) {
                    j = next[j - 1];
                }
                if (pattern.charAt(j) == target.charAt(i)) j++;
                if (j == size) return i - j + 1;
            }
            return -1;
        }
    }

    static class ShellSort {
        private int[] arr;
        private int size;

        public ShellSort(int[] arr) {
            this.arr = arr;
            this.size = arr.length;
        }

        /**
         * @description: 开始希尔排序
         */
        public void startSort() {
            for (int len = size / 2; len > 0; len /= 2) {
                for (int i = len; i < size; i++) {
                    if (arr[i - len] < arr[i]) {
                        int j = i;
                        int initValue = arr[i];
                        while (j - len >= 0 && arr[j - len] < initValue) {
                            arr[j] = arr[j - len];
                            j = j - len;
                        }
                        arr[j] = initValue;
                    }
                }
            }
        }

        /**
         * @description: 展示排序后的数组
         */
        public void list() {
            System.out.println(Arrays.toString(arr));
        }
    }

    static class RadixSort {
        private int[] arr;
        private int minNum;
        private int maxOrder;

        public RadixSort(int[] arr) {
            this.arr = arr;
            for (int i : arr) {
                if (i < 0)
                    minNum = Math.min(i, minNum);
                maxOrder = Math.max(i, maxOrder);
            }
            maxOrder = (maxOrder + "").length();
        }

        /**
         * @description: 开始基数排序
         */
        public void radixSort() {

            // 补齐负数
            for (int i = 0; i < arr.length; i++) {
                arr[i] += Math.abs(minNum);
            }

            int[][] bucket = new int[10][arr.length];
            int[] count = new int[10];
            for (int i = 0, n = 1; i < maxOrder; i++, n *= 10) {
                for (int j = 0; j < arr.length; j++) {
                    int index = arr[j] / n % 10;
                    bucket[index][count[index]++] = arr[j];
                }
                int cur = 0;
                for (int j = 0; j < count.length; j++) {
                    if(count[j]>0){
                        for (int k = 0; k < count[j]; k++) {
                            arr[cur++] = bucket[j][k];
                        }
                        count[j] = 0;
                    }
                }
            }
            for (int i = 0; i < arr.length; i++) {
                arr[i] -= Math.abs(minNum);
            }
            

        }

        public void startSort(){
            radixSort();
        }
        /**
            @description: 展示排序后的数组
        */
        public void list(){
            System.out.println(Arrays.toString(arr));
        }
    }

    static class mergeSort {
        private int[] arr;
        private int size;

        public mergeSort(int[] arr) {
            this.arr = arr;
            this.size = arr.length;
        }

        /**
         * @description: 开始归并排序
         */
        public void startSort() {
            sort(this.arr, 0, this.arr.length - 1);
        }

        /**
         * @description: 归并排序入口
         */
        private void sort(int[] arr, int left, int right) {
            if (left >= right) return;
            int mid = (right - left) / 2 + left;
            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }

        /**
         * @description: 归并排序处理
         */
        private void merge(int[] arr, int left, int mid, int right) {
            int[] tmp = new int[right - left + 1];
            int l = left;
            int r = mid + 1;
            int index = 0;
            while (l <= mid && r <= right) {
                if (arr[l] < arr[r]) tmp[index++] = arr[l++];
                else tmp[index++] = arr[r++];
            }
            while (l <= mid) tmp[index++] = arr[l++];
            while (r <= right) tmp[index++] = arr[r++];
            index = 0;
            int cur = left;
            while (cur <= right) arr[cur++] = tmp[index++];


        }

        /**
         * @description: 展示排序后的数组
         */
        private void list() {
            System.out.println(Arrays.toString(this.arr));
        }
    }
}
