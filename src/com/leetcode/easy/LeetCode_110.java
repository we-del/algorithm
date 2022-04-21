package com.leetcode.easy;

/**
 * ClassName: LeetCode_110
 * Description:
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 本题中，一棵高度平衡二叉树定义为：
 * <p>
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 * <p>
 * date: 2022/4/21 10:20
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class LeetCode_110 {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 9, 20, 15, 7,2,1};
        TreeNode treeNode = new TreeNode();
        for (int i : arr) {
            treeNode.add(i);
        }
        treeNode.infixOrder();
        System.out.println(treeNode.treeHeight());
        System.out.println(treeNode.treeLeftHeight());
        System.out.println(treeNode.treeRightHeight());
        System.out.println(treeNode.isAVL());
    }

    private static class TreeNode {
        Node root;

        public void add(int val) {
            if (root == null) {
                root = new Node(val);
                return;
            }
            root.add(new Node(val));
        }

        public boolean isAVL() {
            return !(Math.abs(this.treeLeftHeight() - this.treeRightHeight()) > 1);
        }

        public int treeHeight() {
            return this.root.treeHeight();
        }

        public int treeLeftHeight() {
            if (this.root.left == null) return 0;
            return this.root.left.treeHeight();
        }

        public int treeRightHeight() {
            if (this.root.right == null) return 0;
            return this.root.right.treeHeight();
        }

        public void infixOrder() {
            this.root.infixOrder();
        }
    }

    private static class Node {
        Node left;
        Node right;
        int value;

        public Node(int value) {
            this.value = value;
        }

        public void infixOrder() {
            if (this != null && this.left != null) {
                this.left.infixOrder();
            }
            System.out.println(this.value);
            if (this != null && this.right != null) {
                this.right.infixOrder();
            }
        }

        public void add(Node node) {
            // 如果root节点为空则直接指向
            if (node.value > this.value) {
                if (this.right != null) {
                    this.right.add(node);
                } else {
                    this.right = node;
                }
            } else {
                if (this.left != null) {
                    this.left.add(node);
                } else {
                    this.left = node;
                }
            }
        }

        public int treeHeight() {
            if (this == null) return 0;
            return Math.max(this.left == null ? 0 : this.left.treeHeight(), this.right == null ? 0 : this.right.treeHeight()) + 1;
        }
    }
}
