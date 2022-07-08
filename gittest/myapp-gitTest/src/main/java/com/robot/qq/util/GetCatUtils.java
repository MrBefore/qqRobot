package com.robot.qq.util;

import java.util.Random;

/**
 * @author wangzk
 */
public class GetCatUtils {
    /**
     * 获取猫猫图片
     *
     * @return String
     */
    public static String getCat() {
        String url = "D:/Photo/cat/";
        Random random = new Random();
        int num = random.nextInt(101);
        url = url + num + ".jpg";
        return url;
    }


    /**
     * 获取表情包
     *
     * @return String
     */
    public static String getEmoCation() {
        String url = "D:/Photo/emoticon/";
        Random random = new Random();
        int num = random.nextInt(153);
        url = url + num + ".jpg";
        return url;
    }

    /**
     * 星期四
     *
     * @return String
     */
    public static String getThursday() {
        String url = "D:/Photo/Thursday/";
        Random random = new Random();
        int num = random.nextInt(4);
        url = url + num + ".jpg";
        return url;
    }

}
