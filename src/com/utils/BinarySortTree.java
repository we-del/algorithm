package com.utils;

/**
 * ClassName: TT
 * Description:
 * date: 2022/3/1 17:36
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
        binaryTree.list();
        System.out.println("根节点为：" + BinaryTree.getRoot());
        binaryTree.delete(7);
        System.out.println("根节点为：" + BinaryTree.getRoot());
        binaryTree.delete(6);
        System.out.println("根节点为：" + BinaryTree.getRoot());
        binaryTree.delete(4);
        binaryTree.list();
        System.out.println("根节点为：" + BinaryTree.getRoot());
        binaryTree.delete(9);
        binaryTree.list();
        System.out.println("根节点为：" + BinaryTree.getRoot());
        binaryTree.delete(3);
        binaryTree.list();
        System.out.println("根节点为：" + BinaryTree.getRoot());
        binaryTree.list();
        binaryTree.delete(8);
        System.out.println("根节点为：" + BinaryTree.getRoot());
        binaryTree.list();
    }
}

class BinaryTree {
    private static Node root;

    public static Node getRoot() {
        return root;
    }

    public static void setRoot(Node root) {
        BinaryTree.root = root;
    }

    public void add(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (root == null) {
                root = new Node(arr[i]);
            } else {
                root.add(new Node(arr[i]));
            }
        }

    }

    public void list() {
        root.list();
    }

    public void delete(int value) {
        root.delete(value);
    }

    public void update(int oldValue, int value) {
        root.update(oldValue, value);
    }

    public Node search(int value) {
        return root.search(value);
    }


}

class Node {
    private Node left;
    private Node right;
    private Integer value;

    public Node(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    // 增
    public void add(Node node) {
        if (node.value < this.value) {
            if (this.left != null) {
                this.left.add(node);
            } else {
                this.left = node;
            }
        } else {
            if (this.right != null) {
                this.right.add(node);
            } else {
                this.right = node;
            }
        }
    }

    // 删
    public void delete(int value) {

        // 移除的是根节点
        if (BinaryTree.getRoot().value == value) {
            Node left = BinaryTree.getRoot().left;
            Node right = BinaryTree.getRoot().right;
            Node newRoot = getPivotValue();

            if (left == newRoot || right == newRoot) {
                if (left == newRoot) {
                    delete(left.value);
                }
                if (right == newRoot) {
                    delete(right.value);
                }
            }

            // 使原根节点指向null
            BinaryTree.getRoot().left = null;
            BinaryTree.getRoot().right = null;

            // 新根节点连接左右节点
            newRoot.left = left;
            newRoot.right = right;
            BinaryTree.setRoot(newRoot);
            return;
        }

        Node awaitRemoveNode = search(value);
        Node parentNodeOfAwaitRemoveNode = searchParent(value);

        // 删除单个节点
        if (awaitRemoveNode.left == null && awaitRemoveNode.right == null) {
            if (parentNodeOfAwaitRemoveNode.left == awaitRemoveNode) {
                parentNodeOfAwaitRemoveNode.left = null;
            } else {
                parentNodeOfAwaitRemoveNode.right = null;
            }
            return;
        } else if (awaitRemoveNode.left != null && awaitRemoveNode.right != null) {
            //删除有两个子树的节点
            Node pivotNode = getPivotValue();
            Node left = awaitRemoveNode.left;
            Node right = awaitRemoveNode.right;
            if (left == pivotNode || right == pivotNode) {
                if (left == pivotNode) {
                    left = null;
                }
                if (right == pivotNode) {
                    right = null;
                }
            }

            // 连接新的父节点
            if (parentNodeOfAwaitRemoveNode.left == awaitRemoveNode) {
                parentNodeOfAwaitRemoveNode.left = pivotNode;
            } else {
                parentNodeOfAwaitRemoveNode.right = pivotNode;
            }
            // 新父节点获得原来的指向
            pivotNode.left = left;
            pivotNode.right = right;
            awaitRemoveNode.left = null;
            awaitRemoveNode.right = null;

        } else {
            // 删除有一个子树的节点
            if (parentNodeOfAwaitRemoveNode.left == awaitRemoveNode) {
                //被删除子树在父节点左边

                //保存删除节点的左子树或者右子树
                if (awaitRemoveNode.left != null) {
                    parentNodeOfAwaitRemoveNode.left = awaitRemoveNode.left;
                } else {
                    parentNodeOfAwaitRemoveNode.left = awaitRemoveNode.right;
                }
            } else {
                //被删除子树在父节点右边
                if (awaitRemoveNode.left != null) {
                    parentNodeOfAwaitRemoveNode.right = awaitRemoveNode.left;
                } else {
                    parentNodeOfAwaitRemoveNode.right = awaitRemoveNode.right;
                }
            }

        }

    }

    public Node getPivotValue() {
        Node tmp;
        if (this.left != null) {
            tmp = this.left;
            while (tmp.right != null) {
                tmp = tmp.right;
            }
        } else {
            tmp = this.right;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
        }
        delete(tmp.value);
        return tmp;
    }

    // 改
    public void update(int oldValue, int value) {
        if (oldValue < this.value) {
            if (this.left != null) {
                this.left.update(oldValue, value);
            }
        } else if (oldValue > this.value) {
            if (this.right != null) {
                this.right.update(oldValue, value);
            }
        } else {
            // 该节点移除，然后重新添加到树中

            // 修改该节点的值
            this.value = value;
            // 重新添加到树中
        }
    }

    // 查
    public Node search(int value) {
        if (value < this.value) {
            if (this.left != null) {
                return this.left.search(value);
            }
        } else if (value > this.value) {
            if (this.right != null) {
                return this.right.search(value);
            }
        } else {
            // 该节点移除，然后重新添加到树中
            return this;
        }
        return null;
    }

    // 找到查找值的父节点
    public Node searchParent(int value) {
        if (BinaryTree.getRoot().value == value) { //第一次调用就相同， 说明是根节点
            return this;
        }
        if (this.left != null && this.left.value == value) {
            return this;
        }
        if (this.right != null && this.right.value == value){
            return  this;
        }

            if (value < this.value) {
                if (this.left != null) {
                    return this.left.searchParent(value);
                }
            } else {
                if (this.right != null) {
                    return this.right.searchParent(value);
                }
            }

        return null;
    }

    // 列出所有的二叉树
    public void list() {
        if (this.left != null) {
            this.left.list();
        }
        System.out.println(this);

        if (this.right != null) {
            this.right.list();
        }
    }
}
