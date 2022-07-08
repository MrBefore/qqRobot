package com.robot.qq.util;

import java.util.Scanner;

/**
 * 模拟抽卡工具类
 *
 * @author wangzk
 */
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

    public static void main(String[] args) {
        drop();
    }
}
