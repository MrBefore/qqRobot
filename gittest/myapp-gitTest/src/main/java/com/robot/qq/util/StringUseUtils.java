package com.robot.qq.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 通用List方法工具类
 *
 * @author wangZk
 */
public class StringUseUtils {
    /**
     * 获取两个集合的合集
     *
     * @param s1 List
     * @param s2 List
     * @return List
     */
    private static List<Long> getMaxList(List<Long> s1, List<Long> s2) {
        List<Long> allList = new ArrayList<>();
        allList.addAll(s1);
        allList.addAll(s2);
        return allList.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 获取两个集合的交集
     *
     * @param s1 List
     * @param s2 List
     * @return List
     */
    private static List<Object> getMinList(List<Object> s1, List<Object> s2) {
        List<Object> minList = new ArrayList<>();
        if (s1.size() >= s2.size()) {
            for (Object obj : s1) {
                for (Object object : s2) {
                    if (obj.equals(object)) {
                        minList.add(obj);
                    }
                }
            }
        } else {
            for (Object obj : s2) {
                for (Object object : s1) {
                    if (obj.equals(object)) {
                        minList.add(obj);
                    }
                }
            }
        }
        return minList;
    }

    /**
     * 获取差集
     *
     * @param s1 List
     * @param s2 List
     * @return List
     */
    private static List<Object> getSetList(List<Object> s1, List<Object> s2) {
        List<Object> setList;
        if (s1.size() >= s2.size()) {
            setList = s1;
            setList.removeAll(s2);
        } else {
            setList = s2;
            setList.removeAll(s1);
        }
        return setList;
    }


    /**
     * 逗号分隔的字符串转list
     *
     * @param str String
     * @return List
     */
    public static List<String> getListStr(String str, String split) {
        String[] list = str.split(split);
        return Arrays.asList(list);
    }


    /**
     * 获取长度
     *
     * @return int
     */
    private static int getStingLong() {
        String s = "7a8be5fcfff691d900ac285bcad6c3f1";
        return s.length();
    }

    /**
     * 获取长度
     *
     * @return int
     */
    private static String replaceSting(String str, String s) {
        return str.replaceAll(s, "");
    }


    /**
     * 获取Map信息，并转换为String
     *
     * @param map Map
     * @return String
     */
    public static String changeMapToString(Map<String, Object> map) {
        JSONObject json = new JSONObject(map);
        String photo = json.get("data").toString().substring(1).substring(1).substring(1).substring(1).substring(1);
        photo = photo.substring(0, photo.length() - 1);
        return photo;
    }

    /**
     * 测试用
     *
     * @param args String
     */
    public static void main(String[] args) {
        // 原
        List<Long> list1 = new ArrayList<>();
        list1.add(1L);
        list1.add(2L);
        list1.add(3L);
        list1.add(5L);
        // 新
        List<Long> list2 = new ArrayList<>();
        list1.add(2L);
        list1.add(3L);
        // 删除全部，然后全部新增
        if (list1.size() > list2.size()) {

        }
        // 增
        // 不动


        List<Long> list = getMaxList(list1, list2);


        System.out.println(list);
    }


}
