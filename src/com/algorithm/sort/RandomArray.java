package com.algorithm.sort;

/**
 * ClassName: RandomArray
 * Description:
 * date: 2022/2/13 18:42
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class RandomArray {
    public static void main(String[] args){

    }
     public  static int[]  randomArray(int length){
         int[] arr = new int[length];
         for(int i = 0; i < arr.length;i++){
             arr[i] = (int)(Math.random()*100);
         }
         return arr;
     }
}
