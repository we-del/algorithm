package com.codingSoldier.recursionToDynamicStrategy;

/**
 * ClassName: Winner
 * Description:
 * date: 2022/3/27 17:05
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class Winner {
    public static void main(String[] args){

    }
    public static int win(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        return Math.max(f(arr,0,arr.length-1),s(arr,0,arr.length-1));
    }
    public static int f(int[] arr,int l,int r){
        if(l == r){
            return arr[l];
        }
        return Math.max(
                arr[l]+s(arr,l-1,r),
                arr[r]+s(arr,l,r-1)
        );
    }
    public static int s(int[] arr,int i,int j){
        if(i == j){
            return 0;
        }
        return Math.min(
                f(arr,i+1,j),
                f(arr,i,j-1)
        );
    }
}
