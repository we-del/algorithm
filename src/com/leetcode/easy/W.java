package com.leetcode.easy;

import org.junit.Test;

import java.util.Arrays;

/**
 * ClassName: W
 * Description:
 * date: 2022/4/9 14:14
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class W {
    public static void main(String[] args) {

    }

    public static void getAnswer() {
        for (int i = 1; i < 2; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.println();
            }
        }
    }

    @Test
    public void e() {
        int[] arr = {2, 1, 3, 5, 6, 4};
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j - 1 >= 0; j--) {
                if(arr[j] < arr[j-1]){
                    arr[j] ^=arr[j-1];
                    arr[j-1] ^=arr[j];
                    arr[j] ^=arr[j-1];
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
