package com.robot.qq.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


/**
 * 处理枚举
 *
 * @author wangzk
 */
@NoArgsConstructor
@AllArgsConstructor
public enum DealEnum {

    //处理_忽略或取消	0
    DEAL_IGNORE_CANCEL(0),

    //处理_同意或确定	10
    DEAL_AGREE_CONFIRM(10),

    //处理_拒绝	20
    DEAL_REFUSE(20),

    //处理_单向同意	10
    DEAL_AGREE_SINGLE(10),

    ;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
