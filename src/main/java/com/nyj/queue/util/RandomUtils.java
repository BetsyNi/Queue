package com.nyj.queue.util;

import com.nyj.queue.exception.BusinessException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 随机数工具类-注意:此类不保证线程安全
 *
 * @author: Xinling Jing
 * @date: 2019/4/20 0020 19:38
 * @description:
 */
public class RandomUtils {

    private static SimpleDateFormat format = null;
    private static Random random = null;
    private static final int THOUSAND = 1000;


    static {
        format = new SimpleDateFormat("HH:mm:ss");
        random = new Random();
    }

    /**
     * 获取一天内的随机时间戳,单位 ms
     *
     * @return 该随机时间戳自当天 08:00:00 之后所经过的毫秒数
     */
    public static long randomTime() throws ParseException {
        return randomTime("00:00:00", "23:59:59");
    }

    /**
     * 以易读格式获取一天指定时间范围内时间,格式: HH:mm:ss
     *
     * @return 以 HH:mm:ss 格式返回随机时间
     */
    public static String randomTimeForHuman() throws ParseException {
        long randomNum = RandomUtils.randomTime();
        Date date = new Date(randomNum);
        return RandomUtils.format.format(date);
    }

    /**
     * 获取一天内指定时间范围内的随机时间戳,单位 ms
     *
     * @param beginTime 格式要求: HH:mm:ss
     * @param endTime   格式要求: HH:mm:ss
     * @return 该随机时间戳自当天 08:00:00 之后所经过的毫秒数
     */
    public static long randomTime(String beginTime, String endTime) throws ParseException {
        Date begin = format.parse(beginTime);
        Date end = format.parse(endTime);
        if (begin.compareTo(end) >= 0) {
            throw new BusinessException(1001, "End time can not less than begin time");
        }
        // [m, n]
        int tmp = random.nextInt((int) (end.getTime() / THOUSAND - begin.getTime() / THOUSAND + 1));
        return (begin.getTime() + tmp) * THOUSAND;
    }

    /**
     * 获取指定时间所对应的时间戳,单位 ms
     *
     * @param time 格式要求: HH:mm:ss
     * @return 该时间自当天 08:00:00 之后所经过的毫秒数
     */
    public static int getTime(String time) {
        try {
            return (int) format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getTimeForHuman(int time) {
        return RandomUtils.format.format(time);
    }

    /**
     * 以易读格式获取一天指定时间范围内时间,格式: HH:mm:ss
     *
     * @param beginTime 格式要求: HH:mm:ss
     * @param endTime   格式要求: HH:mm:ss
     * @return 以 HH:mm:ss 格式返回随机时间
     */
    public static String randomTimeForHuman(String beginTime, String endTime) throws ParseException {
        long randomNum = RandomUtils.randomTime(beginTime, endTime);
        Date date = new Date(randomNum);
        return RandomUtils.format.format(date);
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(RandomUtils.getTimeForHuman(1000));
        System.out.println(RandomUtils.getTime("08:03:49"));
    }
}
