package com.nyj.queue.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: Xinling Jing
 * @date: 2019/4/20 0020 19:55
 * @description:
 */
@Data
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private int status;
    private String msg;
}
