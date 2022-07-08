package com.robot.qq.service.impl;

import com.robot.qq.common.TemplateUtil;
import com.robot.qq.entity.CallbackMsg;
import com.robot.qq.enums.MsgTypeEnum;
import com.robot.qq.service.SendToUserService;
import com.robot.qq.util.StringUseUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 给单个用户发消息具体实现
 *
 * @author wangzk
 */
@Service
public class SendToUserServiceImpl implements SendToUserService {

    private final TemplateUtil xsTemplate;

    public SendToUserServiceImpl(TemplateUtil xsTemplate) {
        this.xsTemplate = xsTemplate;
    }

    /**
     * 给单个用户发文本消息
     *
     * @param message CallbackMsg
     * @param msg     CallbackMsg
     */
    @Override
    public void sendMsgToUser(CallbackMsg message, String msg) {
        xsTemplate.sendMsg(message.getMqRobot(),
                MsgTypeEnum.MSG_TYPE_RRI_EDN.getCode(),
                null, message.getMqFromQ(), msg);
    }

    /**
     * 给单个用户发图片
     *
     * @param message CallbackMsg
     * @param fileUrl CallbackMsg
     */
    @Override
    public void sendPhotoToUser(CallbackMsg message, String fileUrl) {
        Map<String, Object> dataResult;
        dataResult = xsTemplate.sendPhoto(message.getMqRobot(),
                MsgTypeEnum.MSG_TYPE_RRI_EDN.getCode(),
                message.getMqFromQ(), fileUrl);
        String photo = StringUseUtils.changeMapToString(dataResult);
        // 调用发消息
        sendMsgToUser(message, photo);
    }
}
