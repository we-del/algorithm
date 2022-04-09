package com.leetcode.easy;

import java.util.Arrays;

/**
 * ClassName: Leetoce_21
 * Description:
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 * <p>
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 * <p>
 * date: 2022/4/7 9:40
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class Leetoce_21 {
    public static void main(String[] args) {
        LinkedList linkedList1 = new LinkedList();
        linkedList1.add(3);
        linkedList1.add(1);
        linkedList1.add(4);
        LinkedList linkedList2 = new LinkedList();
        linkedList2.add(5);
        linkedList2.add(1);
        linkedList2.add(2);
        //linkedList1.list();
        LinkedList mergeList = linkedList1.merge(linkedList1, linkedList2);
        mergeList.list();
        //System.out.println(linkedList1.size());
    }

    // 简单的链表
    private static class LinkedList {
        Node head = new Node();

        public boolean add(int value) {
            return head.add(new Node(value));
        }

        public void list() {
            head.list();
        }

        public void reverse() {
            head.reverse();
        }

        public LinkedList merge(LinkedList linkedList1, LinkedList linkedList2) {
            return head.merge(linkedList1, linkedList2);
        }

        public int size() {
            return head.size();
        }
    }

    private static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }

        public Node() {
        }

        public boolean add(Node node) {
            if (node == null) return false;

            if (this.next == null) {
                this.next = node;
                return true;
            }
            Node tmp = this;
            Node cur = this.next;
            while (true) {

                if (this.next.value > node.value) {
                    this.next = node;
                    node.next = cur;
                    return true;
                }
                if (cur.value > node.value) {
                    tmp.next = node;
                    node.next = cur;
                    return true;
                }
                if (cur.next == null) {
                    cur.next = node;
                    return true;
                }
                tmp = tmp.next;
                cur = cur.next;
            }

        }

        public void list() {
            Node cur = this;
            while (cur.next != null) {
                cur = cur.next;
                System.out.print(cur.value + " -> ");
            }
            System.out.println();
        }

        public void reverse() {
            Node tmpHead = null;
            Node cur = this.next;
            Node next = null;
            while (true) {
                next = cur.next;
                cur.next = tmpHead;
                tmpHead = cur;
                cur = next;
                if (cur == null) break;
            }
            this.next = tmpHead;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }

        public LinkedList merge(LinkedList linkedList1, LinkedList linkedList2) {
            Node cur = linkedList2.head.next;
            Node[] nodeArr = new Node[linkedList2.size()];
            int index = 0;
            while (cur != null) {
                nodeArr[index++] = cur;
                cur = cur.next;
            }
            Arrays.toString(nodeArr);
            for (Node node : nodeArr) {
                linkedList1.head.add(node);
            }
            return linkedList1;
        }

        public int size() {
            Node cur = this;
            int index = 0;
            while (cur.next != null) {
                index++;
                cur = cur.next;
            }
            return index;
        }
    }
}
