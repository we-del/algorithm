package com.utils;

import org.junit.Test;

import java.util.Arrays;

/**
 * ClassName: TT
 * Description:
 * date: 2022/3/7 8:45
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
interface IT{
    void test();
}
public class TT {
    public static void main(String[] args) {
        //函数先进行传参调用,当被调用函数执行完后，就将其返回值返回到 改行(22行)
        int a = max(6, 5);
        System.out.println(a); // 打印a
        // 也可以直接函数调用+打印
        System.out.println(max(1,2));
    }
    private static int max(int n1,int n2){
        return Math.max(n1,n2);
    }

}
