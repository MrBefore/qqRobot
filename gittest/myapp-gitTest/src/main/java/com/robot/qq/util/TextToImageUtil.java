package com.robot.qq.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;


/**
 * Text转换为Image工具类
 *
 * @author wangzk
 */
public class TextToImageUtil {


    /**
     * 根据str,font的样式等生成图片
     *
     * @param strArr      String
     * @param font        Font
     * @param imageHeight int
     * @param everyLine   int
     * @param lineHeight  int
     * @throws Exception Exception
     */
    private static void createImage(String[] strArr, Font font,
                                    int imageHeight, int everyLine, int lineHeight) throws Exception {

        FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(font);
        // 标点符号也算一个字
        int stringWidth = fm.charWidth('字');
        int lineStringNum = 500 % stringWidth == 0 ? (500 / stringWidth) : (500 / stringWidth) + 1;
        List<String> newList = new ArrayList<>();
        List<String> listStr = new ArrayList<>(Arrays.asList(strArr));
        for (int j = 0; j < listStr.size(); j++) {
            if (listStr.get(j).length() > lineStringNum) {
                newList.add(listStr.get(j).substring(0, lineStringNum));
                listStr.add(j + 1, listStr.get(j).substring(lineStringNum));
                listStr.set(j, listStr.get(j).substring(0, lineStringNum));
            } else {
                newList.add(listStr.get(j));
            }
        }

        int a = newList.size();
        int imgNum = a % everyLine == 0 ? (a / everyLine) : (a / everyLine) + 1;

        for (int m = 0; m < imgNum; m++) {
            String filePath = "D:/Photo/moYu/" + m + ".jpg";
            File outFile = new File(filePath);
            // 创建图片
            BufferedImage image = new BufferedImage(500, imageHeight,
                    BufferedImage.TYPE_INT_BGR);
            Graphics g = image.getGraphics();
            Color bc = null;
            Color fc = null;

            Color bc0 = new Color(102, 113, 192);
            Color fc0 = new Color(213, 211, 96);

            Color bc1 = new Color(159, 239, 226);
            Color fc1 = new Color(167, 58, 80);

            Color bc2 = new Color(251, 155, 105);
            Color fc2 = new Color(6, 5, 0);

            Color bc3 = new Color(32, 174, 102);
            Color fc3 = new Color(255, 199, 192);

            Random random = new Random();
            int num = random.nextInt(4);
            if (num == 0) {
                bc = bc0;
                fc = fc0;
            } else if (num == 1) {
                bc = bc1;
                fc = fc1;
            } else if (num == 2) {
                bc = bc2;
                fc = fc2;
            } else if (num == 3) {
                bc = bc3;
                fc = fc3;
            }

            g.setClip(0, 0, 500, imageHeight);
            // 背景色白色
            g.setColor(bc);

            g.fillRect(0, 0, 500, imageHeight);
            //  字体颜色黑色
            g.setColor(fc);
            // 设置画笔字体
            g.setFont(font);
            // 每张多少行，当到最后一张时判断是否填充满
            for (int i = 0; i < everyLine; i++) {
                int index = i + m * everyLine;
                if (newList.size() - 1 >= index) {
                    g.drawString(newList.get(index), 0, lineHeight * (i + 1));
                }
            }
            g.dispose();
            // 输出png图片
            ImageIO.write(image, "jpg", outFile);
        }
    }


    public static void createImage(String str) throws Exception {
        String[] strArr = str.split("\n");
        // 每张图片的高度
        int imageHeight = 650;
        // 每行或者每个文字的高度
        int lineHeight = 20;
        // 每张图片有多少行文字
        int everyLine = imageHeight / lineHeight;
        createImage(strArr, new Font("方正粗黑宋简体", Font.BOLD, 16), imageHeight, everyLine, lineHeight);
        // createImage(strArr, new Font("华文彩云", Font.PLAIN, 18), imageHeight, everyLine, lineHeight);
    }


    public static void main(String[] args) throws Exception {
        String imageStr = VacationUtils.calcEffectiveDateNoPhoto();
        createImage(imageStr);
    }
}
