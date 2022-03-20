package com.data_structure.binarySortTree;

/**
 * ClassName: BinarySortTree
 * Description:
 * date: 2022/2/27 16:05
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class BinarySortTree {
    public static void main(String[] args) {
       BinaryTree binaryTree = new BinaryTree();
        int[] arr = {7, 3, 9, 1, 6, 8, 10, 4};
        binaryTree.add(arr);
        binaryTree.infixOrder();
        binaryTree.removeNode(7);
        binaryTree.infixOrder();
        System.out.println("根节点为：" + BinaryTree.root);
        binaryTree.removeNode(6);
        binaryTree.infixOrder();
        System.out.println("根节点为：" + BinaryTree.root);
        binaryTree.removeNode(4);
        binaryTree.infixOrder();
        System.out.println("根节点为：" + BinaryTree.root);
        binaryTree.removeNode(9);
        System.out.println("根节点为：" + BinaryTree.root);
        binaryTree.infixOrder();

        System.out.println("根节点为：" + BinaryTree.root);
        binaryTree.removeNode(3);

        System.out.println("根节点为：" + BinaryTree.root);
        binaryTree.infixOrder();
        System.out.println("根节点为：" + BinaryTree.root);
        binaryTree.removeNode(8);
        System.out.println("根节点为：" + BinaryTree.root);
        binaryTree.infixOrder();
        binaryTree.removeNode(1);
        System.out.println("根节点为：" + BinaryTree.root);
        binaryTree.infixOrder();
        binaryTree.removeNode(10);
        System.out.println("根节点为：" + BinaryTree.root);
        binaryTree.infixOrder();
    }

}

class BinaryTree {
    public static Node root;

    public void add(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (root == null) {
                root = new Node(arr[i]);
            } else {
                root.add(new Node(arr[i]));
            }
        }

    }

    public Node removeNode(int value) {
        return root.removeNode(value);
    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("此二叉树为空");
            return;
        }

        root.infixOrder();
    }

    public Node search(int value) {
        if (root == null) {
            System.out.println("当前二叉树为空");
            return null;
        }
        return root.search(value);
    }

    public Node searchParent(int value) {
        if (root == null) {
            System.out.println("当前二叉树为空");
            return null;
        }
        return root.searchParent(value);
    }

}

class Node {
    public Node left;
    public Node right;
    public Integer value;

    public Node(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }


    // 删除节点 （返回被删除的节点）
    public Node removeNode(int value) {
        Node node = search(value);

        // 过滤掉节点中不存在该值的情况
        if (node == null) {
            return null;
        }

        // 如果删除的是根节点
        if (node == BinaryTree.root) {
            // 拿到之前根节点的左右节点
            Node left = node.left;
            Node right = node.right;

            // 如果左边节点为空则从右边获取值
            Node midValue = getLeftMaxValue(node);
            if (midValue == null) {
                midValue = getRightValue(node);
            }
            // 如果待连接的(左)右节点和即将成为根节点的节点相同
            if (midValue == left || midValue == right) {

                // 即将称为根节点的节点 和左边的节点相同
                if (midValue == left) {
                    removeNode(left.value);
                    left = node.left;
                }
                // 即将成为根节点的节点 和右边的节点相同
                if (midValue == right) {
                    removeNode(right.value);
                    right = node.right;
                }
            }
            // 给新根节点连接左右节点
            midValue.left = left;
            midValue.right = right;
            // 移除当前root节点
            node.left = null;
            node.right = null;
            // 更新root节点
            BinaryTree.root = midValue;
            return node;

        }

        // 该值是一个叶子节点
        Node nodeParent = searchParent(value);
        if (node.left == null && node.right == null) {
            if (node == nodeParent.left) {
                nodeParent.left = null;
            }
            if (node == nodeParent.right) {
                nodeParent.right = null;
            }
            return node;
        } else if (node.left != null && node.right != null) { // 该值有两颗子树
            // 需要找一个该树中处在中等位置的一个值，该值位于 该树的左子树中的最右边的节点或右子树的最左边节点
            Node leftMaxValue = getLeftMaxValue(node);
            Node tmp = null; // 保存被删除的节点用于返回
            Node tmpChildTreeLeft = null; // 保存原有左子树
            Node tmpChildTreeRight = null; // 保存原有右子树

            // 删除的是左边这个子树
            if (nodeParent.left == node) {

                // 保存删除被删除树之间的节点
                tmp = nodeParent.left;
                tmpChildTreeLeft = nodeParent.left.left;
                tmpChildTreeRight = nodeParent.left.right;
                nodeParent.left = leftMaxValue;
            } else { // 删除的是右边这个子树
                tmp = nodeParent.right;
                tmpChildTreeLeft = nodeParent.right.left;
                tmpChildTreeRight = nodeParent.right.right;
                nodeParent.right = leftMaxValue;

            }
            leftMaxValue.left = tmpChildTreeLeft;
            leftMaxValue.right = tmpChildTreeRight;
            return tmp;

        } else { // 该值有一颗子树
            if (nodeParent.left == node) { // 该节点是左子节点
                if (node.left != null) {
                    nodeParent.left = node.left;
                } else {
                    nodeParent.left = node.right;
                }
            } else {  // 该节点是右子节点
                if (node.left != null) {
                    nodeParent.right = node.left;
                } else {
                    nodeParent.right = node.right;
                }
            }
            return node;
        }
    }

    public Node getLeftMaxValue(Node node) {
        if (node.left == null) {
            return null;
        }
        Node tmp = node.left;
        while (tmp.right != null) {
            tmp = tmp.right;
        }
        // 删除该节点
        removeNode(tmp.value);
        return tmp;
    }

    public Node getRightValue(Node node) {
        if (node.right == null) {
            return null;
        }
        Node tmp = node.right;
        while (tmp.left != null) {
            tmp = tmp.left;
        }
        // 删除该节点
        removeNode(tmp.value);
        return tmp;
    }

    // 搜索当前节点的值9
    public Node search(int value) {
        if (this.value == value) {
            return this;
        }
        Node tmp = null;
        if (this.left != null && value < this.value) {
            tmp = this.left.search(value);
            if (tmp != null) {
                return tmp;
            }
        }
        if (this.right != null && value > this.value) {

            tmp = this.right.search(value);
            if (tmp != null) {
                return tmp;
            }
        }
        return null;
    }

    // 搜索父节点的值
    public Node searchParent(int value) {
        // 每次查找都会从当前节点的父节点开始查找

        // 左边节点不等于空 且该节点的值等于传入的值 说明找到该父节点
        if (this.left != null && (this.left.value == value)) {
            return this;
        }
        // 右边节点不等于空 且该节点的值等于传入的值 说明找到该父节点
        if (this.right != null && (this.right.value == value)) {
            return this;
        }
        // 否则执行以下语句

        Node tmp = null;
        // 如果传入的值小于该父节点，则从父节点的左边开始继续查找
        if (value < this.value) {
            if (this.left != null) {
                tmp = this.left.searchParent(value);
                if (tmp != null) {
                    return tmp;
                }
            }
        } else {
            // 否则从父节点的右边开始查找
            if (this.right != null) {
                tmp = this.right.searchParent(value);
                if (tmp != null) {
                    return tmp;
                }
            }
        }
        return null;
    }

    // 确定node所在的位置
    public void add(Node node) {
        if (node.value < this.value) {
            // 放在this的左边
            if (this.left != null) {
                this.left.add(node);
            } else {
                this.left = node;
            }

        } else {
            // 放在this的右边
            if (this.right != null) {
                this.right.add(node);
            } else {
                this.right = node;
            }
            // 放在this的右边
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}
