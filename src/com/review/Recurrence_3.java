package com.review;

import org.junit.Test;

import java.util.*;

/**
 * ClassName: Recurrence_3
 * Description:
 * date: 2022/5/11 10:37
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class Recurrence_3 {

    public static void main(String[] args) {
//        testHeapSort();
//        testGraph();
//        testQuickSort();
//        testMergeSort();
//        testShellSort();
//        testKMP();
//        testInsert();
        testRadixSort();
    }


    private static void testHeapSort() {
        HeapSort heapSort = new HeapSort(new int[]{3, 5, 2, 4, 1});
        heapSort.sort();
        System.out.println(Arrays.toString(heapSort.arr));
    }

    private static void testGraph() {
        Graph graph = new Graph(new String[]{"A", "B", "C", "D"});
        graph.establishConnect();
        graph.list();
        System.out.print("bfs: ");
        graph.bfs();
        System.out.println();
        System.out.println("------");
        System.out.print("dfs: ");
        graph.dfs();
    }

    private static void testQuickSort() {
        QuickSort quickSort = new QuickSort(new int[]{3, 5, 6, 2, 1, 7});
        quickSort.sort();
        System.out.println(Arrays.toString(quickSort.arr));

    }

    private static void testMergeSort() {
        MergeSort mergeSort = new MergeSort(new int[]{3, 5, 6, 2, 1, 7});
        mergeSort.sort();
        System.out.println(Arrays.toString(mergeSort.arr));

    }

    private static void testShellSort() {
        ShellSort shellSort = new ShellSort(new int[]{3, 5, 6, 2, 1, 7});
        shellSort.sort();
        System.out.println(Arrays.toString(shellSort.arr));

    }

    private static void testKMP() {
        KMP kmp = new KMP("ABCAAAABCABCAB", "ABCAB");
        System.out.println(kmp.kmpSearch());

    }

    private static void testInsert() {
        InsertSort insertSort = new InsertSort(new int[]{3, 5, 2, 4, 1});
        insertSort.sort();
        System.out.println(Arrays.toString(insertSort.arr));

    }
    private static void testRadixSort() {
        RadixSort radixSort = new RadixSort(new int[]{7,8,2,12,3,103,-231,-99});
        radixSort.sort();
        System.out.println(Arrays.toString(radixSort.arr));
    }

    /**
     * @description: 堆排序
     */
    private static class HeapSort {
        public int[] arr;
        public int size;

        public HeapSort(int[] arr) {
            this.arr = arr;
            this.size = arr.length;
        }

        public void sort() {
            for (int i = size / 2 - 1; i >= 0; i--) {
                adjust(i, size - 1);
            }
            for (int i = size - 1; i > 0; i--) {
                arr[0] ^= arr[i];
                arr[i] ^= arr[0];
                arr[0] ^= arr[i];
                adjust(0, i);
            }
        }

        private void adjust(int i, int length) {
            int val = arr[i];
            for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
                if (j + 1 < length && arr[j] < arr[j + 1]) {
                    j++;
                }
                if (val < arr[j]) {
                    arr[i] = arr[j];
                    i = j;
                } else {
                    break;
                }
            }
            arr[i] = val;
        }
    }

    /**
     * @description: 图
     */
    private static class Graph {
        public String[] vertex;
        public boolean[] state;
        public int[][] connect;
        public int size;

        public Graph(String[] vertex) {
            this.vertex = vertex;
            this.size = vertex.length;
            this.state = new boolean[size];
            this.connect = new int[size][size];
        }

        public void establishConnect() {
            for (int i = 0; i < size + size / 2; i++) {
                startConnect((int) (Math.random() * size), (int) (Math.random() * size));
            }
        }

        private void startConnect(int r, int l) {
            connect[r][l] = 1;
            connect[l][r] = 1;
        }

        private int getFirstVertex(int r) {
            for (int i = 0; i < size; i++) {
                if (connect[r][i] > 0) return i;
            }
            return -1;
        }

        private int getNextVertex(int r, int l) {
            for (int i = l; i < size; i++) {
                if (connect[r][i] > 0) return i;
            }
            return -1;
        }

        private void dfs(int r) {
            state[r] = true;
            System.out.print(vertex[r] + " -> ");
            int f = getFirstVertex(r);
            while (f != -1) {
                if (!state[f]) dfs(f);
                f = getNextVertex(r, f + 1);
            }
        }

        public void dfs() {
            for (int i = 0; i < size; i++) {
                state[i] = false;
            }
            for (int i = 0; i < size; i++) {
                if (!state[i]) dfs(i);
            }
        }

        public void list() {
            for (int[] ints : connect) {
                System.out.println(Arrays.toString(ints));
            }
        }

        private void bfs(int r) {
            state[r] = true;
            System.out.print(vertex[r] + " -> ");
            LinkedList<Integer> linkedList = new LinkedList<>();
            linkedList.addLast(r);
            while (!linkedList.isEmpty()) {
                int f = getFirstVertex(linkedList.removeFirst());
                while (f != -1) {
                    if (!state[f]) {
                        System.out.print(vertex[f] + " -> ");
                        state[f] = true;
                        linkedList.addLast(f);
                    }
                    f = getNextVertex(r, f + 1);
                }

            }
        }

        public void bfs() {
            for (int i = 0; i < size; i++) {
                state[i] = false;
            }
            for (int i = 0; i < size; i++) {
                if (!state[i]) bfs(i);
            }
        }
    }

    /**
     * @description: 快速排序
     */
    private static class QuickSort {
        public int[] arr;
        public int size;

        public QuickSort(int[] arr) {
            this.arr = arr;
            this.size = arr.length;
        }

        public void sort() {
            quickSort(0, size - 1);
        }

        // 3 2 1 | 1
        private void quickSort(int left, int right) {
            if (left < right) {
                int l = left;
                int r = right;
                int pivot = arr[left];
                while (true) {
                    while (l < r && arr[r] >= pivot) r--;
                    while (l < r && arr[l] <= pivot) l++;
                    if (l == r) {
                        arr[left] = arr[l];
                        arr[l] = pivot;
                        break;
                    }
                    swap(l, r);
                }
                quickSort(left, l - 1);
                quickSort(l + 1, right);
            }

        }

        public void swap(int l, int r) {

            arr[l] ^= arr[r];
            arr[r] ^= arr[l];
            arr[l] ^= arr[r];
        }
    }

    /**
     * @description: 归并排序
     */
    private static class MergeSort {
        public int[] arr;
        public int size;

        public MergeSort(int[] arr) {
            this.arr = arr;
            this.size = arr.length;
        }

        public void sort() {
            mergeSort(0, size - 1);
        }

        private void mergeSort(int left, int right) {
            if (left >= right) return;
            int mid = (right - left) / 2 + left;
            mergeSort(left, mid);
            mergeSort(mid + 1, right);
            process(left, mid, right);
        }

        /**
         * public protected 默认 private
         * ArrayList Vector LinkedList
         */
        private void process(int left, int mid, int right) {
            int[] tmp = new int[right - left + 1];
            int l = left;
            int r = mid + 1;
            int index = 0;
            while (l <= mid && r <= right) {
                if (arr[l] < arr[r]) {
                    tmp[index++] = arr[l++];
                } else {
                    tmp[index++] = arr[r++];
                }
            }
            while (l <= mid) tmp[index++] = arr[l++];
            while (r <= right) tmp[index++] = arr[r++];
            index = 0;
            int cur = left;
            while (cur <= right) arr[cur++] = tmp[index++];
        }
    }

    /**
     * @description: 希尔排序   快排 > 归并 ,堆排 >希尔
     */
    private static class ShellSort {
        public int[] arr;
        public int size;

        public ShellSort(int[] arr) {
            this.arr = arr;
            this.size = arr.length;
        }

        public void sort() {
            for (int len = size / 2; len > 0; len /= 2) {
                for (int i = len; i < size; i++) {
                    int j = i;
                    if (arr[i] > arr[j - len]) {
                        int val = arr[j];
                        while (j - len >= 0 && val > arr[j - len]) {
                            arr[j] = arr[j - len];
                            j = j - len;
                        }
                        arr[j] = val;
                    }
                }
            }
        }
    }

    /**
     * @description: kmp 算法
     */
    private static class KMP {
        public String target;
        public String pattern;
        public int[] next;

        public KMP(String target, String pattern) {
            this.target = target;
            this.pattern = pattern;
            this.next = new int[pattern.length()];
        }

        public int kmpSearch() {
            getNext();
            for (int i = 0, j = 0; i < target.length(); i++) {
                while (j > 0 && pattern.charAt(j) != target.charAt(i)) j = next[j - 1];
                if (pattern.charAt(j) == target.charAt(i)) j++;
                if (j == pattern.length()) return i - j + 1;
            }
            return -1;
        }

        private void getNext() {
            // A B C A B
            next[0] = 0;
            for (int i = 1, j = 0; i < pattern.length(); i++) {
                while (j > 0 && pattern.charAt(j) != pattern.charAt(i)) j = next[j - 1];
                if (pattern.charAt(j) == pattern.charAt(i)) j++;
                next[i] = j;
            }
        }

    }

    /**
     * @description: 插入排序
     */
    private static class InsertSort {
        public int[] arr;
        public int size;

        public InsertSort(int[] arr) {
            this.arr = arr;
            this.size = arr.length;
        }

        public void sort() {
            for (int i = 1; i < size; i++) {
                for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                    arr[j] ^= arr[j - 1];
                    arr[j - 1] ^= arr[j];
                    arr[j] ^= arr[j - 1];
                }
            }
        }
    }

    /**
     * @description: 基数排序
     */
    private static class RadixSort {
        public int[] arr;
        public int size;
        public int min;
        public int maxLen;

        public RadixSort(int[] arr) {
            this.arr = arr;
            this.size = arr.length;
            for (int i : arr) {
                this.min = Math.min(this.min, i);
                this.maxLen = Math.max(this.maxLen, i);
            }
            this.maxLen = (this.maxLen + "").length();
        }

        public void sort() {
            int[][] bucket = new int[10][size];
            int[] count = new int[10];
            for (int i = 0; i < size; i++) {
                arr[i] = arr[i] + Math.abs(min);
            }
            for (int i = 0, n = 1; i < maxLen; i++, n *= 10) {
                for (int j = 0; j < size; j++) {
                    int index = arr[j] / n % 10;
                    bucket[index][count[index]++] = arr[j];
                }
                int cur = 0;
                for (int j = 0; j < count.length; j++) {
                    if (count[j] > 0) {
                        for (int k = 0; k < count[j]; k++) {
                            arr[cur++] = bucket[j][k];
                        }
                        count[j] = 0;
                    }
                }
            }
            for (int i = 0; i < size; i++) {
                arr[i] = arr[i] - Math.abs(min);
            }
        }
    }
}

