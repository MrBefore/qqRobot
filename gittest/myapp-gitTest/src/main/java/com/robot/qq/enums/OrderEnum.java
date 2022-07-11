package com.robot.qq.enums;

/**
 * @author sunhao
 * @data 2022/7/11
 */
public enum OrderEnum {
    DISABLE_ALL("禁用全部"),
    ENABLE_ALL("启用全部");

    private String code;

    OrderEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
