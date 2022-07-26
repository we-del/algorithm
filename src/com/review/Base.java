package com.review;

/**
 * ClassName: Base
 * Description:
 * date: 2022/5/4 10:19
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class Base {
    private static String baseName = "base";
    public static void main(String[] args) {
        Base  b = new Sub();
        System.out.println(b.baseName);
//        sub.show();
    }
    public Base() {

        callName();
    }
    public void show() {
    }
    public void callName() {
        System.out.println(baseName);
    }
    static class Sub extends Base {
        private static String baseName = "sub";
        public void callName() {
            System.out.println(baseName);
        }
    }
}
