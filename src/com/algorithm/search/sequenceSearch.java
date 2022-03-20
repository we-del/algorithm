package com.algorithm.search;

/**
 * ClassName: sequenceSearch
 * Description:
 * date: 2022/2/19 12:35
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class sequenceSearch {
    public static void main(String[] args) {
        int[] arr = {2, 3, 1};
        if (seqSearch(arr, 9)) {
            System.out.println("it having arr");
        } else {
            System.out.println("it not having arr");
        }
    }

    public static boolean seqSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (target == arr[i]) {
                return true;
            }
        }
        return false;
    }
}
