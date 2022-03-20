package com.algorithm.sort;

/**
 * ClassName: SelectionSort
 * Description:
 * date: 2022/2/13 17:49
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class SelectionSort {
    public static void main(String[] args){
        int[] arr = {5,2,4,3,1};

        for (int i : arr) {
            System.out.print(i+" ");
        }
    }
    public void selectSort(int arr[]){ // 设  int[] arr = {5,2,4,3,1};
        for(int i = 0; i < arr.length-1;i++){
            for (int j = i+1; j < arr.length; j++) {
                // 2 5 4 3 1 | 1 5 4 3 2 || 1 4 5 3 2 | 1 3 5 4 2 | 1 2 5 4 3 || 1 2 3 4 5 || 1 2 3 4 5
                if(arr[i] > arr[j]){
                    arr[i]^=arr[j];
                    arr[j]^=arr[i];
                    arr[i]^=arr[j];
                }
            }
        }
    }
}
