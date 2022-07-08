package com.robot.qq.service.impl;

import com.robot.qq.common.TemplateUtil;
import com.robot.qq.entity.CallbackMsg;
import com.robot.qq.enums.MsgTypeEnum;
import com.robot.qq.service.SendToGroupService;
import com.robot.qq.util.StringUseUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 给单个用户发消息具体实现
 *
 * @author wangzk
 */
@Service
public class SendToGroupServiceImpl implements SendToGroupService {

    private final TemplateUtil xsTemplate;

    public SendToGroupServiceImpl(TemplateUtil xsTemplate) {
        this.xsTemplate = xsTemplate;
    }

    /**
     * 给单个群发文本消息
     *
     * @param message CallbackMsg
     * @param msg     String
     */
    @Override
    public void sendMsgToGroup(CallbackMsg message, String msg, String groupId) {
        Map<String, Object> dataResult;
        dataResult = xsTemplate.sendMsg(message.getMqRobot(),
                MsgTypeEnum.MSG_TYPE_GROUP.getCode(),
                groupId, null, msg);
    }

    /**
     * 给单个群发图片
     *
     * @param message CallbackMsg
     * @param fileUrl String
     */
    @Override
    public void sendPhotoToGroup(CallbackMsg message, String fileUrl, String groupId) {
        Map<String, Object> dataResult;
        dataResult = xsTemplate.sendPhoto(message.getMqRobot(),
                MsgTypeEnum.MSG_TYPE_GROUP.getCode(),
                groupId, fileUrl);
        String photo = StringUseUtils.changeMapToString(dataResult);
        // 调用发消息
        sendMsgToGroup(message, photo, groupId);
    }
}
