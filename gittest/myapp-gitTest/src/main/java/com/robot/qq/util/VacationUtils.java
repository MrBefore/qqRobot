package com.robot.qq.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 2022年假期判断
 *
 * @author wangZq
 */

@Component
public class VacationUtils {

    /**
     * 假期
     */
    private static HashMap<String, String> map = new HashMap<>();

    /**
     * 发工资
     */
    private static HashMap<String, String> money = new HashMap<>();

    static {
        map.put("春节", "2023-01-22");
        map.put("清明节", "2023-04-05");
        map.put("劳动节", "2023-04-30");
        map.put("端午节", "2023-06-22");
        map.put("中秋节", "2022-09-10");
        map.put("国庆节", "2022-10-01");
        map.put("元旦", "2023-01-01");
    }

    static {
        money.put("一月", "2023-01-30");
        money.put("二月", "2023-02-28");
        money.put("三月", "2023-03-30");
        money.put("四月", "2023-04-30");
        money.put("五月", "2023-05-30");
        money.put("六月", "2023-06-30");
        money.put("七月", "2022-07-30");
        money.put("八月", "2022-08-30");
        money.put("九月", "2022-09-30");
        money.put("十月", "2022-10-30");
        money.put("十一月", "2022-11-30");
        money.put("十二月", "2022-12-30");
    }

    /**
     * 计算距离假期还剩多少天
     * <p>
     * 显示当日星期，计算所有假期时间
     *
     * @return Author：YCM 2017年2月7日 下午2:45:33
     */
    public static String calcEffectiveDate() throws ParseException {
        return calcEffectiveDateUtil(true);
    }

    /**
     * 计算距离假期还剩多少天
     * <p>
     * 显示当日星期，计算所有假期时间
     *
     * @return Author：YCM 2017年2月7日 下午2:45:33
     */
    public static String calcEffectiveDateNoPhoto() throws ParseException {
        return calcEffectiveDateUtil(false);
    }

    /**
     * 计算并输出假期公共方法
     *
     * @param flag Boolean
     * @return String
     * @throws ParseException ParseException
     */
    private static String calcEffectiveDateUtil(Boolean flag) throws ParseException {
        // 获取当前日期
        Date now = new Date();
        // 返回星期几
        String weekDay = dayForWeek();
        HashMap<String, Integer> dayMap = new HashMap<>(10);
        HashMap<String, Integer> moneyMap = new HashMap<>(10);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat nowDaySdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        map.forEach((key, value) -> {
            try {
                Date vacationDate = sdf.parse(value);
                Integer number = getDaysBySecondForDay(now, vacationDate);
                dayMap.put(key, number);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        money.forEach((key, value) -> {
            try {
                Date vacationDate = sdf.parse(value);
                Integer number = getDaysBySecondForDay(now, vacationDate);
                moneyMap.put(key, number);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        String nowDays = nowDaySdf.format(now);
        int number = getDayForWeekDay();

        int hour = getDaysBySecondForHour();
        StringBuilder result = new StringBuilder();
        List<Map.Entry<String, Integer>> list = sortByValue(dayMap);
        List<Map.Entry<String, Integer>> moneyList = sortByValue(moneyMap);

        result.append("摸鱼办公室").append("\n");

        result.append("今天是 ").append(nowDays).append(" 星期").append(weekDay).append("\n");

        result.append("你好，摸鱼人，工作再累，一定不要忘记摸鱼哦 ! 有事没事起身，去茶水间、去廊道、去天台走走，别老在工位上坐着。 多喝点水，钱是老板的，但命是自己的 !").append("\n");

        if (flag) {

            result.append("\uD83D\uDC1F").append("\uD83D\uDC1F").append("\uD83D\uDC1F").append(" 距离 下班 还有 ").append(hour).append(" 小时").append("\n");

            result.append("\uD83D\uDC1F").append("\uD83D\uDC1F").append("\uD83D\uDC1F").append(" 距离 周末 还有 ").append(number).append(" 天 零 ").append(hour).append(" 小时").append("\n");

            for (Map.Entry<String, Integer> map : list) {
                result.append("\uD83D\uDC1F").append("\uD83D\uDC1F").append("\uD83D\uDC1F").append(" 距离 ").append(map.getKey()).append(" 放假还有 ").append(map.getValue()).append(" 天 零 ").append(hour).append(" 小时").append("\n");
            }

            result.append("*********************************************").append("\n");

            result.append("下面是工资发放信息： ").append("\n");

            for (Map.Entry<String, Integer> map : moneyList) {
                result.append("\uD83D\uDCB0").append("\uD83D\uDCB0").append("\uD83D\uDCB0").append(" 距离 ").append(map.getKey()).append(" 发工资，还有 ").append(map.getValue()).append(" 天").append("\n");
            }
            return result.toString();
        } else {

            result.append(" 距离 下班 还有 ").append(hour).append(" 小时").append("\n");

            result.append(" 距离 周末 还有 ").append(number).append(" 天 零 ").append(hour).append(" 小时").append("\n");

            for (Map.Entry<String, Integer> map : list) {
                result.append(" 距离 ").append(map.getKey()).append(" 放假还有 ").append(map.getValue()).append(" 天 零 ").append(hour).append(" 小时").append("\n");
            }

            result.append("********************************************************").append("\n");

            result.append("下面是工资发放信息： ").append("\n");

            for (Map.Entry<String, Integer> map : moneyList) {
                result.append(" 距离 ").append(map.getKey()).append(" 发工资，还有 ").append(map.getValue()).append(" 天").append("\n");
            }
            return result.toString();
        }
    }

    /**
     * 指出当前星期
     *
     * @return String
     */
    private static String dayForWeek() {
        Date tmpDate = new Date();
        Calendar cal = Calendar.getInstance();
        String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};
        try {
            cal.setTime(tmpDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 指示一个星期中的某天。
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 指出当前距离周末的天数
     *
     * @return String
     */
    private static int getDayForWeekDay() {
        Calendar cal = Calendar.getInstance();
        int today = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return 5 - today;
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔（天数）
     *
     * @param date1 Date
     * @param date2 Date
     * @return int
     */
    private static Integer getDaysBySecondForDay(Date date1, Date date2) {
        return (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔（小时数）
     *
     * @return int
     */
    private static Integer getDaysBySecondForHour() throws ParseException {
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yy:MM:dd hh:mm:ss");
        String nowDays = sdf.format(nowDate);
        String now = nowDays.substring(0, 9) + "18:00:00";
        Date date = sdf.parse(now);
        return (int) ((date.getTime() - nowDate.getTime()) / (1000 * 3600));
    }

    /**
     * 按照值排序
     *
     * @param map HashMap
     * @return List
     */
    private static List<Map.Entry<String, Integer>> sortByValue(HashMap<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Comparator.comparing(Map.Entry::getValue));
        return list;
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(VacationUtils.calcEffectiveDate());
    }
}
