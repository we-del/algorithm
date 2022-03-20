package com.data_structure.linkedlist;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtil {
    @SuppressWarnings({"all"})
    public static String[] randomDataOfPeople() {

        /*
            问题1： 名字问题，听起来像男名，而性别缺随机到女性，反之如此，学了正则再来优化
                包含 武 胜 勇 超 哲 豪 江 全 兴 贤 阳 康 等默认为男姓，其余不变
 
        */
        String[] date = getRandomDateTime(); // 获得随机日期
        String birthday = date[0] + "-" + date[1] + "-" + date[2];

        // 获得随机人名
        String name = getRandomName();

        // 获得随机电话
        String telephone = getRandomTelephone();

        // 获得随机性别
        String sex = getRandomSex();

        // 根据姓名判断性别
        sex = identitySex(name, sex);

        String[] result = {name, sex, birthday, telephone};
        return result;
    }

    public static String getRandomSex() {
        String[] sexRandom = {"男", "女"};
        String sex = sexRandom[(int) (Math.random() * sexRandom.length)];
        return sex;
    }

    public static boolean isMale(String name) {
        /**
         *  "翔", "天", "靖", "文", "武", "胜", "勇", "盛", "超", "哲", "孟", "豪", "梓", "祺", "江", "全", "兴", "良",
         *   "宇", "星", "睿", "贤", "曾", "新", "曦", "景", "轩", "阳", "荣", "正", "鸿", "宏", "泽", "康", "光","浩",
         *   "郝", "然", "昌", "雅", "辉", "信", "兰", "菊", "昂", "涵", "灵", "楠", "岚", "蝶", "琴", "香", "晴","柔",
         *  "枫", "柳", "蕊", "睿", "白", "玉", "海", "薇", "烟", "丹", "凝", "萍", "梦", "烟", "燕", "雪", "怀",
         *   "芙", "初", "宛", "若"
         * */

        Pattern pattern = Pattern.compile("武|胜|勇|超|哲|豪|江|全|兴|贤|阳|康|昌|靖|郝|海");
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    public static boolean isFemale(String name) {
        /**
         *  "翔", "天", "靖", "文", "武", "胜", "勇", "盛", "超", "哲", "孟", "豪", "梓", "祺", "江", "全", "兴", "良",
         *   "宇", "星", "睿", "贤", "曾", "新", "曦", "景", "轩", "阳", "荣", "正", "鸿", "宏", "泽", "康", "光","浩",
         *   "郝", "然", "昌", "雅", "辉", "信", "兰", "菊", "昂", "涵", "灵", "楠", "岚", "蝶", "琴", "香", "晴","柔",
         *  "枫", "柳", "蕊", "睿", "白", "玉", "海", "薇", "烟", "丹", "凝", "萍", "梦", "烟", "燕", "雪", "怀",
         *   "芙", "初", "宛", "若"
         * */

        Pattern pattern = Pattern.compile("雅|兰|菊|楠|蝶|琴|香|柔|蕊|玉|丹|萍|燕|雪|郝|芙|若");
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    public static String getRandomName() {

        String[] familyNameRandom = {
                "周", "李", "杨", "王", "欧阳", "吴", "袁", "董", "赵", "魏", "郑", "张", "陈", "夏", "孙", "钱", "王",
                "卫", "许", "徐", "严", "华", "陶", "施", "于", "喻", "章", "苏", "鲁", "方", "凤", "曹", "司马", "公孙",
                "彭", "余", "何", "马", "苗", "郭", "祁", "黄", "雷", "薛", "唐", "明", "米", "汤", "殷", "贺", "史", "乐",
                "时", "元", "贝", "毛", "皮", "罗", "和", "桂", "熊"
        };
        String[] nameRandom = {
                "翔", "天", "靖", "文", "武", "胜", "勇", "盛", "超", "哲", "孟", "豪", "梓", "祺", "江", "全", "兴", "良",
                "宇", "星", "睿", "贤", "曾", "新", "曦", "景", "轩", "阳", "荣", "正", "鸿", "宏", "泽", "康", "光", "浩",
                "郝", "然", "昌", "雅", "辉", "信", "兰", "菊", "昂", "涵", "灵", "楠", "岚", "蝶", "琴", "香", "晴", "柔",
                "枫", "柳", "蕊", "睿", "白", "玉", "海", "薇", "烟", "丹", "凝", "萍", "梦", "烟", "燕", "雪", "怀",
                "芙", "初", "宛", "若"
        };
        String name = familyNameRandom[(int) (Math.random() * familyNameRandom.length)]
                + nameRandom[(int) (Math.random() * nameRandom.length)]
                + ((int) (Math.random() * 2) > 0 ? nameRandom[(int) (Math.random() * nameRandom.length)] : "");
        return name;
    }

    public static String[] getRandomDateTime() {

        int yearRandom = (int) ((Math.random() * 10) + 1995);
        int monthRandom = (int) ((Math.random() * 12) + 1);
        int dayRandom = (int) ((Math.random() * 31) + 1); // 最大31 ，之后在系统对月份做判断

        boolean isLeapYear = false; // 是否为闰年

        // 闰年 每4年是一个闰年，但每个整年只有能被4整除的是闰年(100,200,300不是闰年，400是闰年)
        if (yearRandom % 100 == 0) {
            if (yearRandom % 400 == 0) { // 闰年
                isLeapYear = true;
            } else { // 平年
                isLeapYear = false;
            }
        } else {
            if (yearRandom % 4 == 0) {

                isLeapYear = true;
            } else {
                isLeapYear = false;
            }
        }

        // 做日期越界处理
        switch (monthRandom) {
            // 大月: 1 3 5 7 8 10 12 小月: 4 6 9 11 变月: 2
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (dayRandom > 30) {
                    dayRandom = 30;
                }
                break;
            case 2:
                if (dayRandom > 28) { // 如果随机数大于28就需要做判断
                    if (isLeapYear) {
                        dayRandom = 29;
                    } else {
                        dayRandom = 28;
                    }
                }
                break;
        }

        // 处理以下月日(如果小于10则在其前添0)
        String year = yearRandom + "";
        String month = monthRandom + "";
        String day = dayRandom + "";
        if (monthRandom < 10) {
            month = "0" + month;
        }
        if (dayRandom < 10) {
            day = "0" + day;
        }
        String[] date = {year, month, day};

        return date;
    }

    public static String getRandomTelephone() {


        String telephoneRandom = "1";
        for (int i = 0; i < 10; i++) { // 得到完整的随机电话
            telephoneRandom += (int) ((Math.random() * 10));
        }
        return telephoneRandom;
    }

    public static String identitySex(String name, String sex) {
        if (isMale(name)) {
            sex = "男";
        }
        if (isFemale(name)) {
            sex = "女";
        }
        return sex;
    }
}