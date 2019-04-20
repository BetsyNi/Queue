package com.nyj.queue.util;

import com.nyj.queue.exception.BusinessException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 随机数工具类
 *
 * @author: Xinling Jing
 * @date: 2019/4/20 0020 19:38
 * @description:
 */
public class RandomUtils {

    private static SimpleDateFormat format = null;

    static {
        format = new SimpleDateFormat("HH:mm:ss");
    }

    /**
     * 生成一天内的随机时间，格式：HH:mm:ss
     */
    public static String randomTime() throws ParseException {
        return randomTime("00:00:00", "23:59:59");
    }

    /**
     * 返回一天内指定时间范围内的随机时间
     *
     * @param beginTime 格式要求： HH:mm:ss
     * @param endTime   格式要求： HH:mm:ss
     */
    public static String randomTime(String beginTime, String endTime) throws ParseException {
        Date begin = format.parse(beginTime);
        Date end = format.parse(endTime);
        if (begin.compareTo(end) >= 0) {
            throw new BusinessException(1001, "End time can not less than begin time");
        }
        return "";
    }
}
