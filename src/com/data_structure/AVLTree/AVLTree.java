package com.data_structure.AVLTree;

/**
 * ClassName: AVLTree
 * Description:
 * date: 2022/3/2 10:32
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class AVLTree {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        // int[] arr = {4, 3, 6, 5, 7, 8}; // 右旋转情况
        //int[] arr = {10, 8, 12, 7, 9, 6};   // 左旋转情况
        //int[] arr = {10, 7, 12, 6, 8, 9};   // 局部旋转+左旋转情况
        int[] arr = {8, 7, 11, 10, 12, 9};  // 局部旋转+右旋转情况
        binaryTree.add(arr);
        System.out.println("树的高度" + binaryTree.treeHeight());
        System.out.println("左子树高度" + binaryTree.leftHeight());
        System.out.println("右子树高度" + binaryTree.rightHeight());
        binaryTree.infixOrder();
        binaryTree.reverse();
        System.out.println("=========");
        binaryTree.infixOrder();
    }

}

class BinaryTree {
    public static Node root;

    // 添加到树节点中(包含平衡树判断)
    public void add(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (root == null) {
                root = new Node(arr[i]);
            } else {
                root.add(new Node(arr[i]));
            }

            // 每次添加完一个节点后，判断其是否需要(左，右)旋转
            if (root.rightHeight() - root.leftHeight() > 1) {
                // 需要左旋转


                // 每次添加完一个节点后，判断其是否需要局部旋转
                if (root.right.left != null && root.right.left.left != null) {
                    root.rightPartRotate();
                }

                // 左旋转
                root.leftRotate();
            }
            if (root.leftHeight() - root.rightHeight() > 1) {
                // 需要右旋转

                // 每次添加完一个节点后，判断其是否需要局部旋转(完成左边局部有序)
                if (root.left.right != null && root.left.right.right != null) {
                    root.leftPartRotate();
                }

                root.rightRotate();
            }
        }

    }

    public Node removeNode(int value) {
        return root.removeNode(value);
    }

    public void reverse() {
         root.reverse();
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

    // 左子树局部旋转
    public void leftPartRotate() {
        this.root.leftPartRotate();
    }

    // 右子树局部旋转
    public void rightPartRotate() {
        this.root.rightPartRotate();
    }

    public int leftHeight() {
        if (root != null && root.left != null) {
            return root.leftHeight();
        }
        return -1;
    }

    public int rightHeight() {
        if (root != null && root.right != null) {
            return root.rightHeight();
        }
        return -1;
    }

    public int treeHeight() {
        if (root != null) {
            return root.height();
        }
        return -1;
    }

    public void leftRotate() {
        if (root != null && root.rightHeight() - root.leftHeight() > 1) {
            root.leftRotate();
            System.out.println("左旋转成功");
        }
    }

    public void rightRotate() {
        if (root != null && root.leftHeight() - root.rightHeight() > 1) {
            root.rightRotate();
            System.out.println("右旋转成功");
        }
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

    // 左子树高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return this.left.height();
    }

    // 右子树高度
    public int rightHeight() {
        if (this.right == null) {
            return 0;
        }
        return this.right.height();
    }

    // 左子树局部旋转
    public void leftPartRotate() {
        Node moveNode = this.left; // 保留需要移动的左子树节点
        this.left = moveNode.right; // 根节点连接新的左子树节点
        moveNode.right = null; // 以前的左子树节点置空

        this.left.left = moveNode; // 新的左子树节点指向旧子树节点

    }

    // 右子树局部旋转
    public void rightPartRotate() {
        Node moveNode = this.right; //保留需要移动的右子树节点
        this.right = moveNode.left; // 根节点连接新的右子树节点
        moveNode.left = null; // 旧右子树节点 取消指向
        this.right.right = moveNode; // 新的右子树节点指向旧子树节点
    }

    // 左旋转(当右边的树高度大于左书高度时(右边树高度-左边树高度的绝对值大于1))
    public void leftRotate() {
        Node newRoot = this.right; // 记录新根节点
        this.right = newRoot.left; // 使旧根节点的右节点指向其左节点
        newRoot.left = this; // 使新节点的左节点指向旧节点
        BinaryTree.root = newRoot; // 设置新的节点
    }

    // 右旋转(当左边的树高度大于右书高度时(左边树高度-右边树高度的绝对值大于1))
    public void rightRotate() {
        Node newRoot = this.left; // 记录新根节点
        this.left = newRoot.right;// 使旧根节点的右节点指向其左节点
        newRoot.right = this; // 使新节点的右节点指向旧节点
        BinaryTree.root = newRoot; // 设置新的节点

    }

    // 返回以该节点开始计算树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
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

    // 反转二叉树
    public void reverse() {
        Node tmp = this.left;
        this.left = this.right;
        this.right = tmp;
        if (this.left != null) {
            this.left.reverse();
        }
        if (this.right != null) {
            this.right.reverse();
        }
    }
}
