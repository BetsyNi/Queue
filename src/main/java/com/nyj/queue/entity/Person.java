package com.nyj.queue.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
     * 体检人到达时间
     */
    private String arriveTime;
    /**
     * 体检人退出队列时间
     */
    private String outTime;
    /**
     * 病人所需要体检的项目列表(体检套餐)
     */
    private List<Integer> phyExamNos;

}
