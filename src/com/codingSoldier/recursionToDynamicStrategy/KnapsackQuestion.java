package com.codingSoldier.recursionToDynamicStrategy;

/**
 * ClassName: KnapsackQuestion
 * Description:
 * date: 2022/3/27 16:36
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class KnapsackQuestion {

    public static void main(String[] args){
    }

    /**
     * @param v 货物的总价值
     * @param w 货物的总重量
     * @param alreadyW 已装货物的总重量
     * @param index 已装货物的个数
     * @param bagSize 背包的容量
     *                
     * @description: 当使用回溯法求解时，应把多把递归的解进行收集到一个变量种，然后返回 变量
     * */
    public static  int process(int[] w,int[] v,int index,int alreadyW,int bagSize){
        if(alreadyW > bagSize){ // 货物已经装不下
            return -1;
        }
        if(index == w.length){ // 货物装的下
            return 0;
        }
        int p1 = process(w,v,index+1,alreadyW,bagSize); // 保存背包初始容量
        int p2next = process(w,v,index+1,alreadyW+w[index],bagSize); // 保存 最新的背包容量
        int p2 = -1;
        if(p2next!=-1){  // 当前决策不为-1
            p2=v[index]+p2next; // 则获得决策的值
        }
        return Math.max(p1,p2); // 返回最大背包容量
    }
}
