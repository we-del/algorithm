package com.data_structure.sparseArray;

import java.io.*;

/**
 * ClassName: SparseArray
 * Description:
 * date: 2022/2/7 20:08
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class SparseArray {
    public static void main(String[] args) {
        // 创建一个稀疏数组 ， 稀疏数组是一个二维数组存储着另一个二维数组的有效数据
        int row = 7;
        int col = 7;
        int[][] arr = new int[row][col];
        // 模拟数组中已有数据
        arr[3][4] = 12;
        arr[1][2] = 13;
        arr[4][1] = 7;
        arr[6][4] = -21;
        arr[1][6] = 54;
        arr[0][4] = 22;
        int count = 0; // 此属性用于记录有效的数据个数
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) count++;
            }
        }

        int[][] sparseArray = new int[count + 1][3];
        // 初始化第一行的数据
        sparseArray[0][0] = row;
        sparseArray[0][1] = col;
        sparseArray[0][2] = count;
        count = 1;

        // 存储稀疏数据
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    sparseArray[count][0] = i; // 存储行
                    sparseArray[count][1] = j; // 存储列
                    sparseArray[count][2] = arr[i][j]; // 存储值
                    count++;
                }
            }
        }


        // 查看稀疏数组的数据
        for (int[] ints : sparseArray) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

        // 将稀疏数组存储到磁盘中
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream("D:\\aLearning\\java\\data_structure_code\\com\\sparseArray.data"));
            objectOutputStream.writeObject(sparseArray); // 将稀疏数组以对象保存
        } catch (IOException e) {
            e.printStackTrace();
        }


        // ============================ 稀疏数组 ----> 原始二维数组
        // 读取磁盘中的稀疏数组
        int[][] sparseArr = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    new FileInputStream("D:\\aLearning\\java\\data_structure_code\\com\\sparseArray.data"));
            sparseArr = (int[][]) objectInputStream.readObject(); // 将对象反序列化为稀疏数组
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 将稀疏数组转换为二维数组
        int[][] arrFromSparseArr = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < arrFromSparseArr.length; i++) {
            // 将稀疏数组存储的行列和值装载入arrFromSparseArr二维数组中
            arrFromSparseArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        // 查看装载结果
        for (int i = 0; i < arrFromSparseArr.length; i++) {
            for (int j = 0; j < arrFromSparseArr[i].length; j++) {
                if (arrFromSparseArr[i][j] != 0) {
                    System.out.println("在" + i + "行" + j + "列有一个值为" + arrFromSparseArr[i][j]);

                }
            }
        }
    }
}



