package com.robot.qq.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


/**
 * 消息处理枚举
 *
 * @author wangzk
 */
@NoArgsConstructor
@AllArgsConstructor
public enum MsgDealEnum {

    //消息处理_不处理	0
    MSG_DEAL_NOT_DEAL(0),

    //消息处理_继续	1
    MSG_DEAL_CONTINUE(1),

    //消息处理_拦截	2
    MSG_DEAL_INTERCEPT(2),

    ;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
