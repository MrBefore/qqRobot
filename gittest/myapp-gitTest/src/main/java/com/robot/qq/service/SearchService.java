package com.robot.qq.service;

import com.robot.qq.entity.CallbackMsg;

/**
 * @author user
 */
public interface SearchService {
    /**
     * 发送图片
     *
     * @param message 回调信息
     */
    void search(CallbackMsg message);
}
