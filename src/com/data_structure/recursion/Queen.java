package com.data_structure.recursion;

/**
 * ClassName: Queen
 * Description:
 * date: 2022/2/13 10:44
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class Queen {
    private int max = 8;
    private int[] arr = new int[max];
    public static void main(String[] args){
            new Queen().queen8(0);
    }

    private void print(){
        for (int i : arr) {
            System.out.print(i+" ");
        }
        System.out.println();
    }

    private void queen8(int n){
        // System.out.println("n="+n);
        if(n == max){
            print();
            return;
        }
        for (int i = 1; i <= max; i++) {
            arr[n] = i;
            if(verify(n)){
                queen8(n+1);
            }
        }

    }

    private boolean verify(int n){

        for (int i = 0; i < n; i++) {
            if(arr[n] == arr[i] || Math.abs(n-i) == Math.abs(arr[n] -arr[i])){
                return false;
            }
        }
        return true;
    }
}
