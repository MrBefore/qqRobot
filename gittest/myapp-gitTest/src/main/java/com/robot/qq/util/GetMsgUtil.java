package com.robot.qq.util;

import java.net.URLDecoder;
import java.util.Scanner;

/**
 * 获取加密信息工具类
 *
 * @author wangzk
 */
public class GetMsgUtil {
    /**
     * 解密
     *
     * @param args String
     */
    public static void main(String[] args) {
        System.out.println("请输入需要解密的内容：");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        System.out.println("解密内容为：");
        System.out.println(URLDecoder.decode(input));
    }
}
