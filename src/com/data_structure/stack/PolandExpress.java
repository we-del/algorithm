package com.data_structure.stack;

import org.junit.Test;

import java.util.*;

/**
 * ClassName: PolandExpress
 * Description:
 * date: 2022/2/11 18:33
 *
 * @author: xx
 * @version:
 * @since JDK 1.8
 */
public class PolandExpress {
    public static void main(String[] args) {
        // 波兰表达式为 前缀表达式 ， 我们经常使用逆波兰表达式，即后缀表达式
        // (3+4)*5-6 的 逆波兰表达式为  3 4 + 5 * 6 -
        // String str = "3 4 + 5 * 6 -";
        // String str = midExprToRearExpr("(3+4)*5-6");
        //String str = midExprToRearExpr("3*4+3+(5-6)");  // 3 4 * 3 + 5 6 - +
        //String str = invertToRearExpr("3*4+3+(((52-6))+24+3)");  // 3 4 * 3 + 52 6 - + 24 + 3 +
        //String str = invertToRearExpr("3*4+3+((52*3)-33+(((23))-6)+24+3)");  // 3 4 * 3 + 52 3 * 33 - 23 6 - + 24 + 3 + +
        //String str = invertToRearExpr("5+2*(3+4*5-3)+(20*3)");  // 5 2 3 4 5 * 3 - + * 20 3 * + +
        String str = invertToRearExpr("3*3-2/(2*3+2+(23-1))");  // 5 2 3 * 4 11 * 3 5 2 * 4 + + + + +
        //  String str = invertToRearExpr("1+((2+3)*4)-5");  //  1 2 3 + 4 * + 5 -
        inversePolandComputed(stringToList(str));

    }

    // 中缀表达式转后缀表达式(原)
    public static String midExprToRearExpr(String express) {
        // 首先准备两个栈 ，一个存储运算符，一个存储运算数
        Stack<String> signs = new Stack<>();
        Stack<String> nums = new Stack<>();

        int index = 0; // 字符串索引
        // 11+(32*2+12)-32/4
        while (true) {
            String c = express.charAt(index) + "";
            if (isOpe(c)) {
                // 是操作符

                // 过滤掉 peek()到没有的数据 防止报错
                try {
                    if (priority(c) <= priority(signs.peek())) {
                        // 如果当前运算符优先级小于等于栈顶的优先级则改运算符弹出
                        nums.add(signs.pop());
                    }
//                    else{
//                        nums.add(c);
//                    }
                } catch (Exception e) {

                }

                try {
                    if (c.equals("(")) {
                        signs.add(c); // 把 (压入栈中
                    } else if (signs.peek().equals("(")) {
                        // 说明该符号是(第一个字符直接压入
                        signs.add(c);
                    }
                } catch (Exception e) {
                }

                if (c.equals(")")) {
                    // 说明改组 () 操作完成，取出改()中运算优先级最低的数据
                    String sign = signs.pop();
                    signs.pop(); // 把 (丢出
                    nums.add(sign);

                    // 以) 结尾
                    if (index + 1 == express.length()) {

                        // 以 ) 结尾 需要弹出 符号栈底的最后一个符号
                        nums.add(signs.pop());
                        return getRearExpr(nums, nums.size());
                    }
                }
                if (signs.empty() && !(c.equals(")"))) {
                    // 栈底为空直接压入
                    signs.add(c);
                }


                index++; // 前移辅助指针


            } else {
                // 是数字
                String numsStr = c;

                // 连续读取数字字符，是符号字符就退出
                while (true) {
                    // 判断express下一位是否为数字，是就截取，不是就退出
                    index++;
                    // 以 数字结尾

                    // 已经达到字符串末尾
                    if (index == express.length()) {
                        nums.add(numsStr);  //  将数字压入数栈
                        nums.add(signs.pop()); // 将最后的符号压入数栈
                        return getRearExpr(nums, nums.size());
                    }
                    String c1 = express.charAt(index) + "";
                    if (!(isOpe(c1))) {
                        // 下一位是数字就读取，截取到字符串中
                        numsStr += c1;
                    } else {
                        // 下一位是操作符就退出
                        break;
                    }

                }


                // 将数字添加到栈中
                nums.add(numsStr);
            }
        }

    }


    // 中缀表达式转后缀表达式(新)
    public static String invertToRearExpr(String express) {
        /**
         *   中缀表达式转后缀表达式思路
         *  	1）从左向右遍历字符串
         *  	2）如果是 数字(包括连续) 就直接入 数栈
         *     3）如果是运算符 则 进行判断
         *     	1. 如果栈底为空 且 当前字符不是 ) 则直接存入符号栈底(当前字符不是 ) 是为了过滤开头就右()的情况)
         *     	2. 如果栈顶为 ( 则直接将当前运算符存入符号栈顶
         *     	3. 当碰到 ) ，则依次弹出符号栈顶的运算符到 数栈，直到碰到 ( 位置，此时丢弃()号
         *     	4. 如果当前运算符优先级 小于等于 符号栈顶的优先级 则将栈顶的运算符压入数栈，当前运算符压入栈顶，否则就将其压入栈顶
         *     4）重复遍历字符串直到达到表达式尾
         *     5）将符号栈中剩余的运算符依次淡出并压入数栈
         *     6） 将数栈所有的元素取出，存入集合反转在转为字符串即为后缀表达式
         *
         * */


        // 首先准备两个栈 ，一个存储运算符，一个存储运算数
        Stack<String> signs = new Stack<>();
        Stack<String> nums = new Stack<>();

        int index = 0; // 字符串索引
        // 11+(32*2+12)-32/4
        while (true) {
            String c = express.charAt(index) + "";
            if (isOpe(c)) {
                // 是操作符

                // 过滤掉 peek()到没有的数据 防止报错
                try {
                    if (priority(c) <= priority(signs.peek())) {
                        // 如果当前运算符优先级小于栈顶的优先级
                        nums.add(signs.pop());
                    }
                        signs.add(c);
                } catch (Exception e) {

                }

                // 如果以(开头则直接存储
                try {
                    if (c.equals("(")) {
                        signs.add(c); // 把 (压入栈中
                    } else if (signs.peek().equals("(") && !(c.equals(")"))) {
                        // !(c.equals(")")) 是为了过滤掉连续 ((3))括号的情况
                        // 说明该符号是(第一个字符直接压入
                        signs.add(c);
                    }
                } catch (Exception e) {
                }

                if (c.equals(")")) {
                    // 说明改组 () 操作完成，取出改()中运算优先级最低的数据
                    while (true) {
                        if (!(signs.peek().equals("("))) {
                            nums.add(signs.pop());
                        } else {
                            signs.pop(); // 把 (丢出
                            break;
                        }
                    }

                    // 以) 结尾
                    if (index + 1 == express.length()) {

                        // 以 ) 结尾 需要弹出 符号栈的剩余符号
                        while (signs.size() != 0) {
                            nums.add(signs.pop());
                        }
                        return getRearExpr(nums, nums.size());
                    }
                }
                if (signs.empty() && !(c.equals(")"))) {
                    // 栈底为空直接压入
                    signs.add(c);
                }


                index++; // 前移辅助指针


            } else {
                // 是数字
                String numsStr = c;

                // 连续读取数字字符，是符号字符就退出
                while (true) {
                    // 判断express下一位是否为数字，是就截取，不是就退出
                    index++;
                    // 以 数字结尾

                    // 已经达到字符串末尾
                    if (index == express.length()) {
                        nums.add(numsStr);  //  将数字压入数栈
                        while (signs.size() != 0) {
                            nums.add(signs.pop());
                        }
                        return getRearExpr(nums, nums.size());
                    }
                    String c1 = express.charAt(index) + "";
                    if (!(isOpe(c1))) {
                        // 下一位是数字就读取，截取到字符串中
                        numsStr += c1;
                    } else {
                        // 下一位是操作符就退出
                        break;
                    }

                }


                // 将数字添加到栈中
                nums.add(numsStr);
            }
        }

    }

    // 从数字栈中取出完整的数据
    public static String getRearExpr(Stack<String> expr, int length) {
        String str = "";

        // 取出所有的值
        while (length > 0) {

            str += expr.pop() + " ";

            length--;
        }
        // 从栈中取出来的值是相反的，所以我们要进行反转
        String[] s = str.split(" ");
        // 把数据存放到集合中
        ArrayList<String> list = new ArrayList<>();
        for (String s1 : s) {
            list.add(s1);
        }
        // 反转数据
        Collections.reverse(list);
        String resultStr = "";
        // 添加空格然后返回
        for (String s1 : list) {
            resultStr += s1 + " ";
        }

        return resultStr;
    }

    // 将字符串转换为集合
    public static List<String> stringToList(String str) {
        String[] s = str.split(" ");
        ArrayList<String> strings = new ArrayList<>();
        for (String s1 : s) {
            strings.add(s1);
        }
        return strings;
    }

    // 逆波兰表达式入栈
    public static void inversePolandComputed(List<String> list) {

        Stack<Integer> nums = new Stack<>();
        for (String s : list) {
            if (isOpe(s)) {
                int num1 = nums.pop(); // 取出栈顶的数
                int num2 = nums.pop();
                Integer result = computed(num1, num2, s); // 进行计算
                nums.add(result);
            } else {
                // 是数字
                nums.add(Integer.parseInt(s));
            }
        }
        // 一系列计算后 数栈只会右一个数 打印即可
        System.out.println(nums.pop());
    }

    public static boolean isOpe(String ope) {
        switch (ope) {
            case "+":
            case "-":
            case "*":
            case "/":
            case "(":
            case ")":
                return true;
            default:
                return false;
        }
    }

    public static Integer computed(int num1, int num2, String ope) {
        switch (ope) {
            case "*":
                return num2 * num1;
            case "/":
                return num2 / num1;
            case "+":
                return num2 + num1;
            case "-":
                return num2 - num1;
            default:
                return null;
        }
    }

    // 得到优先级
    public static Integer priority(String ch) {
        switch (ch) {
            case "+":
            case "-":
                return 0;
            case "*":
            case "/":
                return 1;
            default:
                return null;
        }
    }
}
