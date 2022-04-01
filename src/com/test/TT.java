package com.test;

import org.junit.Test;

/**
 * ClassName: TT
 * Description:
 * date: 2022/3/18 20:26
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class TT {
    public static void main(String[] args){
        Cat cat = new Cat();
        for (int i = 0; i < 10; i++) {
            // 开启了10个线程
            cat.start();
        }
    }
    @Test
    public void e(){
        final Object[] arr = new Object[10];
        arr[0] = 1;
        arr[0] = 2;
        
    }
}
class Cat extends Thread{
    @Override
    public void run() {
        System.out.println("我来运行");
    }
}
