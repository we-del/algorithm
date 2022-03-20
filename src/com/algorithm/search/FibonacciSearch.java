package com.algorithm.search;

import java.util.Arrays;

/**
 * ClassName: FIbonacciSearch
 * Description:
 * date: 2022/2/19 15:45
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 7, 9};
        System.out.println(fibFind(arr, 9));


    }

    // 得到Fibonacci数列
    public static int[] fib(int length) {
        int[] arr = new int[length];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr;
    }


    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0; // 表示斐波那契分隔数值的下标
        int mid = 0;
        int[] f = fib(20);

        // 找到斐波那契分隔数值的下标
        while (high > f[k] - 1) {
            k++;
        }

        // 因为f[k] 的值 可能大于 a 的长度  ，因此需要可能一个数组来临时存储数值
        int[] temp = Arrays.copyOf(a, f[k]);
        // 如果临时数组长度大于a数组长度，则多余的部分用a最后一个值来填充
        // 如  1 4 5 6 0 0 0    变为 1 4 5 6 6 6 6
        // 3*3-2/(2*3+2+(23-1))   3 3 * 2 2 3 * 2 + 23 1 - + / -
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }

        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {
                high = mid - 1;
                k--; // 更改分隔点的位置
            } else if (key > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return 0;
    }

    public static int fibFind(int[] a, int key) {


        int mid = 0;
        int k = 0;
        int[] f = fib(20);
        int low = 0;
        int high = a.length - 1;

        // 寻找黄金分割
        while (high > f[k] - 1) {
            k++;
        }

        int[] temp = Arrays.copyOf(a, f[k]);

        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }

        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key > temp[mid]) {

                low = mid + 1;
                k -= 2;
            } else if (key < temp[mid]) {
                high = mid - 1;
                k--;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }

        }
        return 0;
    }
}
