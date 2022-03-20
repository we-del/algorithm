package com.data_structure.tree;

/**
 * ClassName: OrderStoreTree
 * Description: 顺序存储二叉树
 * date: 2022/2/21 21:50
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class OrderStoreTree {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
        System.out.println();
        arrBinaryTree.infixOrder();
        System.out.println();
        arrBinaryTree.afterOrder();
        System.out.println();
    }
}

class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    // 重载方法，此时代码整洁
    public void preOrder() {
        preOrder(0);
    }
    public  void infixOrder(){
        infixOrder(0);
    }
    public void afterOrder(){
        afterOrder(0);
    }
    // 前序遍历
    public void preOrder(int index) {
        if (index < 0 && index >= arr.length) {
            System.out.println("请传入正确的索引");
            return;
        }
        System.out.print(arr[index] + " ");

        // 左遍历
        if (2 * index + 1 < arr.length) {
            preOrder(2 * index + 1);
        }
        if (2 * index + 2 < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    // 中序遍历
    public void infixOrder(int index) {
        if (index < 0 && index >= arr.length) {
            System.out.println("请传入正确的索引");
            return;
        }

        // 左遍历
        if (2 * index + 1 < arr.length) {
            infixOrder(2 * index + 1);
        }

        System.out.print(arr[index] + " ");

        if (2 * index + 2 < arr.length) {
            infixOrder(2 * index + 2);
        }
    }

    // 后序遍历
    public void afterOrder(int index) {
        if (index < 0 && index >= arr.length) {
            System.out.println("请传入正确的索引");
            return;
        }

        // 左遍历
        if (2 * index + 1 < arr.length) {
            afterOrder(2 * index + 1);
        }
        if (2 * index + 2 < arr.length) {
            afterOrder(2 * index + 2);
        }
        
        System.out.print(arr[index] + " ");

    }
}
