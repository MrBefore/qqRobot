package com.robot.qq.controller;

import com.robot.qq.entity.CallbackMsg;
import com.robot.qq.entity.ReqResult;
import com.robot.qq.enums.MsgTypeEnum;
import com.robot.qq.service.SendToGroupService;
import com.robot.qq.service.SendToUserService;
import com.robot.qq.util.DiscUtils;
import com.robot.qq.util.FoodUtils;
import com.robot.qq.util.TextToImageUtil;
import com.robot.qq.util.VacationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.regex.Pattern;

/**
 * 发送文本消息-controller
 *
 * @author wangzk
 */

@RequestMapping("/sendMsg")
@RestController
public class SendMsgController {
    @Autowired
    private SendToUserService toUserService;

    @Autowired
    private SendToGroupService toGroupService;

    /**
     * 发送消息-公共方法
     *
     * @param message CallbackMsg 回调信息
     * @param msg     String 要发出的消息
     * @return ReqResult 统一出参
     */
    private ReqResult getReqResult(@RequestBody CallbackMsg message, String msg) {
        // 群号
        String groupId = "934631369";
        // 测试群号
        String test = "240956911";
        if (message.getMqType().equals(MsgTypeEnum.MSG_TYPE_RRI_EDN.getCode()) || message.getMqType().equals(MsgTypeEnum.MSG_TYPE_GROUP.getCode())) {
            // 给单个用户发消息
            if (message.getMqType().equals(MsgTypeEnum.MSG_TYPE_RRI_EDN.getCode())) {
                toUserService.sendMsgToUser(message, msg);
            }
            // 给指定群发消息
            if (message.getMqType().equals(MsgTypeEnum.MSG_TYPE_GROUP.getCode())) {
                if (message.getMqFromId().equals(groupId)) {
                    toGroupService.sendMsgToGroup(message, msg, groupId);
                }
                if (message.getMqFromId().equals(test)) {
                    toGroupService.sendMsgToGroup(message, msg, test);
                }
            }
        }
        return new ReqResult(1);
    }

    /**
     * 骰子
     *
     * @param message CallbackMsg 回调信息
     * @param request request请求
     * @return ReqResult 统一出参
     * @throws Exception Exception
     */
    @PostMapping("/disc")
    public ReqResult disc(@RequestBody CallbackMsg message, HttpServletRequest request) throws Exception {
        message.setMqMsg(URLDecoder.decode(message.getMqMsg(), "utf-8"));
        String mqMsg = message.getMqMsg();
        //消息类型为：好友或群聊时：
        String flag = "Disc:.*";
        boolean isMatch = Pattern.matches(flag, mqMsg);
        if (isMatch) {
            String disc = DiscUtils.getOneDice(mqMsg);
            return getReqResult(message, disc);
        } else {
            return new ReqResult(1);
        }
    }

    /**
     * 摸鱼提示
     *
     * @param message CallbackMsg 回调信息
     * @param request request请求
     * @return ReqResult 统一出参
     * @throws Exception Exception
     */
    @PostMapping("/moYu")
    public ReqResult moYu(@RequestBody CallbackMsg message, HttpServletRequest request) throws Exception {
        message.setMqMsg(URLDecoder.decode(message.getMqMsg(), "utf-8"));
        String mqMsg = message.getMqMsg();
        //消息类型为：好友或群聊时：
        String flag = "摸鱼";
        String str = VacationUtils.calcEffectiveDateNoPhoto();
        boolean isMatch = flag.equals(mqMsg);
        if (isMatch) {
            return getReqResult(message, str);
        } else {
            return new ReqResult(1);
        }
    }

    /**
     * xx吃啥
     *
     * @param message CallbackMsg 回调信息
     * @param request request请求
     * @return ReqResult 统一出参
     * @throws Exception Exception
     */
    @PostMapping("/food")
    public ReqResult test(@RequestBody CallbackMsg message, HttpServletRequest request) throws Exception {
        message.setMqMsg(URLDecoder.decode(message.getMqMsg(), "utf-8"));
        String mqMsg = message.getMqMsg();
        //消息类型为：好友或群聊时：
        String flag = ".*吃啥";
        boolean isMatch = Pattern.matches(flag, mqMsg);
        if (isMatch) {
            String food = FoodUtils.getRandomFood();
            return getReqResult(message, food);
        } else {
            return new ReqResult(1);
        }
    }


}
