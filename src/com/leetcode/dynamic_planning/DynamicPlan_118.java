package com.leetcode.dynamic_planning;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: DynamicPlan_118
 * Description:
 * date: 2022/4/1 11:51
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class DynamicPlan_118 {
    public static void main(String[] args) {
        System.out.println(new DynamicPlan_118().generate(5));
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> arrayList = new ArrayList<>();
            list.add(arrayList);
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    arrayList.add(j, 1);
                } else {
                    arrayList.add(j, list.get(i - 1).get(j - 1) + list.get(i - 1).get(j));
                }
            }
        }
        return list;
    }
}
