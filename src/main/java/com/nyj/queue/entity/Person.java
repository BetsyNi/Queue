package com.nyj.queue.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 体检人
 *
 * @author: Xinling Jing
 * @date: 2019/4/20 0020 06:22
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    /**
     * 体检人编号
     */
    private Integer no;
    /**
     * 体检人到达医院的时间
     */
    private Long arriveTime;
    /**
     * 病人所需要体检的项目列表(体检套餐)
     */
    private List<Integer> phyExamNos;

    public static void main(String[] args) throws ParseException {
        Long arriveTime = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        System.out.println(format.format(new Date()));

        String beginTime = "19:01:20";
        String endTime = "19:01:20";
        SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
        Date d1 = format1.parse(beginTime);
        Date d2 = format1.parse(endTime);
        System.out.println(endTime.compareTo(beginTime));
    }

}
