package com.data_structure.stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * ClassName: StackDemo
 * Description:
 * date: 2022/2/10 11:19
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class StackDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String key = "";
        boolean loop = true;
        StackLinkedList stackLinkedList = new StackLinkedList();
        stackLinkedList.init(new Date((int) (Math.random() * 9)));
        while (true) {
            System.out.println("=========================");
            System.out.println("1. 添加数据到栈");
            System.out.println("2. 取出栈顶的一个数据");
            System.out.println("3. 列出栈里的数据");
            System.out.println("9. 退出");
            key = scanner.next();
            switch (key) {
                case "1":
                    stackLinkedList.push(new Date((int) (Math.random() * 9)));
                    break;
                case "2":
                    stackLinkedList.pop();
                    break;
                case "3":
                    stackLinkedList.list();
                    break;
                case "9":
                    loop = false;
                    break;
            }
        }
    }

}

class StackArray {
    private int maxSize;
    private int top = -1;
    private int[] arr;

    public StackArray(int maxSize) {
        if (maxSize <= 0) {
            throw new RuntimeException("请输入正确的初始值(>0)");
        }
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    // 往自制栈里添加数据
    public void push(int value) {
        if (isFull()) {
            System.out.println("数组已经满了");
            return;
        }
        arr[++top] = value;
    }

    public boolean isFull() {
        return top + 1 == maxSize;
    }

    // 取出自制栈顶层的一个数据
    public Integer pop() {
        if (isEmpty()) {
            System.out.println("数组是空的");
            return null;
        }
        return arr[top--];
    }

    public boolean isEmpty() {
        return arr.length == 0 || top == -1;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("数组是空的");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(arr[i]);
        }
    }
}

class StackLinkedList {
    private Date head = new Date();
    private Date last = new Date();

    // 初始化链表
    public void init(Date h) {
        head.setNext(h);
        last.setNext(h);
    }

    // 向栈压入数据
    public void push(Date h) {


        if(head.getNext()==null && last.getNext() == null){
            // 如果链表没有数据则新增数据压入栈底
            head.setNext(h);
            last.setNext(h);
        }else{
            // 新增数据压入栈顶
            last.getNext().setNext(h); // 使当前指向的链表指向最新的链表
            h.setPre(last.getNext()); // 使最新链表的pre指向上一个链表
            last.setNext(last.getNext().getNext()); // 移动到最新的链表上
        }
    }

    // 取出栈顶的数据
    public Integer pop() {
        if (isEmpty()) {
            System.out.println("当前栈没有数据");
            return null;
        }
        int value = 0;
        if (head.getNext() !=null &&head.getNext().getNext()!=null) {
            // 栈里有二个或两个以上数据
            value = last.getNext().getValue();
            Date next = last.getNext();
            last.setNext(last.getNext().getPre());
            next.setPre(null); // 断掉和前一个链表的指向 pre = null
            last.getNext().setNext(null); // 断掉和最后一个链表的指向 next = null
        } else {
            // 栈里只有一个数据
            value = last.getNext().getValue();
            last.setNext(null);
            head.setNext(null);
        }

        return value;
    }

    // 遍历栈的全部数据
    public void list() {
        if (isEmpty()) {
            System.out.println("当前栈没有数据");
            return;
        }
        // first 在首部  last 在尾部  ，获得last节点，开始向first打印，即可完成栈结构
        Date tmp = last.getNext();
        while (true) {
            System.out.println(tmp);
            if (tmp.getPre() == null) {
                return;
            }
            tmp = tmp.getPre();

        }

    }

    public boolean isEmpty() {
        return head.getNext() == null || last.getNext() == null;
    }
}

class Date {
    private Date pre;
    private Date next;
    private int value;

    public Date() {
    }

    @Override
    public String toString() {
        return "Date{" +
                "value=" + value +
                '}';
    }

    public Date(int value) {
        this.value = value;
    }

    public Date getPre() {
        return pre;
    }

    public void setPre(Date pre) {
        this.pre = pre;
    }

    public Date getNext() {
        return next;
    }

    public void setNext(Date next) {
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
