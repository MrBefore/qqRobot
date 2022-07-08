package com.robot.qq.service;

import com.robot.qq.entity.CallbackMsg;

/**
 * 给单个群发消息
 *
 * @author wangzk
 */
public interface SendToGroupService {
    /**
     * 给单个群发文本消息
     *
     * @param message CallbackMsg
     * @param msg     String
     * @param groupId String
     */
    void sendMsgToGroup(CallbackMsg message, String msg, String groupId);

    /**
     * 给单个群发图片
     *
     * @param message CallbackMsg
     * @param fileUrl String
     * @param groupId String
     */
    void sendPhotoToGroup(CallbackMsg message, String fileUrl, String groupId);
}
