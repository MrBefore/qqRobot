package com.robot.qq.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 随机食品工具类
 *
 * @author wangzk
 */
public class FoodUtils {
    private static List<String> foodList = new ArrayList<>();

    static {
        foodList.add("小碗菜");
        foodList.add("热干面");
        foodList.add("饺子");
        foodList.add("刀削面");
        foodList.add("肯德基");
        foodList.add("炒河粉");
        foodList.add("麻辣香锅");
        foodList.add("鸡腿饭");
        foodList.add("鸭腿饭");
        foodList.add("兰州拉面");
        foodList.add("过桥米线");
        foodList.add("花甲粉");
        foodList.add("花甲面");
        foodList.add("炒面");
        foodList.add("蒸面条");
        foodList.add("老碗面");
        foodList.add("铁板炒饭");
        foodList.add("五谷渔粉");
        foodList.add("算了，不吃了");
    }

    /**
     * 获取随机食物
     *
     * @return String
     */
    public static String getRandomFood() {
        Random random = new Random();
        int size = foodList.size();
        int num = random.nextInt(size);
        return foodList.get(num);
    }
}
