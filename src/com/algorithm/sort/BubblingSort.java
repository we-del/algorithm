package com.algorithm.sort;

/**
 * ClassName: BubblingSort
 * Description:
 * date: 2022/2/13 16:05
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class BubblingSort {
    static int count;
    public static void main(String[] args) {
        //int[] arr = {1,3,2,4,5};
        int[] arr =RandomArray.randomArray(50000);
        speedTest(arr);
    }
    public static void bubblingSort(int[] arr){
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 2 3 4 5 1 | 2 3 4 1 5 || 2 3 1 4 5 || 2 1 3 4 5 || 1 2 3 4 5
                    flag = true; // 记录是否交换过位置
                    arr[j] ^= arr[j + 1];
                    arr[j + 1] ^= arr[j];
                    arr[j] ^= arr[j + 1];
                }
            }
            if(flag){
                // 重置flag
                flag = false;
            }else{
                // 没有交换过位置 说明 此数组已经有序
                break;
            }
        }
    }

    public static void speedTest(int[] arr){
        long l = System.currentTimeMillis();
        System.out.println("冒泡开始排序");
        bubblingSort(arr);
        System.out.println("冒泡结束排序");
        System.out.println(System.currentTimeMillis()-l);
    }
}
