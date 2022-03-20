package com.algorithm.sort;

/**
 * ClassName: AnalyseOfTimeFrequence
 * Description:
 * date: 2022/2/13 11:20
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class AnalyseOfTimeFrequency {
    public static void main(String[] args){
        //1 . 时间频率为 T(n) = n+1   (n为for循环的次数 ，+1 为结束判断的运行次数)
        int total = 0;
        int end = 100;
        for (int i = 0; i < end; i++) {
            total+=1;
        }

        // 2. 时间频率为 T(n) = 1 (下一句为 直接计算,只用执行一次，所有频率为1)
        total =(1+end)*end/2;

    }
}
