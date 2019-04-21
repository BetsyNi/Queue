package com.nyj.queue.entity;

import com.alibaba.fastjson.JSON;
import com.nyj.queue.util.RandomUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

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
        persons.sort(Comparator.comparingInt(o -> RandomUtils.getTime(o.getArriveTime())));
        logger.info("加载体检人列表:{}", JSON.toJSONString(persons));

        // 为每个体检窗口创建一个队列
        for (PhyExam phyExam : phyExams) {
            Deque<Person> queue = new LinkedList<>();
        }
    }
}
