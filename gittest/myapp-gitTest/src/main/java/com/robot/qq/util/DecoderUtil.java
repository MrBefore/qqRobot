package com.robot.qq.util;

import java.net.URLDecoder;
import java.util.Scanner;

/**
 * @author wangzk
 */
public class DecoderUtil {

    /**
     * 解码
     *
     * @param s String
     * @return String
     */
    private static String getDecodeMgs(String s) {
        return URLDecoder.decode(s);
    }

    /**
     * 解密
     *
     * @param args String
     */
    public static void main(String[] args) {
        Scanner n = new Scanner(System.in);
        System.out.println("请输入密文：");
        String s = n.next();
        String code = getDecodeMgs(s);
        System.out.println(code);
    }

}
