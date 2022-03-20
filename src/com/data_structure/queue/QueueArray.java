package com.data_structure.queue;

import java.util.Scanner;

/**
 * ClassName: QueenArray
 * Description:
 * date: 2022/2/8 9:47
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class QueueArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String key = null;
        boolean loop = true;
        Queen queen = new Queen(3);
        while(loop){
            System.out.println("输入a(add)添加数据");
            System.out.println("输入g(getData)得到数据");
            System.out.println("输入s(show)得到当前指向");
            System.out.println("输入e(exit)退出");
            System.out.println("输入除了以上其他键重新选择");
            key = scanner.next();
            switch (key){
                case "a":
                    // 添加随机数
                    queen.add((int)(Math.random()*10));
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
                default:
                    break;
            }
        }
    }

    static class Queen {
        private int pre; // 指向前一个节点
        private int next; // 指向后一个节点
        private int arr[];

        // 初始化队列
        public Queen(int size) {
            arr = new int[size];
            pre = -1;
            next = -1;
        }

        // 添加数据到队列
        public void add(int value) {

            if (pre == arr.length - 1) {
                throw new RuntimeException("队列空间不足");
            }
            pre++;
            arr[pre] = value;
        }
        
        // 从队列取出数据
        public int getData(){
            if(pre == next){
                throw new RuntimeException("没有可取出的数据");
            }
            next++;
            System.out.println("取出了"+next+"位置的数据，为："+arr[next]);
            return arr[next];
        }

        // 显示当前头数据
        public void showHead(){
            if(pre == next){
                throw new RuntimeException("没有可取出的数据");
            }
            System.out.println("当前在"+(next+1)+"位置，数据为："+arr[next+1]);
        }
    }
}
