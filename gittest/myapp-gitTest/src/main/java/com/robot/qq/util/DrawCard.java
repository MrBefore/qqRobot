package com.robot.qq.util;

import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 * 模拟抽卡工具类
 *
 * @author wangzk
 */
@Slf4j
public class DrawCard {

    /**
     * 模拟抽卡
     */
    private static void drop() {
        Scanner n = new Scanner(System.in);
        System.out.println("是否抽卡？y/n");
        String s = n.next();
        String yes = "y";
        int max = 9;
        if (yes.equals(s)) {
            int count = 0;
            for (int j = 0; j < max; j++) {
                int i = (int) (Math.random() * 100);
                int k = i + 1;
                if (i >= 0 && i < 90) {
                    count += 1;
                    System.out.println("R" + "\t" + "\uD83E\uDD49");
                } else if (i >= 90 && i < 99) {
                    count = 0;
                    System.out.println("SR" + "\t" + "\uD83E\uDD48");
                } else {
                    count = 0;
                    System.out.println("SSR" + "\t" + "\uD83E\uDD47");
                }
            }
            if (count == max) {
                int i = (int) (Math.random() * 10);
                int k = 90 + i + 1;
                if (i >= 0 && i < max) {
                    System.out.println("SR" + "\t" + "\uD83E\uDD48");
                } else {
                    System.out.println("SSR" + "\t" + "\uD83E\uDD47");
                }
            }
        } else {
            System.out.println("抽卡结束！");
        }
    }

    /**
     * 计算环比变化
     *
     * @param num1 String
     * @param num2 String
     * @return String
     */
    private static String getRoundPercent(String num1, String num2) {
        try {
            //接受字符串类型的百分数
            NumberFormat nf = NumberFormat.getPercentInstance();
            //保留两位小数
            nf.setMaximumFractionDigits(2);
            //将double类型的量转化为字符串输出
            DecimalFormat df = new DecimalFormat("#0.00%");
            //用NumberFormat的parse方法和doubleValue方法将字符串百分数转换为double类型的可计算数值
            double round = (nf.parse(num1).doubleValue() - nf.parse(num2).doubleValue()) / nf.parse(num2).doubleValue();
            //再将double转为String输出
            return df.format(round);
        } catch (Exception e) {
            log.info("计算环比变化率出现异常", e);
            return "0.00%";
        }
    }


    public static void main(String[] args) {
        String round = getRoundPercent("2.57%", "50.24%");
        System.out.println(round);
    }
}
