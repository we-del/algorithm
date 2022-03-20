package com.leetcode.middle;

import java.util.Scanner;

/**
 * ClassName: TwoNumberGetSum_title2
 * Description:
 * date: 2022/3/9 19:41
 *
 * @author: xx
 * @version:
 * @description: 将两个数由高位向低位形成链表，并进行相加，相加后的值倒叙存入链表
 * 如 123 变为链表为 1 -> 2 -> 3， 123+111 = 234  ，存入链表为 4 -> 3 -> 2
 * @since JDK 1.8
 */
public class TwoNumberGetSum_title2 {
    public static void main(String[] args) {

        // 完成初始化
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入两个数，形成链表");

        int num1 = Integer.parseInt(scanner.next());
        int num2 = Integer.parseInt(scanner.next());
        int max1 = (num1 + "").length();
        int max2 = (num2 + "").length();
        int n1 = 1;
        int n2 = 1;
        LinkedList linkedList1 = new LinkedList();
        LinkedList linkedList2 = new LinkedList();

        while (max1 > 1) {
            n1 *= 10;
            max1--;
        }
        while (max2 > 1) {
            n2 *= 10;
            max2--;
        }
        // 此时 n1和n2都拿到了当前num1和num2的高位值

        // 将第一个数的每个位数拆分单个数使用链表连接
        while (n1 > 0) {
            int n = num1 / n1 % 10;
            if (linkedList1.head == null) {
                linkedList1.head = new Node(n);
            } else {
                linkedList1.add(new Node(n));
            }
            n1 /= 10;
        }
        // 将第二个数的每个位数拆分单个数使用链表连接
        while (n2 > 0) {
            int n = num2 / n2 % 10;
            if (linkedList2.head == null) {
                linkedList2.head = new Node(n);
            } else {
                linkedList2.add(new Node(n));
            }
            n2 /= 10;
        }

        // 打印两条链表
        linkedList1.list();
        linkedList2.list();


        LinkedList linkedList3 = new LinkedList();
        // 两个数相加的链表
        int sum = num1 + num2;
        int max3 = (sum + "").length(); // 两数相加的最大位数
        for (int i = 0, j = 1; i < max3; i++, j *= 10) {
            // 3 1 10 100
            int num = sum / j % 10;
            if (linkedList3.head == null) {
                linkedList3.head = new Node(num);
            } else {
                linkedList3.add(new Node(num));
            }
        }

        // 打印出来相加的李娜表
        linkedList3.list();

    }

    /**
     * @description: 为了不污染本包里其他的类，故将此类定义为局部内部类，且用private修饰
     */
    private static class LinkedList {
        public Node head;

        public void add(Node node) {
            head.add(head, node);
        }

        public void list() {
            head.list();
        }

        public LinkedList() {
        }

    }

    private static class Node {
        public Node next;
        public int value;

        public Node(int value) {
            this.value = value;
        }

        public void list() {
            Node cur = this;
            while (cur != null) {
                System.out.print(cur.value + " -> ");
                cur = cur.next;
            }
            System.out.println();

        }

        public void add(Node head, Node node) {
            Node cur = head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = node;
        }
    }
}

