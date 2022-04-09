package com.data_structure.queue;

import java.util.Scanner;

/**
 * ClassName: CircleQueenArray
 * Description:
 * date: 2022/2/8 11:11
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class CircleQueueArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String key = null;
        boolean loop = true;
        CircleQueen queen = new CircleQueen(4);
        while (loop) {
            System.out.println("输入a(add)添加数据");
            System.out.println("输入g(getData)得到数据");
            System.out.println("输入s(show)得到当前指向");
            System.out.println("输入all(showData)得到当前指向");
            System.out.println("输入e(exit)退出");
            System.out.println("输入除了以上其他键重新选择");
            key = scanner.next();
            switch (key) {
                case "a":
                    // 添加随机数
                    queen.add((int) (Math.random() * 10));
                    break;
                case "g":
                    int data = queen.getData();
                    break;
                case "s":
                    queen.showHead();
                    break;
                case "e":
                    loop = false;
                    break;
                case "all":
                    queen.showData();
                default:
                    break;
            }
        }
    }

    static class CircleQueen {
        private int maxSize;
        /**
         * 约定 front rear 初始值为 0 且留有一个预留空间
         */
        private int front; // 指向队列头
        private int rear; // 指向队列尾
        private int arr[];

        // 初始化队列
        public CircleQueen(int size) {
            arr = new int[size];
            maxSize = size;
        }

        // 添加数据到队列
        public void add(int value) {

            if (isFull()) {
                System.out.println("队列空间不足");
                return;
            }
            arr[rear] = value; // 直接添加数据
            rear = (rear + 1) % maxSize;
        }

        // 判断数据是否存满
        public boolean isFull() {
            /**
             * 此算法会留有一个预留空间 如 4个空间  maxSize = 4 , 数组索引为 0，1，2，3
             * 第一次存储 0 1 2 [3 是预留空间]   (3+1)% 4 == 0 (设front = 0)
             * 第二次存储 1 2 3  [0 是预留空间]  (0+1)%4==1  （设front = 1）
             * 第三次存储 0 2 3 [1 是预留空间]   (1+1)%4==2  (设 front = 2)
             * 第四次存储 0 1 3  [2 是预留空间]   (2+1)%4==3 (设 front = 3)
             * */
            return (rear + 1) % maxSize == front;
        }

        // 判断数据是否为空
        public boolean isEmpty() {
            return front == rear;
        }

        // 从队列取出数据
        public Integer getData() {
            if (isEmpty()) {
                System.out.println("没有可取出的数据");
                return null;
            }
            int value = arr[front];
            front = (front + 1) % maxSize;
            return value;
        }

        // 显示当前头数据
        public void showHead() {
            if (isEmpty()) {
                System.out.println("没有可取出的数据");
                return;
            }
            System.out.println("当前在" + front + "位置，数据为：" + arr[front]);
        }

        // 显示全部数据
        public void showData() {
            for (int i = front; i < front + size(); i++) {
                System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i%maxSize]);
            }
        }

        // 求出当前队列有效数据个数
        public int size() {
            return (rear + maxSize - front) % maxSize;
        }
    }
}
