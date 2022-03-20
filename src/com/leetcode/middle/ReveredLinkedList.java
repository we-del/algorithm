package com.leetcode.middle;

/**
 * ClassName: ReveredLinkedList
 * Description:
 * date: 2022/3/15 12:10
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class ReveredLinkedList {
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        int[] arr = {3, 1, 5, 7, 6};
        LinkedList linkedList = new LinkedList();
        linkedList.add(arr);
        linkedList.list();
        reversedLinkedLIst(linkedList);
        System.out.println();
        linkedList.list();
    }

    public static void reversedLinkedLIst(LinkedList linkedList) {
        Node newHead = null;
        Node cur = linkedList.getHead().next;
        Node next = null;
        while(true){

            next = cur.next;
            cur.next = newHead;
            newHead = cur;
            if(next == null){
                linkedList.setHead(newHead);
                return;
            }
            cur = next;
        }
    }

    static class LinkedList {
        private Node head = new Node();

        public Node getHead() {
            return head;
        }

        public void setHead(Node head) {
            this.head = head;
        }

        public void add(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                this.head.add(new Node(arr[i]));
            }
        }

        public void list() {
            head.list();
        }
    }

    static class Node {
        Node next;
        int value;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }

        public void add(Node node) {
            // 写一个简单的链表拼接，并非按照顺序拼接
            if (this.next == null) {
                this.next = node;
                return;
            }
            Node cur = this.next;
            while (true) {
                if (cur.next == null) {
                    cur.next = node;
                    return;
                }
                cur = cur.next;
            }

        }

        public void list() {
            Node cur = this.value == 0 ? this.next : this;
            while (cur != null ) {
                System.out.print(cur.value + " -> ");
                cur = cur.next;
            }
        }
    }
}
