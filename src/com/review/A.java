package com.review;

/**
 * ClassName: A
 * Description:
 * date: 2022/5/3 19:55
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class A {
    public static void main(String[] args) {
        B b = new B(3);

    }
}

class B extends C {
    public int bCount;

    public B(int bCount) {
        super(bCount + 5);
        this.bCount = bCount;
    }

    public void showBCount() {
        System.out.println(bCount);
    }
}

class C {
    public int cCount;

    public C(int cCount) {
        this.cCount = cCount;
    }

    public void showCCount() {
        System.out.println(cCount);
    }
}
