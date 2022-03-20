package com.algorithm.sort;

import java.util.Arrays;

/**
 * ClassName: HeapSort
 * Description:
 * date: 2022/2/24 21:03
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class HeapSort {
    public static void main(String[] args) {
        //int[] arr = {4, 6, 8, 5, 9};
        int[] arr =RandomArray.randomArray(10);
        heapSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    // 将一个数组(二叉树)，调整为一个大顶堆

    public static  void heapSort(int[] arr){
        // 找出二叉树中大堆顶的数, i = arr.length/2-1 表示从一个非叶子节点开始比对，当i=0时，最终会将该树中最大数放到大堆顶
        for (int i = arr.length/2-1; i >=0 ; i--) {
            adjustHeap(arr,i, arr.length);
        }

        // 每次将堆顶的值移动到数组数组尾部(最大数放到length,第二大数放到length-1,第三大数放到Length-2...)直到全部有序
        for (int j = arr.length-1; j >0 ; j--) {

            // 将大堆顶的树放到尾部
            arr[0]^=arr[j];
            arr[j]^=arr[0];
            arr[0]^=arr[j];

            // 继续从 0 —— j位置找一个最大数放到大堆顶
            adjustHeap(arr,0,j);
        }
    }

    /**
     * @param arr    需要调正的数组
     * @param i      表示非叶子节点在数组中索引
     * @param length 表示对多少个元素进行调正
     */

    /*
    *  过程解析： 每次调用此方法会将数组中的一个length范围的最大数放到大堆顶(首索引位置)
    * */
    private static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i]; // 取出当前值

        // 每次调正一个局部大顶堆 ， 当上一个大顶堆的值被换下来后，再将该值移动到合适的局部堆位置
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {

            // 比较两个左右子节点谁大
            if (j + 1 < length && arr[j] < arr[j + 1]) {
                // 左子节点
                j++;
            }

            // 较大子节点和父节点比较
            if(arr[j]>temp){ // 如果条件成立
                arr[i] = arr[j]; //  则把较大子节点的值放到父节点上
                i = j; // i得到替换节点的索引
            }else{ // 如果条件不成立，说明该子树的已经找到了局部大顶堆，
                break;
            }

        }

        // 把temp(即原来位置的值)放到它该放到的位置(如果是局部最大数则i不会改变，否则该数会被放到某子节点位置)
        arr[i] = temp;
    }
}
