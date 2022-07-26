package com.review;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * ClassName: Recurrence_4
 * Description:
 * date: 2022/5/26 9:31
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class Recurrence_4 {
    public static void main(String[] args) {
//        testMergeSort();
//        testQuickSort();
//        testHeapSort();
//        testGraph();
        testBinarySearch();
    }

    public static void testMergeSort() {
        MergeSort mergeSort = new MergeSort(new int[]{5, 2, 6, 1, 3});
        mergeSort.sort();
        System.out.println(Arrays.toString(mergeSort.arr));
    }

    public static void testQuickSort() {
        QuickSort quickSort = new QuickSort(new int[]{5, 2, 6, 1, 3});
        quickSort.sort();
        System.out.println(Arrays.toString(quickSort.arr));
    }

    public static void testHeapSort() {
        HeapSort heapSort = new HeapSort(new int[]{5, 2, 6, 1, 3});
        heapSort.sort();
        System.out.println(Arrays.toString(heapSort.arr));
    }

    public static void testGraph() {
        Graph graph = new Graph(new String[]{"A", "B", "C", "D", "E"});
        graph.establishConnect();
        graph.list();
        System.out.print("bfs: ");
        graph.bfs();
        System.out.println();
        System.out.println("------");
        System.out.print("dfs: ");
        graph.dfs();
    }


    public static void testBinarySearch(){
         BinarySearch binarySearch= new BinarySearch(new int[]{3,4,5,6,7,2,1});
         binarySearch.sort();
         System.out.println(Arrays.toString(binarySearch.arr));
        System.out.println(binarySearch.search(3));
        System.out.println(binarySearch.find(3));
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
            mergeSort(0, mid);
            mergeSort(mid + 1, right);
            merge(left, right, mid);
        }

        private void merge(int left, int right, int mid) {
            int[] tmp = new int[right - left + 1];
            int l = left;
            int r = mid + 1;
            int index = 0;
            while (l <= mid && r <= right) {
                if (arr[l] < arr[r]) tmp[index++] = arr[l++];
                else tmp[index++] = arr[r++];
            }
            while (l <= mid) {
                tmp[index++] = arr[l++];
            }
            while (r <= right) {
                tmp[index++] = arr[r++];
            }
            int cur = left;
            index = 0;
            while (cur <= right) {
                arr[cur++] = tmp[index++];
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
            action(0, size - 1);
        }

        private void action(int left, int right) {
            if (left >= right) return;
            int l = left;
            int r = right;
            int pivot = arr[left];
            while (true) {
                while (l < r && arr[r] >= pivot) r--;
                while (l < r && arr[l] <= pivot) l++;
                if (l == r) {
                    arr[left] = arr[r];
                    arr[r] = pivot;
                    break;
                }
                arr[r] ^= arr[l];
                arr[l] ^= arr[r];
                arr[r] ^= arr[l];
            }
            action(left, r - 1);
            action(r + 1, right);

        }
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
                process(i, size - 1);
            }
            System.out.println(Arrays.toString(arr));
            for (int i = size - 1; i > 0; i--) {
                arr[0] ^= arr[i];
                arr[i] ^= arr[0];
                arr[0] ^= arr[i];
                process(0, i);
            }
        }

        private void process(int i, int length) {
            int val = arr[i];
            for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
                if (j + 1 < length && arr[j] < arr[j + 1]) j++;
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
     * @description: 二分查找
     */
    private static class BinarySearch {
        private int[] arr;
        private int size;

        public BinarySearch(int[] arr) {
            this.arr = arr;
            this.size = arr.length;
        }

        public void sort() {
            start(0, size - 1);
        }

        private void start(int left, int right) {
            if (left >= right) return;
            int mid = (right - left) / 2 + left;
            start(0, mid);
            start(mid + 1, right);
            process(left, right, mid);
        }

        private void process(int left, int right, int mid) {
            int[] arr = new int[right - left + 1];
            int l = left;
            int r = mid + 1;
            int index = 0;
            while (l <= mid && r <= right) {
                if (this.arr[l] <= this.arr[r]) arr[index++] = this.arr[l++];
                else arr[index++] = this.arr[r++];
            }
            while (l <= mid) {
                arr[index++] = this.arr[l++];
            }
            while (r <= right) {
                arr[index++] = this.arr[r++];
            }
            int cur = left;
            index = 0;
            while (cur <= right) {
                this.arr[cur++] = arr[index++];
            }
        }

        public int search(int n) {
            int l = 0;
            int r = size - 1;
            while (true) {
                if (l > r) return -1;
                int mid = (r - l) / 2 + l;
                if (arr[mid] == n) return mid;
                if(arr[mid] > n){
                    r = mid -1;
                }else{
                    l = mid +1;
                }
            }
        }

        public int find(int n) {
            return search(n, 0, size - 1);
        }

        public int search(int n, int left, int right) {
            int mid = (right - left) / 2 + left;
            if (left > right)
                return -1;
            if (n == arr[mid]) {
                return mid;
            }
            if (arr[mid] < n) {
                return search(n, mid + 1, right);
            } else {
                return search(n, left, mid - 1);
            }
        }
    }
}
