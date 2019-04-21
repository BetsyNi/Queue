package com.nyj.queue.thread;

import com.nyj.queue.entity.Customer;
import com.nyj.queue.method.RandomCombo;
import com.nyj.queue.method.RandomTimeRange;

import java.util.concurrent.TimeUnit;

/**
 * @author : Ni Yujia
 * @date : 2019/4/15
 */
public class CustomerThread extends Thread {

    private RandomCombo randomCombo = new RandomCombo();

    private RandomTimeRange randomTimeRange = new RandomTimeRange();

    private Customer customer = new Customer();

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getName() + " " + i);
            // 顾客到来概率
            if (randomTimeRange.rateTime(0.8)) {
                // 顾客携带套餐组合0
                randomCombo.randomCombo(i);
                customer.getArriveTime();
            }
            // 顾客到来的时间间隔
            int sleepTime = randomTimeRange.randomTime(1,3);
            try {
                TimeUnit.MILLISECONDS.sleep(sleepTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new CustomerThread().start();
    }

}