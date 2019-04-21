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
     * 体检人到达医院的时间
     */
    private String arriveTime;
    /**
     * 病人所需要体检的项目列表(体检套餐)
     */
    private List<Integer> phyExamNos;

}
