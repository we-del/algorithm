package com.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: MergeLinedList
 * Description:
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 * <p>
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：lists = [[]]
 * 输出：[]
 * <p>
 * <p>
 * date: 2022/3/13 9:07
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class MergeLinedList {
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        int[] arr1 = {3, 1, 2};
        LinkedList linkedList1 = new LinkedList(arr1[0]);
        linkedList1.add(arr1);
        linkedList1.list();

        int[] arr2 = {4, 6, 3};
        LinkedList linkedList2 = new LinkedList(arr2[0]);
        linkedList2.add(arr2);
        linkedList2.list();

        int[] arr3 = {5, 1, 2};
        LinkedList linkedList3 = new LinkedList(arr3[0]);
        linkedList3.add(arr3);
        linkedList3.list();

        LinkedList merge = merge(linkedList1, linkedList2, linkedList3);
        merge.list();
    }

    private static LinkedList merge(LinkedList... args) {
        if (args.length < 2) {
            System.out.println("传入的链表个数不足");
            return null;
        }
        List<LinkedList> list = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            list.add(args[i]);
        }
        return handler(list);
    }

    private static LinkedList handler(List<LinkedList> list) {
        while (list.size() > 1) {
            LinkedList linkedList1 = list.get(0);
            LinkedList linkedList2 = list.get(1);
            LinkedList newLinkedList = new LinkedList();

            Node first = linkedList1.head.next;
            Node two = linkedList2.head.next;


            // 进行处理，此时已经完成了有序
            while (first != null && two != null) {
                if (first.value < two.value) {
                    newLinkedList.add(first.value);
                    first = first.next;
                } else {
                    newLinkedList.add(two.value);
                    two = two.next;
                }
            }
            while (first != null) {
                newLinkedList.add(first.value);
                first = first.next;
            }
            while (two != null) {
                newLinkedList.add(two.value);
                two = two.next;
            }


            // 移除已经合并的链表，添加合并后的链表
            list.remove(linkedList1);
            list.remove(linkedList2);
            list.add(newLinkedList);
        }
        // 最终会返回一个完整的链表
        return list.get(0);
    }

    private static class LinkedList {
        public Node head = new Node();

        public LinkedList(int n) {
            this.head.next = new Node(n);
        }

        public LinkedList() {
        }

        public void add(int[] arr) {
            for (int i = 1; i < arr.length; i++) {
                head.add(new Node(arr[i]));
            }
        }

        public void add(int value) {
            head.add(new Node(value));
        }

        public void list() {
            this.head.list();
        }
    }

    private static class Node {
        public Node next;
        public int value;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }

        public void add(Node node) {
            if (this.next == null) {
                this.next = node;
                return;
            }

            Node pre = this;
            Node cur = this.next;
            while (true) {
                Node tmp = null;
                if (cur.value > node.value && this.next == cur) {
                    tmp = cur;
                    this.next = node;
                    node.next = tmp;
                    return;
                }

                // 台南佳在链表中部
                if (cur.value > node.value) {
                    pre.next = node;
                    node.next = cur;
                    return;
                }

                // 添加再链表尾部
                if (cur.next == null) {
                    cur.next = node;
                    return;
                }

                pre = pre.next;
                cur = cur.next;
            }


        }

        public void list() {
            Node cur = this.next;
            while (cur != null) {
                System.out.print(cur.value + " -> ");
                cur = cur.next;
            }
            System.out.println();
        }
    }
}
