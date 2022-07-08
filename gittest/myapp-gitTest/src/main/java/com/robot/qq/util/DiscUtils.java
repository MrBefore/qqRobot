package com.robot.qq.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取随机数工具类
 *
 * @author wangzk
 */
public class DiscUtils {
    /**
     * 输入指定值，获取0——制定值之间的随机数
     *
     * @param str String
     * @return String
     */
    public static String getOneDice(String str) {
        String max = str.trim().substring(1).substring(1).substring(1).substring(1).substring(1);
        String regEx = "^-?[0-9]+$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(max);
        if (matcher.find()) {
            Random random = new Random();
            int maxNum = Integer.parseInt(max);
            int num = random.nextInt(maxNum + 1);
            return "Disc:" + max + " = " + num;
        } else {
            return "输入点数不是纯数字！请重新输入";
        }
    }

    /**
     * 测试
     *
     * @return String
     */
    public static String test() {
        Random random = new Random();
        int num = random.nextInt(101);
        return "Disc:100" + " = " + num;
    }
}

