package com.algorithm.divide_and_conquer;

/**
 * ClassName: Hanoitower
 * Description:
 * date: 2022/3/4 14:18
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class HanoiTower {
    public static void main(String[] args) {
        move(3,"A","B","C");
    }

    public static void move(int nums, String a, String b, String c) {
        // 使用分治法解决汉诺塔问题
        // 将汉诺塔的n层看成两个部分，即 最底层的一个部分，和其上的一部分
        if (nums == 1) {
            // 说明此时只有最底层的一个盘，将其移动到c计科
            System.out.println("第1个盘子移动到" + c);
        } else {
            // 移动其上部分
            move(nums - 1, a, c, b); // 将c位置的盘子借助c移动到b
            System.out.println("第" + nums + "个盘子从" + a + "->" + c);
            move(nums - 1, b, a, c);// 将b位置的盘子借助a移动到c
        }
    }
}
