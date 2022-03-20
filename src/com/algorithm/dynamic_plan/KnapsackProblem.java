package com.algorithm.dynamic_plan;

/**
 * ClassName: KnapsackProblem
 * Description:
 * date: 2022/3/6 17:49
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class KnapsackProblem {
    public static void main(String[] args){
        int[] w = {1,4,3}; // 物品的重量
        int[] val = {1500,3000,2000};
        int m = 4; // 背包容量
        int n = val.length;

        int[][] v = new int[n+1][m+1];

        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }

        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if(w[i-1]>j){
                    v[i][j] = v[i-1][j];
                }else{
                    v[i][j] =Math.max(v[i-1][j],val[i]+v[i-1][j-w[i-1]]);
                }
            }
        }


        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j]+" ");
            }
            System.out.println();
        }
    }
}
