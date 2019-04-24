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

    public static void main(String[] args) throws IOException, InterruptedException {
        // 加载所有体检项目列表(体检窗口列表)
        List<PhyExam> phyExams = LoadConfigSingleton.getSingleton().phyExams;
        logger.info("加载体检项目列表:{}", JSON.toJSONString(phyExams));

        // 造一批人
        // TODO-JING 后续此处可更改为从配置文件读入或从键盘读入
        List<Person> persons = LoadConfigSingleton.getSingleton().persons;
        persons.sort(Comparator.comparingInt(o -> RandomUtils.getTime(o.getArriveTime())));
        logger.info("加载体检人列表:{}", JSON.toJSONString(persons));

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
            //Thread.sleep(300);
            for (Map.Entry<Integer, Deque<Person>> entry : phyExamQueues.entrySet()) {
                Person firstPerson = entry.getValue().peekFirst();
                if (firstPerson != null) {
                    String firstPersonOutTimeStr = firstPerson.getOutTime();
                    int firstPersonOutTime = RandomUtils.getTime(firstPersonOutTimeStr);
                    if (firstPersonOutTime == currTime) {
                        entry.getValue().pollFirst();
                    }
                }
            }
            Person person = MathUtils.earliestArrive(persons);
            if (RandomUtils.getTime(person.getArriveTime()) != currTime) {
                currTime += Constant.THOUSAND;
                continue;
            }
            int min = Integer.MAX_VALUE;
            int no = -1;
            // 预期排到该病人的时间
            int expectServerTime = 0;
            for (int phyExamNo : person.getPhyExamNos()) {
                Deque<Person> tmpQueue = phyExamQueues.get(phyExamNo);
                if (tmpQueue.size() > 0) {
                    Person lastPerson = tmpQueue.peekLast();
                    // 该队列最后一个人离开队列的时间点
                    int lastPersonOutTime = RandomUtils.getTime(lastPerson.getOutTime());
                    if (min > lastPersonOutTime) {
                        min = lastPersonOutTime;
                        no = phyExamNo;
                        // 如果选择排该队列
                        expectServerTime = lastPersonOutTime;
                    }
                } else {
                    no = phyExamNo;
                    expectServerTime = currTime;
                    break;
                }
            }
            PhyExam queryPhyExam = new PhyExam(no, null, null, null);
            String time =
                RandomUtils.getTimeForHuman(expectServerTime + phyExams.get(phyExams.indexOf(queryPhyExam)).getMaxTime() * 60 * Constant.THOUSAND);
            Person tmpPerson = new Person(person.getNo(), person.getArriveTime(), time, person.getPhyExamNos());
            person.setArriveTime(time);
            person.setOutTime(time);
            person.getPhyExamNos().remove((Integer) no);
            if (person.getPhyExamNos().size() == 0) {
                persons.remove(person);
            }
            Deque<Person> phyExamQueue = phyExamQueues.get(no);
            phyExamQueue.offerLast(tmpPerson);
            System.out.println();
            logger.info("{} {}号病人前来体检,加入{}体检队列,预计出队时间:{},剩余未体检项:{}", RandomUtils.getTimeForHuman(currTime),
                person.getNo(), no, person.getOutTime(), JSON.toJSONString(person.getPhyExamNos()));
            logger.info("{} 当前所有体检窗口队列情况:", RandomUtils.getTimeForHuman(currTime));
            for (Map.Entry<Integer, Deque<Person>> entry : phyExamQueues.entrySet()) {
                logger.info("{}", JSON.toJSONString(entry));
            }
            currTime += Constant.THOUSAND;
            logger.info("剩余病人:{}", JSON.toJSONString(persons));
        }
    }
}
