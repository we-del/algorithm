package com.codingSoldier.recursionToDynamicStrategy;

/**
 * ClassName: AssociateNumber
 * Description:
 * date: 2022/3/27 15:33
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class AssociateNumber {
    public static void main(String[] args) {
        System.out.println(process("8121".toCharArray(), 0));
    }

    /**
     * 当 输入一串0-9的字符串后，判断其能有多少种字母的排列组合
     * 如   111    有 aaa  ak ka  3 种
     *
     * @description: 此方法用来获取该问题的解
     * @description:  关于递归的返回值的补充
     *              递归可以返回任何类型，但不能直接把返回值整个返回会去,这样只能得到一个解，如果想得到多个解可以
     *              定义一个变量接收，算完所有解后再把该变量返回即可
     */
    public static int process(char[] c, int i) {
        if (i == c.length) { // 当一次方法全部组合完毕后，得到一个该问题的解
            return 1;
        }
        if (c[i] == '0') { // 当遇到0则得不到一个问题的解，因为被其阻拦
            return 0;
        }
        if (c[i] == '1') {
            int result = 0; // 处理 1 ~ 19
            result += process(c, i + 1);
            if(i+1<c.length){
                result += process(c, i + 2);
            }
            return result;
        }

        if(c[i] == '2'){ // 处理 2~ 2n(n范围在 0~6)
            int res = 0;
            res += process(c, i + 1);
            if(i+1<c.length &&  c[i+1]>='0' && c[i+1]<='6'){
                res+=process(c,i+2);
            }
            return res;
        }
        return process(c,i+1); // 3~9 的情况
    }
}
