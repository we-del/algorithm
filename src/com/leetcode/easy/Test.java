package com.leetcode.easy;

/**
 * ClassName: Test
 * Description:
 * date: 2022/5/20 20:48
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class Test {
    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new B();
        B b = new B();
        C c = new C();
        D d = new D();

        System.out.println(a2.show(b));
        System.out.println(a2.show(c));
        System.out.println(a2.show(d));

    }
}

class A {
    public String show(D obj) {
        return "A and D";
    }

    public String show(A obj) {
        return "A and A";
    }
}

class B extends A {
    public String show(B obj) {
        return "B and B";
    }

    public String show(A obj) {
        return "B and A";
    }
}

class C extends B {

}

class D extends B {

}
