package com.review;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
/**
 * ClassName: Recurrence_2
 * Description:
 * date: 2022/4/27 11:09
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class Recurrence_2 {
    public static void main(String[] args) {
//        testKMP();
//        testGraph();
        //  testHeapSort();
        //   testQuickSort();
//        testMergeSort();
//        testRadixSort();
//        testShellSort();
        testChooseSort();
    }

    public static void testKMP() {
        System.out.println(new KMP("abcaac", "ac").KMPSearch());
    }

    public static void testGraph() {
        String[] vertex = new String[]{"A", "B", "C", "D", "E", "F"};
        Graph graph = new Graph(vertex);
        graph.randomConnect();
        graph.list();
        graph.dfs();
        graph.bfs();
    }

    public static void testHeapSort() {
        HeapSort heapSort = new HeapSort(new int[]{3, 2, 1, 5, 4});
        heapSort.sort();
        System.out.println(Arrays.toString(heapSort.arr));
    }

    public static void testQuickSort() {
        QuickSort quickSort = new QuickSort(new int[]{2, 4, 5, 3, 1});
        quickSort.quickSort();
        System.out.println(Arrays.toString(quickSort.arr));
    }

    public static void testMergeSort() {
        MergeSort mergeSort = new MergeSort(new int[]{1, 4, 6, 3, 5, 2});
        mergeSort.mergeSort();
        System.out.println(Arrays.toString(mergeSort.arr));
    }


    public static void testRadixSort() {
        RadixSort radixSort = new RadixSort(new int[]{5, 2, 3, 4, 1});
        radixSort.sort();
        System.out.println(Arrays.toString(radixSort.arr));
    }
    public static void testShellSort() {
        ShellSort shellSort = new ShellSort(new int[]{5, 2, 3, 4, 1});
        shellSort.sort();
        System.out.println(Arrays.toString(shellSort.arr));
    }
    public static void testChooseSort() {
        ChooseSort chooseSort = new ChooseSort(new int[]{5, 2, 3, 4, 1});

        chooseSort.sort();
        System.out.println(Arrays.toString(chooseSort.arr));
    }


    /**
     * @description: 复习kmp算法
     */
    static class KMP {
        public int[] next;
        public String target;
        public String pattern;

        public KMP(String target, String pattern) {
            this.target = target;
            this.pattern = pattern;
            this.next = new int[pattern.length()];
        }

        private void getNext() {
            next[0] = 0;
            for (int i = 1, j = 0; i < pattern.length(); i++) {
                while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) j = next[j - 1];
                if (pattern.charAt(i) == pattern.charAt(j)) j++;
                next[i] = j;
            }
        }

        public int KMPSearch() {
            getNext();
            for (int i = 0, j = 0; i < target.length(); i++) {
                while (j > 0 && target.charAt(i) != pattern.charAt(j)) j = next[j - 1];
                if (target.charAt(i) == pattern.charAt(j)) j++;
                if (j == pattern.length()) return i - j + 1;
            }
            return -1;
        }
    }


    /**
     * @description: 复习图的算法
     */
    static class Graph {
        public String[] vertex;
        public int[][] connectOfVertex;
        public boolean[] connectState;
        public int size;

        public Graph(String[] vertex) {
            this.vertex = vertex;
            this.connectOfVertex = new int[vertex.length][vertex.length];
            this.connectState = new boolean[vertex.length];
            this.size = vertex.length;
        }

        public void list() {
            for (int[] ofVertex : connectOfVertex) {
                System.out.println(Arrays.toString(ofVertex));
            }
        }

        private void randomConnect() {
            for (int i = 0; i < size + 1; i++) {
                int randomR = (int) (Math.random() * size);
                int randomC = (int) (Math.random() * size);
                setConnectAssociate(randomR, randomC);
            }
        }

        private void setConnectAssociate(int r, int c) {
            connectOfVertex[r][c] = 1;
            connectOfVertex[c][r] = 1;
        }

        private int getFirstVertex(int r) {
            for (int i = 0; i < size; i++) {
                if (connectOfVertex[r][i] == 1) return i;
            }
            return -1;
        }

        private int getNextVertex(int r, int c) {
            for (int i = c + 1; i < size; i++) {
                if (connectOfVertex[r][i] == 1) return i;
            }
            return -1;
        }

        private void dfs(int v) {
            connectState[v] = true;
            System.out.print(vertex[v] + " -> ");
            int n = getFirstVertex(v);
            while (n != -1) {
                if (!connectState[n]) {
                    dfs(n);
                }
                n = getNextVertex(v, n);
            }
        }

        public void dfs() {
            for (int i = 0; i < size; i++) {
                connectState[i] = false;
            }
            for (int i = 0; i < size; i++) {
                if (!connectState[i]) dfs(i);
            }
            System.out.println();
        }

        public void bfs(int v) {
            connectState[v] = true;
            System.out.print(vertex[v] + " -> ");
            LinkedList<Integer> linkedList = new LinkedList<>();
            linkedList.addLast(v);
            while (!linkedList.isEmpty()) {
                Integer n = linkedList.removeFirst();
                int f = getFirstVertex(n);
                while (f != -1) {
                    if (!connectState[f]) {
                        connectState[f] = true;
                        System.out.print(vertex[f] + " -> ");
                        linkedList.addLast(f);
                    }
                    f = getNextVertex(n, f);
                }
            }
        }

        public void bfs() {
            for (int i = 0; i < size; i++) {
                connectState[i] = false;
            }
            for (int i = 0; i < size; i++) {
                if (!connectState[i]) bfs(i);
            }
            System.out.println();
        }

    }

    /**
     * @description: 推排序
     */
    static class HeapSort {
        public int[] arr;
        public int size;

        public HeapSort(int[] arr) {
            this.arr = arr;
            this.size = arr.length;
        }

        public void sort() {
            for (int i = size / 2 - 1; i >= 0; i--) {
                adjust(i, size);
            }
            for (int i = size - 1; i > 0; i--) {
                arr[0] ^= arr[i];
                arr[i] ^= arr[0];
                arr[0] ^= arr[i];
                adjust(0, i);
            }
        }

        private void adjust(int i, int length) {
            int tmp = arr[i];
            for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
                if (j + 1 < length && arr[j] < arr[j + 1]) j++;
                if (tmp < arr[j]) {
                    arr[i] = arr[j];
                    i = j;
                } else {
                    break;
                }
            }
            arr[i] = tmp;
        }
    }

    /**
     * @description: 快速排序
     */
    static class QuickSort {
        public int[] arr;
        public int size;

        public QuickSort(int[] arr) {
            this.arr = arr;
            this.size = arr.length;
        }

        public void quickSort() {
            sort(0, size - 1);
        }

        private void sort(int left, int right) {
            if (left >= right) return;
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
                arr[l] ^= arr[r];
                arr[r] ^= arr[l];
                arr[l] ^= arr[r];
            }
            sort(left, r - 1);
            sort(r + 1, right);

        }
    }

    /**
     * @description: 归并排序
     */
    static class MergeSort {
        public int[] arr;
        public int size;

        public MergeSort(int[] arr) {
            this.arr = arr;
            this.size = arr.length;
        }

        public void mergeSort() {
            sort(0, size - 1);
        }

        private void sort(int left, int right) {
            if (left >= right) return;
            int mid = (right - left) / 2 + left;
            sort(left, mid);
            sort(mid + 1, right);
            process(left, mid, right);
        }

        private void process(int left, int mid, int right) {
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
            int cur = left;
            index = 0;
            while (cur <= right) {
                arr[cur++] = tmp[index++];
            }

        }
    }

    /**
     * @description: 复习基数排序
     */
    static class RadixSort {
        public int[] arr;
        public int size;
        public int min;
        public int max;

        public RadixSort(int[] arr) {
            this.arr = arr;
            this.size = arr.length;
            min = arr[0];
            max = arr[0];
            for (int i = 0; i < size; i++) {
                min = Math.min(min, arr[i]);
                max = Math.max(max, arr[i]);
            }
        }

        public void sort() {
            for (int i = 0; i < size; i++) {
                arr[i] += Math.abs(min);
            }

            // 12  9  110  233  23
            // 110 12 233 23 9
            // 9 110 12 23 233
            // 9 12 23 110 233
            int maxLen = (max + "").length();
            int[][] bucket = new int[10][size];
            int[] count = new int[10];
            for (int i = 0, n = 1; i < maxLen; i++, n *= 10) {
                for (int j = 0; j < size; j++) {
                    int index = arr[j] / n % 10;
                    bucket[index][count[index]++] = arr[j];
                }
                int index = 0;
                for (int j = 0; j < count.length; j++) {
                    if (count[j] > 0) {
                        for (int k = 0; k < count[j]; k++) {
                            arr[index++] = bucket[j][k];
                        }
                        count[j] = 0;
                    }
                }
            }
            for (int i = 0; i < size; i++) {
                arr[i] -= Math.abs(min);
            }
        }
    }

    /**
     * @description: 希尔排序
     */
    static class ShellSort {
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
                    int val = arr[i];
                    if (j - len >= 0 && arr[j - len] > val) {
                        while (j - len >= 0 && arr[j - len] > val) {
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
        @description: 选择排序
    */
    static class ChooseSort{
        public int[] arr;
        public int size;
        public ChooseSort(int[] arr) {
            this.arr = arr;
            this.size = arr.length;
        }
        public  void sort(){
            for (int i = 0; i < size; i++) {
                int max = arr[i];
                int j = i ;
                for (; j < size; j++) {
                    max = Math.max(max,arr[j]);
                    
                }

                arr[i] ^=arr[j-1];
                arr[j-1] ^=arr[i];
                arr[i] ^=arr[j-1];
            }
        }
    }
}
