package com.nyj.queue.Entity;

import lombok.Data;

import java.util.LinkedList;

/**
 * @author : Ni Yujia
 * @date : 2019/4/13
 */
@SuppressWarnings("ALL")
@Data
public class Customer {

    // 顾客id
    private int id;
    // 顾客到达时间
    private long arriveTime;
    // 下一顾客到达最短时间
    private int cusMinTime = 0;
    // 下一顾客到达最大时间
    private int cusMaxTime = 1*1000;
    // 顾客到来的概率
    private double rate = 0.8;
    // 是否继续产生顾客
    private boolean status = true;
    // 顾客队列
    private LinkedList<Customer> customers = new LinkedList<Customer>();

    public Customer() {
        // 顾客到达时间
        arriveTime = System.currentTimeMillis();
    }

    // 获取队列头部的顾客
    public synchronized Customer getCustomer() {
        if (customers == null || customers.size() <1) {
            return null;
        }
        return customers.removeFirst();
    }

    public void close() {
        if (status) {
            status = false;
        }
    }

    // 生成顾客线程
    private class CustomerThread extends Thread {
        private CustomerThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (status) {

            }
        }
    }
}
