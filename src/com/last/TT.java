package com.last;

/**
 * ClassName: TT
 * Description:
 * date: 2022/4/14 11:39
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class TT {
    public static void main(String[] args) {
        System.out.println(e(new int[]{2, 3, -2, 4,-3}));
    }


    public static int e(int[] arr) {
        int minM = 1, maxM = 1;
        int max = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                minM ^= maxM;
                maxM ^= minM;
                minM ^= maxM;
            }
            maxM = Math.max(maxM, arr[i] * maxM);
            minM = Math.min(minM, arr[i] * minM);
            max = Math.max(maxM, max);
        }
        return max;
    }

//    public static int n(int index,int N){
//        if(index==N) return -1;
//        n(index+1,N);
//        n(index+1,N);
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//
//            }
//        }
//    }
}
