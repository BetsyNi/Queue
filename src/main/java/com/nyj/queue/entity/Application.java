package com.nyj.queue.entity;

import com.alibaba.fastjson.JSON;
import com.nyj.queue.util.MathUtils;
import com.nyj.queue.util.RandomUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 体检排队系统启动类
 *
 * @author: Xinling Jing
 * @date: 2019/4/20 0020 06:45
 * @description:
 */
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws IOException {
        // 加载所有体检项目列表(体检窗口列表)
        List<PhyExam> phyExams = LoadConfigSingleton.getSingleton().phyExams;
        logger.info("加载体检项目列表:{}", JSON.toJSONString(phyExams));

        // 造一批人
        // TODO-JING 后续此处可更改为从配置文件读入或从键盘读入
        List<Person> persons = LoadConfigSingleton.getSingleton().persons;
        // 按到达时间排序
        persons.sort(Comparator.comparingInt(o -> RandomUtils.getTime(o.getArriveTime())));
        for (Person person : persons) {
            System.out.println(RandomUtils.getTime(person.getArriveTime()));
        }
        logger.info("加载体检人列表:{}", JSON.toJSONString(persons));

        logger.info("最早到达的人:{}", JSON.toJSONString(MathUtils.earliestArrive(persons)));

        //// 所有体检窗口
        // TODO-JING 后续单独提取出来
        Map<Integer, Deque<Person>> phyExamQueues = new HashMap<>(phyExams.size());
        // 为每个体检窗口创建一个队列
        for (PhyExam phyExam : phyExams) {
            Deque<Person> phyExamQueue = new LinkedList<>();
            phyExamQueues.put(phyExam.getNo(), phyExamQueue);
        }

        int currTime = 0;
        while (persons.size() > 0) {
            Person person = MathUtils.earliestArrive(persons);
            if (RandomUtils.getTime(person.getArriveTime()) != currTime) {
                currTime+= Constant.THOUSAND;
                continue;
            }
            int min = Integer.MAX_VALUE;
            int no = -1;
            int expectTime = 0;
            for (int phyExamNo : person.getPhyExamNos()) {
                Deque<Person> tmpQueue = phyExamQueues.get(phyExamNo);
                if (tmpQueue.size() > 0) {
                    Person lastPerson = tmpQueue.peekLast();
                    int lastPersonOutTime = RandomUtils.getTime(lastPerson.getOutTime()) - currTime;
                    if (min > lastPersonOutTime) {
                        min = lastPersonOutTime;
                        no = phyExamNo;
                        expectTime = lastPersonOutTime - currTime;
                    }
                } else {
                    min = 0;
                    no = phyExamNo;
                    break;
                }
            }
            person.setArriveTime(currTime + );
        }
        //// 开始排队
        //for (Person person : persons) {
        //    // 当前人所选购的体检套餐
        //    List<Integer> phyExamCombo = person.getPhyExamNos();
        //    int min = Integer.MAX_VALUE;
        //    int no = -1;
        //    for (Integer phyExamNo : phyExamCombo) {
        //        // 根据体检项目编号获取该体检项目下队列
        //        Deque<Person> somePerson = phyExamQueues.get(phyExamNo);
        //        int expectedTime = somePerson.size() * phyExams.get(phyExamNo).getMaxTime();
        //        if (expectedTime < min) {
        //            min = expectedTime;
        //            no = phyExamNo;
        //        }
        //    }
        //    Deque<Person> tmp = phyExamQueues.get(no);
        //    tmp.add(person);
        //    phyExamQueues.put(no, tmp);
        //}
        //System.out.println("Queue:" + JSON.toJSONString(phyExamQueues));
    }
}
