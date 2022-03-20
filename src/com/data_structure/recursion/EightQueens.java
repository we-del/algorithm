package com.data_structure.recursion;

/**
 * ClassName: EightQueens
 * Description:
 * date: 2022/3/19 21:37
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class EightQueens {
    //编写一个main方法
    public static void main (String[] args) {




        Queens queen = new Queens();
        queen.seat(0);

    }
}

class Queens {

    int[] arr = new int[8];//创建一个数组接收皇后位置


    public void seat(int n) {  //找皇后的位置,n表示第一个皇后的位置
        if (n == 8) { //判断是否为最后一个皇后
            print();	//如果最后一个成功则输出
            return;

        }

        for(int i = 1; i <= 8; i++){
            arr[n] = i; //i表示每一个皇后的行,n表示皇后的列
            if (judge(n)) { //判断是否有冲突
                seat(n + 1); //没有冲突则判断下一个棋子
            }
        }
    }

    public boolean judge(int n) { //判断皇后的位置是否与其它皇后冲突
        for (int i = 0; i < n; i++) {
            //1 arr[i] == arr[n] 是判断棋子是否有在同一列的情况
            //2 Math.abs(arr[i] - arr[n]) == Math.abs(i - n)
            //  是判断棋子是否在同一斜线
            if (arr[i] == arr[n] || Math.abs(arr[i] - arr[n]) == Math.abs(i - n)) {
                return false;
            }

        }
        return true;
    }


    public void print() {
        for (int i = 0; i < 8; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
