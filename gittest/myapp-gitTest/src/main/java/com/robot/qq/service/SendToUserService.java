package com.robot.qq.service;

import com.robot.qq.entity.CallbackMsg;

/**
 * 给单个用户发消息
 *
 * @author wangzk
 */
public interface SendToUserService {
    /**
     * 给单个用户发文本消息
     *
     * @param message CallbackMsg
     * @param msg     String
     */
    void sendMsgToUser(CallbackMsg message, String msg);

    /**
     * 给单个用户发图片
     *
     * @param message CallbackMsg
     * @param fileUrl String
     */
    void sendPhotoToUser(CallbackMsg message, String fileUrl);
}
