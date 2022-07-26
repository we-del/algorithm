package com.review;

import org.junit.Test;

import java.util.Arrays;

/**
 * ClassName: Recurrence_5
 * Description:
 * date: 2022/6/14 9:38
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class Recurrence_5 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new QuickSort(new int[]{5, 2, 6, 3, 1}).sort()));
        System.out.println(Arrays.toString(new MergeSort(new int[]{5, 2, 6, 3, 1}).sort()));
        System.out.println(Arrays.toString(new ShellSort(new int[]{5, 2, 6, 3, 1}).sort()));
        System.out.println(Arrays.toString(new RadixSort(new int[]{3, 5, 41, 999, 10001}).sort()));
        System.out.println(new KMPSearch("abcab", "abca abcc abcab").kmpSearch());
    }

    /**
     * @description: 快速排序
     */
    private static class QuickSort {
        public int arr[];
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
            int pivot = arr[left];
            int l = left;
            int r = right;
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
            action(0, size - 1);
            return this.arr;
        }

        private void action(int left, int right) {
            if (left >= right) return;
            int mid = (right - left) / 2 + left;
            action(left, mid);
            action(mid + 1, right);
            process(left, right, mid);
        }

        private void process(int left, int right, int mid) {
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
     * @description: 希尔排序
     */
    private static class ShellSort {
        public int[] arr;
        public int size;

        public ShellSort(int[] arr) {
            this.arr = arr;
            this.size = arr.length;
        }

        public int[] sort() {
            action();
            return this.arr;
        }

        public void action() {
            for (int i = size / 2; i > 0; i /= 2) {
                for (int j = i; j < size; j++) {
                    int n = j;
                    int val = arr[j];
                    if (arr[n] <= arr[n - i]) {
                        while (n - i >= 0 && val <= arr[n - i]) {
                            arr[n] = arr[n - i];
                            n = n - i;
                        }
                        arr[n] = val;
                    }
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
        public int max;

        public RadixSort(int[] arr) {
            this.arr = arr;
            this.size = arr.length;
            for (int i : arr) {
                min = Math.min(min, i);
                max = Math.max(max, i);
            }
        }

        public int[] sort() {
            action();
            return arr;
        }

        public void action() {
            int len = (max + "").length();
            for (int i = 0; i < size; i++) {
                arr[i] += Math.abs(min);
            }
            int[][] bucket = new int[10][size];
            int[] count = new int[10];
            int index = 0;
            for (int i = 0, n = 1; i < len; i++, n *= 10) {
                for (int j = 0; j < size; j++) {
                    index = arr[j] / n % 10;
                    bucket[index][count[index]++] = arr[j];
                }

                int cur = 0;
                for (int j = 0; j < 10; j++) {
                    if (count[j] > 0) {
                        for (int k = 0; k < count[j]; k++) {
                            arr[cur++] = bucket[j][k];
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
     * @description: kmp
     */
    private static class KMPSearch {
        public String pattern;
        public int[] next;
        public String target;

        public KMPSearch(String pattern, String target) {
            this.pattern = pattern;
            this.target = target;
            next = new int[pattern.length()];
            setNext();
        }

        public void setNext() {
            next[0] = 0;
            for (int i = 1, j = 0; i < pattern.length(); i++) {
                while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                    j = next[j - 1];
                }
                if (pattern.charAt(i) == pattern.charAt(j)) {
                    j++;
                }
                next[i] = j;
            }
        }

        public int kmpSearch() {
            for (int i = 0, j = 0; i < target.length(); i++) {

                while (j > 0 && target.charAt(i) != pattern.charAt(j)) {
                    j = next[j - 1];
                }
                if (pattern.charAt(j) == target.charAt(i)) {
                    j++;
                }
                if (j == pattern.length()) {
                    return i - j + 1;
                }
            }
            return -1;
        }

    }

    @Test
    public void e() {
        
    }
}
