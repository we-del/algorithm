package com.codingSoldier.linkedList;

/**
 * ClassName: FastAndSlowPointer
 * Description:
 * date: 2022/3/21 10:15
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class FastAndSlowPointer {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();

        for (int i = 0; i < 20; i++) {
            linkedList.add((int)(Math.random()*30)+1);
        }
        int size = linkedList.size();
        linkedList.list();
        System.out.println(size);

        // 得到上中点
        System.out.println("linkedList.getPivotNode(true) = " + linkedList.getPivotNode(true));
        System.out.println("linkedList.getPivotNode(false) = " + linkedList.getPivotNode(false));
        System.out.println("linkedList.getPivotPreNode(true) = " + linkedList.getPivotPreNode(true));
        System.out.println("linkedList.getPivotPreNode(false) = " + linkedList.getPivotPreNode(false));
    }

    private static class LinkedList {
        public Node head = new Node();

        public void add(int value) {
            Node node = new Node(value);
            if (head.next == null) {
                head.next = node;
                return;
            }
            head.add(node);

        }

        public int size() {
            return head.size();
        }

        public void list() {
            head.list();
        }

        // flag为true 且长度为偶数返回上中点，否则返回下中点
        public Node getPivotNode(boolean flag) {
            // 1 2 3 4 5
            int size = size();
            if (size % 2 == 1) {
                return head.getAppointNode(size / 2 + 1);
            }
            if (flag) {
                return head.getAppointNode(size / 2);
            } else {
                return head.getAppointNode(size / 2 + 1);
            }
        }


        // flag为true 且长度为偶数返回上中点前一个，否则返回下中点前一个
        public Node getPivotPreNode(boolean flag) {
            int size = size();
            if (size % 2 == 1) {
                return head.getAppointNode(size / 2);
            }
            if (flag) {
                return head.getAppointNode(size / 2 - 1);
            } else {
                return head.getAppointNode(size / 2 + 2);
            }
        }
    }

    private static class Node {
        public Node next;
        public int value;

        public Node(int value) {
            this.value = value;
        }

        public Node() {
        }

        public void add(Node node) {
            Node tmp = null;

            if (node.value < this.next.value) {
                tmp = this.next;
                this.next = node;
                node.next = tmp;
                return;
            }
            Node cur = this.next;
            tmp = this;
            while (true) {

                if (node.value < cur.value) {
                    tmp.next = node;
                    node.next = cur;
                    return;
                }
                if (cur.next == null) {
                    cur.next = node;
                    return;
                }
                tmp = tmp.next;
                cur = cur.next;
            }
        }

        public int size() {
            int len = 0;
            Node cur = this;
            // 有3个节点  1 1 | 2 2 | 3 3|
            while (cur.next != null) {
                len++;
                cur = cur.next;
            }
            return len;
        }

        public void list() {
            Node cur = this;
            while (cur.next != null) {
                cur = cur.next;
                System.out.print(cur.value + " -> ");
            }
            System.out.println();

        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }

        public Node getAppointNode(int index) {
            int tmp = 0;
            Node cur = this;
            while (tmp != index) {
                cur = cur.next;
                tmp++;
            }
            return cur;
        }

    }
}
