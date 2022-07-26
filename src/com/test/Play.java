package com.test;

/**
 * ClassName: Play
 * Description:
 * date: 2022/7/14 15:40
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class Play {
    public static void main(String[] args) {
        throw new MoneyNotEnoughOfCrazyThursdayException("Need ￥50，please v me");
    }

    static class MoneyNotEnoughOfCrazyThursdayException extends RuntimeException {
        public MoneyNotEnoughOfCrazyThursdayException(String message) {
            super(message);
        }
    }
}
