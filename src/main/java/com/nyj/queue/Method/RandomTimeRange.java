package com.nyj.queue.Method;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * @author : Ni Yujia
 * @date : 2019/4/13
 */
@Data
public class RandomTimeRange {

    public int randomTime(int minTime,int maxTime) {
        // 强转类型和后面的式子要分别加括号，否则值为0
        int sleepTime = (int) (Math.random() * (maxTime - minTime) + minTime) * 1000;
        try {
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (sleepTime/1000);
    }

    public boolean rateTime(double rate) {
        // 判断事件发生概率，rate为设定的概率
        if (Math.random() < rate) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        RandomTimeRange randomTime = new RandomTimeRange();
        for (int i = 0; i < 10; i++) {

            System.out.println(randomTime.randomTime(1,10));

//            if (randomTime.rateTime(0.5)) {
//                System.out.println(true);
//            }

//            System.out.println("**********");
        }
    }
}
