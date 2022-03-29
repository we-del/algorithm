package com.leetcode.middle;

/**
 * ClassName: DynamicStrategy_55
 * Description:
 * 55. 跳跃游戏
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个下标。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 * <p>
 * date: 2022/3/24 10:54
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class DynamicStrategy_55 {
    boolean flag = false;

    public static void main(String[] args) {
        DynamicStrategy_55 d = new DynamicStrategy_55();
        System.out.println(d.canJump(new int[]{3,0,8,2,0,0,1}));
    }


    public boolean canJump(int[] nums) {
         process(nums,0,0);
         System.out.println(flag);
         return flag;
    }


    public void process(int[] nums, int step, int n) {
        if (step >= nums.length - 1) {
            flag = true;
        }
        for (int i = step;step < nums.length && i < nums[step]+step && i < nums.length; i++) {
            if (nums[i] == 0) return;
            process(nums, nums[i] + i, i + 1);
        }
    }



    // leetcode解
//    public boolean canJump(int[] nums) {
//        int n=1;
//        for(int i=nums.length-2;i>=0;i--){
//            if(nums[i]>=n)
//            {
//                n=1;
//            }
//            else
//            {
//                n++;
//            }
//            if(i==0&&n>1)
//            {
//                return false;
//            }
//        }
//        return true;
//
//    }
}
