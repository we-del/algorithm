package com.leetcode.middle;

import javax.swing.tree.TreeNode;
import java.util.Deque;
import java.util.LinkedList;

/**
 * ClassName: RestructureBinaryTree
 * Description:
 * date: 2022/3/18 10:18
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class RestructureBinaryTree {
    public static void main(String[] args){
        Solution solution = new Solution();
        Solution.TreeNode treeNode = solution.buildTree(new int[]{3, 9, 5, 4, 1, 7, 6, 10, 20, 15, 8}, new int[]{4, 5, 1, 9, 6, 7, 10, 3, 15, 20, 8});
        System.out.println(treeNode);

    }
    private static class Solution {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder == null || preorder.length == 0) {
                return null;
            }
            TreeNode root = new TreeNode(preorder[0]);
            Deque<TreeNode> stack = new LinkedList<TreeNode>();
            stack.push(root);
            int inorderIndex = 0;
            for (int i = 1; i < preorder.length; i++) {
                int preorderVal = preorder[i];
                TreeNode node = stack.peek();
                if (node.val != inorder[inorderIndex]) {
                    node.left = new TreeNode(preorderVal);
                    stack.push(node.left);
                } else {
                    while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                        node = stack.pop();
                        inorderIndex++;
                    }
                    node.right = new TreeNode(preorderVal);
                    stack.push(node.right);
                }
            }
            return root;
        }



        private class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode() {
            }

            @Override
            public String toString() {
                return "TreeNode{" +
                        "val=" + val +
                        ", left=" + left +
                        ", right=" + right +
                        '}';
            }

            TreeNode(int val) {
                this.val = val;
            }

            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }
    }
}


