package com.leetcode.easy;

/**
 * ClassName: Fib
 * Description:
 * date: 2022/3/18 10:38
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class Fib {
    public static void main(String[] args){
        Solution solution = new Solution();
        int fib = solution.fib(9);
        System.out.println(fib);
        // 1 1 2 3 5 8 13 21 34 55

    }
    static class  Solution {
        public int fib(int n) {
            int[] arr = new int[n];
            arr[0] = 1;
            arr[1] = 1;
            for(int i = 2; i< n ;i++){
                arr[i] = arr[i-1]+arr[i-2];
            }
            return arr[n-1];
        }
    }
}
