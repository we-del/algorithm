package com.data_structure.tree;

/**
 * ClassName: ThreadedBinaryTree
 * Description: 线索二叉树
 * date: 2022/2/22 15:11
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class ThreadedBinaryTree {
    public static void main(String[] args) {
        HeroNode2 jimmy = new HeroNode2(1, "jimmy");
        HeroNode2 merry = new HeroNode2(3, "merry");
        HeroNode2 len = new HeroNode2(8, "len");
        HeroNode2 mary = new HeroNode2(10, "mary");
        HeroNode2 wd = new HeroNode2(6, "wd");
        HeroNode2 ed = new HeroNode2(14, "ed");
        HeroNode2 hd = new HeroNode2(18, "hd");
        BinaryTree2 binaryTree2 = new BinaryTree2();
        binaryTree2.setRoot(jimmy);
        jimmy.setLeft(merry);
        jimmy.setRight(wd);
        merry.setLeft(len);
        merry.setRight(mary);
        wd.setLeft(ed);
        wd.setRight(hd);
        binaryTree2.infixThreadNodes(); // 完成线索二叉树
//        System.out.println(len.getLeft());
//        System.out.println(len.getRight());
        binaryTree2.threadedList();


    }
}

class HeroNode2 {
    private int id;
    private String name;
    private HeroNode2 left;
    private HeroNode2 right;

    // 如果 leftType && rightType 为0 表示指向子树，为 1 表示指向前驱&&后继节点
    private int leftType;
    private int rightType;

    @Override
    public String toString() {
        return "HeroNode2{" +
                "id=" + id +
                ", name='" + name + '\'' +

                '}';
    }

    public HeroNode2(int id, String name) {
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


    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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

    public HeroNode2 getLeft() {
        return left;
    }

    public void setLeft(HeroNode2 left) {
        this.left = left;
    }

    public HeroNode2 getRight() {
        return right;
    }

    public void setRight(HeroNode2 right) {
        this.right = right;
    }
}

class BinaryTree2 {
    private HeroNode2 root;

    // 为实现线索化，需要创建一个指向当前节点的前驱节点的辅助指针
    private HeroNode2 pre = null;

    public void setRoot(HeroNode2 root) {
        this.root = root;
    }

    public HeroNode2 getRoot() {
        return root;
    }

    public void infixThreadNodes() {
        infixThreadNodes(root);
    }



    public void threadedList() {
        HeroNode2 node = root;

        while (node != null) {

            // 找到一个leftType为1的节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node); // 打印该节点


            // 每有一个叶子节点就打印依次
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            // 最后移动到非叶子节点上
            node = node.getRight();

        }
    }


    // 线索二叉树中序遍历
    public void infixThreadedList() {
        HeroNode2 node = root;

        // 找到开始的节点
        while(node.getLeftType() == 0){
            node = node.getLeft();
        }

        // 开始遍历后继节点
        while(node != null){
            System.out.println(node);
            node = node.getRight();
        }
    }

    // 线索化二叉树(中序遍历)
    public void infixThreadNodes(HeroNode2 node) {
        if (node == null) {
            return;
        }
        infixThreadNodes(node.getLeft());
        if (node.getLeft() == null) { // 使当前几点指向他的前驱节点
            node.setLeft(pre);
            node.setLeftType(1);
        }

        if (pre != null && pre.getRight() == null) {
            pre.setRight(node); // 使辅助指针指向的节点的right指向后继节点
            pre.setRightType(1);
        }

        // 辅助节点记录当前节点
        pre = node;

        infixThreadNodes(node.getRight());
    }

    // 线索化二叉树(前序)
    public void preThreadNodes(HeroNode2 node) {
        if (node == null) {
            return;
        }
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        preThreadNodes(node.getLeft());
        preThreadNodes(node.getRight());
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


}
