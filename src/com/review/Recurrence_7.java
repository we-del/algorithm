package com.review;

import org.junit.Test;

import java.util.Arrays;

/**
 * ClassName: Recurrence_7
 * Description:
 * date: 2022/7/1 16:15
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class Recurrence_7 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new HeapSort(new int[]{4, 6, 3, 2, 5, 1}).sort()));
        System.out.println(Arrays.toString(new RadixSort(new int[]{4, 63, 13, 232, 15, 1, -13, -87, -122}).sort()));
        // 1 232 63 13 4 15    1 4 13 15 232
    }

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

        public void action(int i, int length) {
            int val = arr[i];
            for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
                if (j + 1 < length && arr[j] < arr[j + 1]) j++;
                if (val < arr[j]) {
                    arr[i] = arr[j];
                    i = j;
                } else break;
            }
            arr[i] = val;
        }
    }

    private static class RadixSort {
        public int[] arr;
        public int size;
        public int max;
        public int min;

        public RadixSort(int[] arr) {
            this.arr = arr;
            this.size = arr.length;
            for (int i : arr) {
                max = Math.max(max, i);
                min = Math.min(min, i);
            }
        }

        public int[] sort() {
            process();
            return this.arr;
        }

        public void process() {
            int len = (max + "").length();
            for (int i = 0; i < arr.length; i++) {
                arr[i] += Math.abs(min);
            }
            int[][] bucket = new int[10][size];
            int[] count = new int[10];
            for (int i = 0, n = 1; i < len; i++, n *= 10) {
                for (int j = 0; j < arr.length; j++) {
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
            for (int i = 0; i < arr.length; i++) {
                arr[i] -= Math.abs(min);
            }
        }
    }

    @Test
    public void e() {
        int a = 1 & 2;
        class MoneyNotEnoughException extends RuntimeException {
            public MoneyNotEnoughException(String message) {
                super(message);
            }
        }
        throw new MoneyNotEnoughException("Need ￥50，please v me");

    }

}
