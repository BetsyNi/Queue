package com.nyj.queue.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 体检项目
 *
 * @author: Xinling Jing
 * @date: 2019/4/20 0020 06:36
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhyExam {

    /**
     * 体检项目编号(窗口编号)
     */
    private Integer no;
    /**
     * 体检项目名称
     */
    private String name;
    /**
     * 所需花费最少时间
     */
    private Integer minTime;
    /**
     * 所需花费最多时间
     */
    private Integer maxTime;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PhyExam) {
            return ((PhyExam) obj).getNo().equals(this.getNo());
        }
        return false;
    }
}
