package com.robot.qq.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 统一出参
 *
 * @author wangZq
 */
public class ReqResult {

    @JsonProperty("status")
    private Integer status;

    public ReqResult() {
    }

    public ReqResult(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
