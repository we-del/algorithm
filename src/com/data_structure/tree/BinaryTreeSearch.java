package com.data_structure.tree;

import com.utils.MyUtil;

/**
 * ClassName: BinaryTreeSearch
 * Description: 二叉树的前序中序后序查找
 * date: 2022/2/21 14:53
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class BinaryTreeSearch {
    public static void main(String[] args){
        int count = 0;
        PersonageNode personageNode1 = new PersonageNode(++count, MyUtil.getRandomName());
        PersonageNode personageNode2 = new PersonageNode(++count, MyUtil.getRandomName());
        PersonageNode personageNode3 = new PersonageNode(++count, MyUtil.getRandomName());
        PersonageNode personageNode4 = new PersonageNode(++count, MyUtil.getRandomName());
        PersonageNode personageNode5 = new PersonageNode(++count, MyUtil.getRandomName());
        PersonageNode personageNode6 = new PersonageNode(++count, MyUtil.getRandomName());
        PersonageNode personageNode7 = new PersonageNode(++count, MyUtil.getRandomName());
        PersonageNode personageNode8 = new PersonageNode(++count, MyUtil.getRandomName());
        BinaryTree1 binaryTree1 = new BinaryTree1();
        binaryTree1.setRoot(personageNode1);


    }

}
class PersonageNode{
    private int id;
    private String name;
    private PersonageNode left;
    private PersonageNode right;

    public PersonageNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // 前序查找
    public PersonageNode preFind(int id){
        if(this.id == id){
            return this;
        }
        if(this.left != null){
          return  this.left.preFind(id);
        }
        if (this.right != null) {
          return  this.right.preFind(id);
        }
        return null;
    }


    // 中序查找
    public PersonageNode infixFind(int id){

        if(this.left != null){
            return  this.left.preFind(id);
        }
        if(this.id == id){
            return this;
        }
        if (this.right != null) {
            return  this.right.preFind(id);
        }
        return null;
    }

    // 后序查找
    public PersonageNode afterFind(int id){

        if(this.left != null){
            return  this.left.preFind(id);
        }
        if (this.right != null) {
            return  this.right.preFind(id);
        }

        if(this.id == id){
            return this;
        }
        return null;
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

    public PersonageNode getLeft() {
        return left;
    }

    public void setLeft(PersonageNode left) {
        this.left = left;
    }

    public PersonageNode getRight() {
        return right;
    }

    public void setRight(PersonageNode right) {
        this.right = right;
    }
}
class BinaryTree1{
    private PersonageNode root;

    public void setRoot(PersonageNode root) {
        this.root = root;
    }
    public PersonageNode preSearch(int n){
        return root.preFind(n);
    }
    public PersonageNode infixSearch(int n){
        return root.infixFind(n);
    }

    public PersonageNode afterSearch(int n) {
        return root.afterFind(n);
    }
}
