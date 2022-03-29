package com.codingSoldier.recursionToDynamicStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: PrintArrayAllSort
 * Description:
 * date: 2022/3/27 10:12
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class PrintArrayAllSort {
    public static void main(String[] args) {
        List<String> abc = strSort("abc");
    }

    public static List<String> strSort(String str) {
        List<String> list = new ArrayList<>();
        process2(list, str.toCharArray(), 0);
        return list;
    }

    public static void process(List<String> list, char[] c, int index) {
        // 当index == c.length 的时候 说明此排列已经完成了
        if (index == c.length) {
            System.out.println(c);
            list.add(c.toString());
            return;
        }

        for (int i = index; i < c.length; i++) {
            swap(c, i, index);
            process(list, c, index + 1);
            swap(c, i, index);
        }
    }

    // 分支限界完成搜索
    public static void process2(List<String> list, char[] c, int index) {
        if (index == c.length) {
            list.add(String.valueOf(c));
            System.out.println(c);
            return;
        }
        boolean[] visit = new boolean[26];
        for (int i = index; i < c.length; i++) {
            if (!visit[c[i] - 'a']) {
                visit[c[i] - 'a'] = true;
                swap(c, i, index);
                process2(list, c, index);
                swap(c, i, index);
            }
        }
    }

    public static void swap(char[] c, int i, int j) {
        char tmp = c[i];
        c[i] = c[j];
        c[j] = tmp;
    }
}
