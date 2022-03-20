package com.data_structure.stack;

import org.junit.Test;

/**
 * ClassName: Calculator
 * Description: 此为小试牛刀版，可能有bug 但不影响
 * date: 2022/2/10 15:03
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class Calculator {

    public static void main(String[] args) {
        String strNums = "20+3*4+4";   // 20 - 12 + 4
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int ope = 0;
        int result = 0;
        char ch = ' ';
        StackLinkedList1 numberStack = new StackLinkedList1();
        StackLinkedList1 opeStack = new StackLinkedList1();

        // 将符号入栈
        while (true) {
            if (index < strNums.length()) { // 防止越界


                ch = strNums.substring(index, index + 1).charAt(0);
                if (opeStack.isOpe(ch)) {
                    index++;
                    //  载入符号
                    if (!(opeStack.isEmpty())) {
                        // 符号栈中已有符号
                        //  载入符号
                        if (opeStack.priority(ch) < opeStack.priority(opeStack.peek())) {
                            // 新增符号的优先级比上一个符号优先级低，则上一个符号先运算

                            // 取出栈底的符号和值并进行运算
                            Integer operate = opeStack.pop();
                            Integer n1 = numberStack.pop();
                            Integer n2 = numberStack.pop();
                            Integer res = numberStack.calculate(n1, n2, operate);

                            numberStack.push(new NumOrChar(res)); // 载入结果

                        }
                    }
                    opeStack.push(new NumOrChar(ch)); //  载入符号
                } else {
                    String str = ch + "";

                    // 连续检索连串数字
                    while (true) {
                        index++;

                        if (index < strNums.length()) { // 防止越界
                            char c = strNums.substring(index, index + 1).charAt(0);
                            if (!(numberStack.isOpe(c))) {
                                str += c;
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    // 是数字
                    numberStack.push(new NumOrChar(Integer.parseInt(str)));

                }
            } else {
                break;
            }
        }

        // 此时符号栈中所有符号都是同一优先级
        while (true) {
            if (!(opeStack.isEmpty())) {
                Integer operate = opeStack.pop();
                Integer n1 = numberStack.pop();
                Integer n2 = numberStack.pop();
                Integer res = numberStack.calculate(n1, n2, operate);

                numberStack.push(new NumOrChar(res)); // 载入结果
            } else {
                System.out.println(strNums + "的结果为:" + numberStack.pop());
                break;
            }
        }
    }
}

class StackLinkedList1 {
    private NumOrChar head = new NumOrChar();
    private NumOrChar last = new NumOrChar();

    // 初始化链表
    public void init(NumOrChar h) {
        head.setNext(h);
        last.setNext(h);
    }

    // 向栈压入数据
    public void push(NumOrChar h) {


        if (head.getNext() == null && last.getNext() == null) {
            // 如果链表没有数据则新增数据压入栈底
            head.setNext(h);
            last.setNext(h);
        } else {
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
        if (head.getNext() != null && head.getNext().getNext() != null) {
            // 栈里有二个或两个以上数据
            value = last.getNext().getValue();
            NumOrChar next = last.getNext();
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

    public NumOrChar getHead() {
        return head;
    }

    public void setHead(NumOrChar head) {
        this.head = head;
    }

    public NumOrChar getLast() {
        return last;
    }

    public void setLast(NumOrChar last) {
        this.last = last;
    }

    // 遍历栈的全部数据
    public void list() {
        if (isEmpty()) {
            System.out.println("当前栈没有数据");
            return;
        }
        // first 在首部  last 在尾部  ，获得last节点，开始向first打印，即可完成栈结构
        NumOrChar tmp = last.getNext();
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

    public char peek() {
        return (char) last.getNext().getValue();
    }

    public boolean isOpe(char ope) {
        switch (ope) {
            case '*':
            case '/':
            case '+':
            case '-':
                return true;
        }
        return false;
    }

    public Integer priority(char ope) {
        switch (ope) {
            case '*':
            case '/':
                return 1;
            case '+':
            case '-':
                return 0;
        }
        return null;
    }

    public Integer calculate(int num1, int num2, int ope) {
        switch (ope) {
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
        }
        return null;
    }
}

class NumOrChar {
    private int value;
    private NumOrChar pre;
    private NumOrChar next;

    public NumOrChar(int value) {
        this.value = value;
    }

    public NumOrChar() {
    }

    @Override
    public String toString() {
        return "NumOrChar{" +
                "value=" + value +
                '}';
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public NumOrChar getPre() {
        return pre;
    }

    public void setPre(NumOrChar pre) {
        this.pre = pre;
    }

    public NumOrChar getNext() {
        return next;
    }

    public void setNext(NumOrChar next) {
        this.next = next;
    }
}

