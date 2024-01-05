package com.algorithm.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 桶排序算法
 *
 * @author wangzk
 */
public class BucketSorting {

    public static List<String> bucketSort(List<String> inputList, int size, boolean flag) {
        List<String> outList = new ArrayList<>();
        // 初始化桶列表信息
        List<Integer> setList = new ArrayList<>(size);
        for (int s = 0; s < size; s++) {
            setList.add(s, 0);
        }
        // 判断传入的列表信息，并把数据放入对应的桶中
        for (String input : inputList) {
            for (int s = 0; s < size; s++) {
                if (String.valueOf(s).equals(input)) {
                    setList.set(s, setList.get(s) + 1);
                }
            }
        }
        if (flag) {
            // 从大到小获取桶中的数据，并输出
            for (int a = setList.size() - 1; a > 0; a--) {
                Integer num = setList.get(a);
                for (int b = 0; b < num; b++) {
                    outList.add(String.valueOf(a));
                }
            }
        } else {
            // 从小到大获取桶中的数据，并输出
            for (int a = 0; a < setList.size(); a++) {
                Integer num = setList.get(a);
                for (int b = 0; b < num; b++) {
                    outList.add(String.valueOf(a));
                }
            }
        }
        return outList;
    }


    public static void main(String[] args) {
        List<String> inputList = new ArrayList<>();
        inputList.add("588");
        inputList.add("122");
        inputList.add("895");
        inputList.add("523");
        inputList.add("844");
        inputList.add("621");
        List<String> outList = bucketSort(inputList, 1000, false);
        for (String out : outList) {
            System.out.println(out);
        }
        System.out.println("end");

    }


}
