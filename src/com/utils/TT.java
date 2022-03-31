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
        //求出1 - 1/2 + 1/3 - 1/4 + 1/5 - 1/6 .... 1/100
        //思路分析:
        //求和 sum 初始值等于0
        //定义 自增变量sum1 从2 - 100
        //for  循环输出 2-100
        //定义 控符变量sum2 (sum1%2)为不为0,从而控制符号

        process(new IT(){
            @Override
            public void test() {
                System.out.println("我来实现匿名内部类");
            }
        });
    }
    public static void process(IT it) {
        it.test();

    }

}
