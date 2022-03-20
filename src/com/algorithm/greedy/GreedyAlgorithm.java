package com.algorithm.greedy;

import java.util.HashMap;
import java.util.HashSet;

/**
 * ClassName: GreedyAlgorithm
 * Description:
 * date: 2022/3/8 9:31
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class GreedyAlgorithm {
    public static void main(String[] args){
        // 创建广播电台列表
        HashMap<String, HashSet<String>> broadcast = new HashMap<>();

        // 创建广播电台
        HashSet<String> k1 = new HashSet<>();
        HashSet<String> k2 = new HashSet<>();
        HashSet<String> k3 = new HashSet<>();
        HashSet<String> k4 = new HashSet<>();
        HashSet<String> k5 = new HashSet<>();

        k1.add("北京");
        k1.add("上海");
        k1.add("天津");

        k2.add("广州");
        k2.add("北京");
        k2.add("深圳");

        k3.add("成都");
        k3.add("上海");
        k3.add("杭州");

        k4.add("上海");
        k4.add("天津");

        k5.add("杭州");
        k5.add("大连");

        broadcast.put("k1",k1);
        broadcast.put("k2",k2);
        broadcast.put("k3",k3);
        broadcast.put("k4",k4);
        broadcast.put("k5",k5);

        HashSet<String> requireCoverCities = new HashSet<>();
        requireCoverCities.add("上海");
        requireCoverCities.add("北京");
        requireCoverCities.add("天津");
        requireCoverCities.add("深圳");
        requireCoverCities.add("成都");
        requireCoverCities.add("杭州");
        requireCoverCities.add("大连");

        while(requireCoverCities.size() != 0){

        }
    }

}
