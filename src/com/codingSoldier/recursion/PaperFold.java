package com.codingSoldier.recursion;

/**
 * ClassName: PapperFold
 * Description:
 * date: 2022/3/21 12:01
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class PaperFold {
    public static void main(String[] args) {
        process(4);
    }

    /**
     *  折痕 可以看成一颗满二叉树 ， 递归模拟的二叉树也可以完成前序中序后序遍历
     * */
    public static void fold(int n, int N, boolean down) {
        if (n >= N) return;

        System.out.print(down ? "凹 " : "凸 ");
        fold(n + 1, N, true);
        fold(n + 1, N, false);
    }

    // 对该方法在进行封装，外部只有传入简单的参数即可完成调用
    public static void process(int N) {
        fold(1, N, true);
    }
}
