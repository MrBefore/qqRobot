package com.robot.qq.controller;

import com.robot.qq.annotation.Controllable;
import com.robot.qq.entity.CallbackMsg;
import com.robot.qq.entity.ReqResult;
import com.robot.qq.enums.MsgTypeEnum;
import com.robot.qq.service.SendToGroupService;
import com.robot.qq.service.SendToUserService;
import com.robot.qq.util.GetCatUtils;
import com.robot.qq.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 发送图片-controller
 *
 * @author wangzk
 */

@RequestMapping("/sendImage")
@RestController
@Controllable
public class SendImageController {
    @Autowired
    private SendToUserService toUserService;

    @Autowired
    private SendToGroupService toGroupService;

    /**
     * 发送图片-公共方法
     *
     * @param message CallbackMsg 回调信息
     * @param fileUrl String 图片链接
     * @return ReqResult 统一出参
     */
    private ReqResult getReqResult(@RequestBody CallbackMsg message, String fileUrl) {
        // 群号
        String groupId = "934631369";
        // 测试群号
        String test = "240956911";
        if (message.getMqType().equals(MsgTypeEnum.MSG_TYPE_RRI_EDN.getCode()) || message.getMqType().equals(MsgTypeEnum.MSG_TYPE_GROUP.getCode())) {
            if (message.getMqType().equals(MsgTypeEnum.MSG_TYPE_RRI_EDN.getCode())) {
                toUserService.sendPhotoToUser(message, fileUrl);
            }
            if (message.getMqType().equals(MsgTypeEnum.MSG_TYPE_GROUP.getCode())) {
                if (message.getMqFromId().equals(groupId)) {
                    toGroupService.sendPhotoToGroup(message, fileUrl, groupId);
                }
                if (message.getMqFromId().equals(test)) {
                    toGroupService.sendPhotoToGroup(message, fileUrl, test);
                }
            }
        }
        return new ReqResult(1);
    }

    /**
     * 摸鱼办图片
     *
     * @param message CallbackMsg 回调信息
     * @param request request请求
     * @return ReqResult 统一出参
     * @throws Exception Exception
     */
    @PostMapping("/moYuMan")
    public ReqResult moYuMan(@RequestBody CallbackMsg message, HttpServletRequest request) throws Exception {
        message.setMqMsg(URLDecoder.decode(message.getMqMsg(), "utf-8"));
        String mqMsg = message.getMqMsg();
        //消息类型为：好友或群聊时：
        String flag = "摸鱼人";
        boolean isMatch = flag.equals(mqMsg);
        if (isMatch) {
            String url = " https://api.j4u.ink/proxy/redirect/moyu/calendar/";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String today = sdf.format(new Date());
            url = url + today + ".png";
            String fileUrl = "D:/Photo/moYuMan/666.png";
            ImageUtil.saveImage(url, fileUrl);
            return getReqResult(message, fileUrl);
        } else {
            return new ReqResult(1);
        }
    }

    /**
     * 猫猫图片
     *
     * @param message CallbackMsg 回调信息
     * @param request request请求
     * @return ReqResult 统一出参
     * @throws Exception Exception
     */
    @PostMapping("/cat")
    public ReqResult cat(@RequestBody CallbackMsg message, HttpServletRequest request) throws Exception {
        message.setMqMsg(URLDecoder.decode(message.getMqMsg(), "utf-8"));
        String mqMsg = message.getMqMsg();
        //消息类型为：好友或群聊时：
        String flag = ".*来点猫猫.*";
        boolean isMatch = Pattern.matches(flag, mqMsg);
        if (isMatch) {
            String fileUrl = GetCatUtils.getCat();
            return getReqResult(message, fileUrl);
        } else {
            return new ReqResult(1);
        }
    }


    /**
     * 表情包
     *
     * @param message CallbackMsg 回调信息
     * @param request request请求
     * @return ReqResult 统一出参
     * @throws Exception Exception
     */
    @PostMapping("/emoticon")
    public ReqResult emoticon(@RequestBody CallbackMsg message, HttpServletRequest request) throws Exception {
        message.setMqMsg(URLDecoder.decode(message.getMqMsg(), "utf-8"));
        String mqMsg = message.getMqMsg();
        //消息类型为：好友或群聊时：
        String flag = "来个表情包";
        boolean isMatch = flag.equals(mqMsg);
        if (isMatch) {
            String fileUrl = GetCatUtils.getEmoCation();
            return getReqResult(message, fileUrl);
        } else {
            return new ReqResult(1);
        }

    }

    /**
     * 星期四
     *
     * @param message CallbackMsg 回调信息
     * @param request request请求
     * @return ReqResult 统一出参
     * @throws Exception Exception
     */
    @PostMapping("/thursday")
    public ReqResult thursday(@RequestBody CallbackMsg message, HttpServletRequest request) throws Exception {
        message.setMqMsg(URLDecoder.decode(message.getMqMsg(), "utf-8"));
        String mqMsg = message.getMqMsg();
        //消息类型为：好友或群聊时：
        String flag = "v我50";
        String flag2 = "V我50";
        boolean isMatch = flag.equals(mqMsg) || flag2.equals(mqMsg);
        if (isMatch) {
            String fileUrl = GetCatUtils.getThursday();
            return getReqResult(message, fileUrl);
        } else {
            return new ReqResult(1);
        }

    }

}
