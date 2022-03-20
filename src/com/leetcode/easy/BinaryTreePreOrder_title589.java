package com.leetcode.easy;

/**
 * ClassName: BinaryTreePreOrder_title0
 * Description:
 * date: 2022/3/10 9:54
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class BinaryTreePreOrder_title589 {
    public static void main(String[] args) {
        answer();
    }

    public static void answer() {
        BinaryTree binaryTree = new BinaryTree();
        Node node1 = new Node(2);
        Node node2 = new Node(7);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(1);
        binaryTree.setRoot(node1);
        binaryTree.add(node2);
        binaryTree.add(node3);
        binaryTree.add(node4);
        binaryTree.add(node5);
        binaryTree.add(node6);
        binaryTree.preOrder();
    }

    private static class BinaryTree {
        public Node root;
        public int high;
        public int low;

        public BinaryTree(Node root) {
            this.root = root;
        }

        public BinaryTree() {
        }

        public void setRoot(Node root) {
            this.root = root;
        }

        public void add(Node node) {
            root.add(node);
        }

        public void preOrder() {
            this.root.preOrder();
        }
    }

    private static class Node {
        public Node left;
        public Node right;
        public int value;

        public Node(int value) {
            this.value = value;
        }

        // 顺序添加
        public void add(Node node) {
            if (node.value < this.value) {
                if (this.left != null) {
                    this.left.add(node);
                } else {
                    this.left = node;
                }
            }
            if (node.value >= this.value) {
                if (this.right != null) {
                    this.right.add(node);
                } else {
                    this.right = node;
                }
            }
        }

        // 前序遍历
        public void preOrder() {
            System.out.println(this.value);
            if (this.left != null) {
                this.left.preOrder();
            }
            if (this.right != null) {
                this.right.preOrder();
            }
        }
    }
}
