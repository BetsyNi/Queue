package com.nyj.queue.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
     * 体检人进入某一队列时间点
     */
    private String arriveTime;
    /**
     * 体检人退出某一队列时间点
     */
    private String outTime;
    /**
     * 病人所需要体检的项目列表(体检套餐)
     */
    private List<Integer> phyExamNos;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            return ((Person) obj).getNo().equals(this.getNo());
        }
        return false;
    }
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        List<Integer> nos = new ArrayList<>();
        nos.add(1);
        nos.add(2);
        nos.add(3);
        Person p = new Person(1, "1", "2", nos);
        persons.add(p);
        System.out.println(persons.indexOf(p));
    }
}
