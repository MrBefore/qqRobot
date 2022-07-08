package com.robot.qq.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


/**
 * 状态枚举
 *
 * @author wangzk
 */
@NoArgsConstructor
@AllArgsConstructor
public enum StatusEnum {

    //状态_在线	10
    STATUS_ONLINE(10),

    //状态_离开	30
    STATUS_LEAVE(30),

    //状态_隐身	40
    STATUS_INVISIBLE(40),

    //状态_忙碌	50
    STATUS_BUSY(50),

    //状态_Q我吧	60
    STATUS_QME(60),

    //状态_勿扰	70
    STATUS_NOT_DISTURB(70),


    ;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
