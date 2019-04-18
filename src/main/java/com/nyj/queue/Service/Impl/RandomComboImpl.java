package com.nyj.queue.Service.Impl;

import com.nyj.queue.Service.RandomCombo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author : Ni Yujia
 * @date : 2019/4/15
 */
public class RandomComboImpl implements RandomCombo {
    public void randomCombo(int n) {

        HashMap<Integer, HashMap<String, String>> combo = new HashMap<Integer, HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();

        int random = (int) (Math.random() * 1000);
        if (random % 3 == 0) {
//            System.out.println("套餐 1");
            map.put("EYE","眼科");
            map.put("EAR","耳科");
            map.put("MOUTH","口腔科");
            combo.put(n, map);
        }
        if (random % 3 == 1) {
//            System.out.println("套餐 2");
            map.put("EYE","眼科");
            map.put("ECG","心电图");
            map.put("BLOOD","血常规");
            map.put("LIVER","肝功能");
            combo.put(n, map);
        }
        if (random % 3 == 2) {
//            System.out.println("套餐 3");
            map.put("EYE","眼科");
            map.put("EAR","耳科");
            map.put("MOUTH","口腔科");
            map.put("ECG","心电图");
            map.put("BLOOD","血常规");
            map.put("LIVER","肝功能");
            map.put("BSCAN","肝功能");
            combo.put(n, map);
        }

        for (Integer key1: combo.keySet()) {
            System.out.println(key1 + ":" + combo.get(key1));
            for (String key2: map.keySet()) {
                System.out.println(key2 + ":" + map.get(key2));
            }
        }

    }


    public static void main(String[] args) {
        RandomComboImpl randomCombo = new RandomComboImpl();
        for (int i= 0; i < 3; i++) {
            randomCombo.randomCombo(i);
            System.out.println("***************************");
        }
    }
}
