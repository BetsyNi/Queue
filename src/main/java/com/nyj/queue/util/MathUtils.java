package com.nyj.queue.util;

import com.nyj.queue.entity.Person;

import java.util.List;

/**
 * @Author: Xinling Jing
 * @Date: 2019-04-24 20:51
 */
public class MathUtils {

    public static Person earliestArrive(List<Person> persons) {
        int earliestArriveTime = Integer.MAX_VALUE;
        Person person = null;
        for (Person tmpPerson : persons) {
            int tmpTime = RandomUtils.getTime(tmpPerson.getArriveTime());
            if (earliestArriveTime > tmpTime) {
                earliestArriveTime = tmpTime;
                person = tmpPerson;
            }
        }
        return person;
    }

}
