package com.utils;

import com.algorithm.sort.RandomArray;

import java.util.Arrays;

/**
 * ClassName: Te
 * Description:
 * date: 2022/2/26 9:59
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class Te {
    public static void main(String[] args) {
        new Queen8().start(0);
    }

}

class Queen8 {
    private int[] result;

    public Queen8() {
        result = new int[8];
    }

    public void start(int n) {
        if (n == 8) {
            log();
            return;
        }
        for (int i = 0; i < 8; i++) {
            result[n] = i;
            if (verify(n)) {
                start(n + 1);
            }
        }

    }

    private boolean verify(int n) {
        for (int i = 0; i < n; i++) {
            // 相同行
            if (result[i] == result[n] || (Math.abs(result[i] - result[n]) == Math.abs(i - n))) {
                return false;
            }
        }
        return true;
    }

    private void log() {
        for (int i : result) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
