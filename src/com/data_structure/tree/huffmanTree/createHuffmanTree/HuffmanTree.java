package com.data_structure.tree.huffmanTree.createHuffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ClassName: HuffmanTree
 * Description:
 * date: 2022/2/25 17:12
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class HuffmanTree {
    public static void main(String[] args){
        int[] arr = {13,7,8,3,29,6,1};
        createHuffmanTree(arr);
    }
    // 创建赫夫曼树的方法
    public static void createHuffmanTree(int[] arr){

        // 创建一个集合进行存储node节点
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        System.out.println(nodes);

        // 当nodes.size() 小于等于1时 结束，此时说明霍夫曼树创建完毕
        while(nodes.size()>1){
            // 当传入一个参数(集合)时，该集合必须实现Comparable方法,Collections会去调用该集合下的compareTo方法
            Collections.sort(nodes); // 每次完成树创建后重新排序
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parentNodes = new Node(left.value + right.value);
            parentNodes.left = left;
            parentNodes.right = right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parentNodes);
        }
        preBinaryTree(nodes.get(0));

    }

    // 前序遍历二叉树
    public static void preBinaryTree(Node node){
        if(node == null){
            return;
        }
        System.out.println(node.value);
        preBinaryTree(node.left);
        preBinaryTree(node.right);
    }
}

// 为了让Node  对象持续排序 Collections集合
// 让Node 类 实现Comparable接口
class Node implements Comparable<Node>{
    int value; // 节点的权
    Node left; // 左子节点
    Node right; // 右子节点

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
