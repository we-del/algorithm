package com.review;

import java.util.Arrays;

/**
 * ClassName: Recurrence_6
 * Description:
 * date: 2022/6/30 11:27
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class Recurrence_6 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new QuickSort(new int[]{3, 2, 5, 6, 1, 4}).sort()));
        System.out.println(Arrays.toString(new MergeSort(new int[]{3, 2, 5, 6, 1, 4}).sort()));
        System.out.println(Arrays.toString(new HeapSort(new int[]{3, 2, 5, 6, 1, 4}).sort()));
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

        public int[] sort() {
            process(0, size - 1);
            return this.arr;
        }

        public void process(int left, int right) {
            if (left >= right) return;
            int l = left;
            int r = right;
            int pivot = arr[left];
            while (true) {
                while (l < r && arr[r] >= pivot) r--;
                while (l < r && arr[l] <= pivot) l++;
                if (l == r) {
                    int tmp = arr[left];
                    arr[left] = arr[l];
                    arr[l] = tmp;
                    break;
                }
                arr[l] ^= arr[r];
                arr[r] ^= arr[l];
                arr[l] ^= arr[r];
            }
            process(left, l - 1);
            process(l + 1, right);
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

        public int[] sort() {
            process(0, size - 1);
            return this.arr;
        }

        public void process(int left, int right) {
            if (left >= right) return;
            int mid = (right - left) / 2 + left;
            process(left, mid);
            process(mid + 1, right);
            action(left, right, mid);
        }

        public void action(int left, int right, int mid) {
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
     * @description: 堆排序
     */
    private static class HeapSort {
        public int[] arr;
        public int size;

        public HeapSort(int[] arr) {
            this.arr = arr;
            this.size = arr.length;
        }

        public int[] sort() {
            process();
            return this.arr;
        }

        public void process() {
            for (int i = size / 2 - 1; i >= 0; i--) {
                action(i, size - 1);
            }
            for (int i = size - 1; i > 0; i--) {
                arr[i] ^= arr[0];
                arr[0] ^= arr[i];
                arr[i] ^= arr[0];
                action(0, i);
            }
        }

        public void action(int dest, int length) {
            int val = arr[dest];
            for (int j = dest * 2 + 1; j < length; j = j * 2 + 1) {
                if (j + 1 < length && arr[j] < arr[j + 1]) j++;
                if (val < arr[j]) {
                    arr[dest] = arr[j];
                    dest = j;
                } else break;
            }
            arr[dest] = val;
        }
    }

}
