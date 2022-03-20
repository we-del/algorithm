package com.data_structure.hashtable;

import com.utils.MyUtil;

import java.util.Scanner;

/**
 * ClassName: HashTable
 * Description: 对某个目标操作的方法要定义在该目标的类中，外部只用调用方法即可
 * date: 2022/2/20 9:59
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class HashTable {
    private int size;
    private EmpLinkedList[] arr;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashTable hashTable = new HashTable(8);
        int count = 0;
        String key = null;
        boolean loop = true;
        while (loop) {

            System.out.println("================================");
            System.out.println("add 添加员工");
            System.out.println("find 查找员工");
            System.out.println("list 列出员工");
            System.out.println("remove 删除员工");
            System.out.println("quit 退出");
            key = scanner.next();
            switch (key) {
                case "add":
                    String name = MyUtil.getRandomName();
                    hashTable.add(new Emp(count++,name));
                    System.out.println(name + "添加成功");
                    break;
                case "find":
                    System.out.println("请输入查找的编码(数字)");
                    int n = Integer.parseInt(scanner.next());

                    Emp emp = hashTable.find(n);
                    if (emp != null) {
                        System.out.println("成员：" + emp.name + " 在链表中");
                    } else {
                        System.out.println("输入的成员不存在");
                    }
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "remove":
                    System.out.println("请输入删除的编码(数字)");
                    int no = Integer.parseInt(scanner.next());
                    Emp emp1 = hashTable.remove(no);
                    if (emp1 != null) {
                        System.out.println("成员：" + emp1.name + " 已经被删除");
                    } else {
                        System.out.println("输入的成员不存在");
                    }
                    break;
                case "quit":
                    System.out.println("正在退出...");
                    try {
                        Thread.sleep(((int) (Math.random() * 300)) + 400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    loop = false;
                    System.out.println("退出完成");

                    break;
            }
        }
    }

    public HashTable(int size) {
        this.size = size;
        arr = new EmpLinkedList[size];

        // 给数组里每一个索引添加头指针
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new EmpLinkedList();
        }
    }

    // 给对应的索引的列添加信息
    public void add(Emp emp) {
        int index = hash(emp.id);
        arr[index].add(emp);
    }

    // 列出哈希表
    public void list() {
        for (int i = 0; i < size; i++) {
            arr[i].list(i + 1);
        }
    }

    // 查找数据
    public Emp find(int id) {
        // 根据传入的id金国hash得到对应的链表
        int index = hash(id);

        // 在对应的链表里调用该方法进行查找
        return arr[index].find(id);
    }

    // 删除员工
    public Emp remove(int id) {
        int index = hash(id);
        return arr[index].remove(id);
    }

    // hash策略
    public int hash(int id) {
        return id % size;
    }
}

class Emp {
    public int id ;
    public String name;
    public Emp next;


    public Emp(int id,String name) {
        this.id = id;
        this.name = name;
    }

    public Emp() {
    }

    @Override
    public String toString() {
        return "  id=" + id + "  name='" + name+"'";
    }
}

class EmpLinkedList {
    public Emp head = new Emp();

    public void add(Emp emp) {
        if (head.next == null) { // 添加到开头
            head.next = emp;
        } else {
            // 添加到末尾
            Emp cur = head.next;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = emp;
        }


    }

    // 列出当前链表的所有信息
    public void list(int no) {
        if (head.next == null) {
            System.out.println(no + "号链表为空");
            return;
        }

        System.out.print(no + "号链表信息为 ");
        Emp cur = head.next;
        while (true) {
            System.out.print("  => " + cur);
            if(cur.next ==null){
                break;
            }
            cur = cur.next;
        }
        System.out.println();

    }

    // 查找元素
    public Emp find(int id) {
        if (head.next == null) {
            System.out.println("当前链表为空");
            return null;
        }
        Emp cur = head.next;
        while (true) {
            if (cur.id == id) {
                return cur;
            }
            if (cur.next == null) {
                return null;
            }
            cur = cur.next;
        }
    }

    // 删除元素
    public Emp remove(int id) {
        if (head.next == null) {
            System.out.println("当前链表为空");
            return null;
        }
        Emp cur = head.next;
        Emp temp = head;
        while (true) {
            // 需要删除在链表首部
            if (cur == head.next && cur.id == id) {
                head.next = cur.next;
                cur.next = null;
                return cur;
            }

            // 需要删除元素在链表中部
            if (cur.next != null && cur.id == id) {
                temp.next = cur.next;
                cur.next = null;
                return cur;
            }

            if (cur.next == null && cur.id == id) {
                temp.next = null; // 将上一个链表断开
                return cur;
            }
            if (cur.next == null) {
                return null;
            }
            temp = temp.next;
            cur = cur.next;
        }
    }
}