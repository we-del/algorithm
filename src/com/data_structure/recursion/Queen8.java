package com.data_structure.recursion;

/**
 * ClassName: Queen8
 * Description:
 * date: 2022/2/12 20:03
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class Queen8 {
    static int count;
    int max = 8;
    int[] array = new int[max];

    public static void main(String[] args) {
            new Queen8().check(0);
            System.out.println(count);
    }

    private void check(int n){

        // 已经找到结果
        if(n == max){
            print();
            return;
        }
        // check 是每一次递归时进入到check中都有 for(int i =0; i<max;i++)的回溯，直到for循环结束为止
        for(int i =0; i<max;i++){
            array[n] = i;
            count++;
            if(judge(n)){
                check(n+1);
            }

            // 如果冲突，则继续执行 array[n] = i; 极限该行的位移
        }
    }

    private void print(){

        for(int i =0; i< array.length;i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }

    // 判断是否在同一行或同一斜线
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //  array[i] == array[n] 表示为 同 列
            //  Math.abs(n - i) == Math.abs(array[n] - array[i]) 表示为同一斜线   1-0 == 1-0   1: 0   2:1
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {

                count++;
                return false;
            }
        }
        return true;
    }
}
