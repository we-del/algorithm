package com.data_structure.tree;

import com.data_structure.hashtable.HashTable;
import com.utils.MyUtil;

import java.util.Scanner;

/**
 * ClassName: BinaryTrueTraversal
 * Description: 此类为二叉树的遍历 ，而擦函数分为 前序，中序和后续遍历
 * date: 2022/2/20 15:56
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class BinaryTrueTraversal {
    public static void main(String[] args) {
        int count = 0;
        HeroNode heroNode1 = new HeroNode(++count, MyUtil.getRandomName());
        HeroNode heroNode2 = new HeroNode(++count, MyUtil.getRandomName());
        HeroNode heroNode3 = new HeroNode(++count, MyUtil.getRandomName());
        HeroNode heroNode4 = new HeroNode(++count, MyUtil.getRandomName());
        HeroNode heroNode5 = new HeroNode(++count, MyUtil.getRandomName());
        HeroNode heroNode6 = new HeroNode(++count, MyUtil.getRandomName());
        HeroNode heroNode7 = new HeroNode(++count, MyUtil.getRandomName());
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(heroNode1);
        binaryTree.getRoot().setLeft(heroNode3);
        binaryTree.getRoot().setRight(heroNode5);
        binaryTree.getRoot().getLeft().setLeft(heroNode2);
        binaryTree.getRoot().getLeft().setRight(heroNode6);
        binaryTree.getRoot().getRight().setLeft(heroNode4);
        binaryTree.getRoot().getRight().setRight(heroNode7);

        Scanner scanner = new Scanner(System.in);
        String key = null;
        boolean loop = true;
        while (loop) {

            System.out.println("================================");
            System.out.println("find 查找员工");
            System.out.println("list 列出员工");
            System.out.println("remove 删除员工");
            System.out.println("quit 退出");
            key = scanner.next();
            switch (key) {
                case "find":
                    System.out.println("请输入查找的编码(数字)");
                    int n = Integer.parseInt(scanner.next());
                    HeroNode heroNode = binaryTree.infixSearch(n);
                    if (heroNode != null) {
                        System.out.println("成员：" + heroNode.getName() + " 在链表中");
                    } else {
                        System.out.println("输入的成员不存在");
                    }
                    break;
                case "list":
                    binaryTree.infixOrder();
                    break;
                case "remove":
                    System.out.println("请输入删除的编码(数字)");
                    int no = Integer.parseInt(scanner.next());
                    binaryTree.removeNode(no);
                    System.out.println("删除操作完成");
                    break;
                case "quit":
                    System.out.println("正在退出...");
                    try {
                        Thread.sleep(((int) (Math.random() * 300)) + 400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("退出完成");
                    loop = false;
                    break;
            }
        }
    }
}

class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public HeroNode getRoot() {
        return root;
    }

    public void preOrder() {
        this.root.preOrder();
    }

    public void infixOrder() {
        this.root.infixOrder();
    }

    public void afterOrder() {
        this.root.afterOrder();
    }

    public HeroNode preSearch(int n) {
        return root.preFind(n);
    }

    public HeroNode infixSearch(int n) {
        return root.infixFind(n);
    }

    public HeroNode afterSearch(int n) {
        return root.afterFind(n);
    }

    // 删除节点
    public void removeNode(int id){
        if(root.getId() == id){
            root = null;
        }else{
            root.removeNode(id);
        }
    }
}

class HeroNode {
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // 前序遍历
    public void preOrder() {
        System.out.print(this + " ");
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.print(this + " ");
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    // 后序遍历
    public void afterOrder() {
        if (this.left != null) {
            this.left.afterOrder();
        }
        if (this.right != null) {
            this.right.afterOrder();
        }
        System.out.print(this + " ");
    }

    // 前序查找
    public HeroNode preFind(int id) {
        if (this.id == id) {
            return this;
        }
        HeroNode no = null;
        if (this.left != null) {
            no = this.left.preFind(id);
            if (no != null) {

                return no;
            }
        }
        if (this.right != null) {
            no = this.right.preFind(id);
            if (no != null) {
                return no;
            }
        }
        return null;
    }


    // 中序查找
    public HeroNode infixFind(int id) {

        HeroNode no = null;
        if (this.left != null) {
            no = this.left.preFind(id);
            if (no != null) {

                return no;
            }
        }

        if (this.id == id) {
            return this;
        }
        if (this.right != null) {
            no = this.right.preFind(id);
            if (no != null) {
                return no;
            }
        }
        return null;
    }

    // 后序查找
    public HeroNode afterFind(int id) {
        HeroNode no = null;
        if (this.left != null) {
            no = this.left.preFind(id);
            if (no != null) {

                return no;
            }
        }
        if (this.right != null) {
            no = this.right.preFind(id);
            if (no != null) {
                return no;
            }
        }

        if (this.id == id) {
            return this;
        }
        return null;
    }

    // 删除节点（连接删除的数）
    public void removeNode(int id) {

        if(this.left != null && this.left.id == id){
            HeroNode temp1 = null;
            HeroNode temp2 = null;
            if(this.left.left!=null){ // 拿到即将删除节点的左子树
                temp1 = this.left.left;
            }
            if(this.left.right!=null){  // 拿到即将删除节点的右子树
                temp2 = this.left.right;
            }
            this.left = temp1; // 连接子树
            temp1.right = temp2; // 该子树下挂载右子树

            return;
        }
        if(this.right != null && this.right.id == id){

            HeroNode temp1 = null;
            HeroNode temp2 = null;
            if(this.right.left!=null){ // 拿到即将删除节点的左子树
                temp1 = this.right.left;
            }
            if(this.right.right!=null){  // 拿到即将删除节点的右子树
                temp2 = this.right.right;
            }
            this.left = temp1; // 连接子树
            temp1.right = temp2; // 该子树下挂载右子树
        }

        if(this.left!=null){
            this.left.removeNode(id);
        }
        if(this.right!=null){
            this.right.removeNode(id);
        }



    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }
}

