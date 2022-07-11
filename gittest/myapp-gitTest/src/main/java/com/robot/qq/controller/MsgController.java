package com.robot.qq.controller;

import com.robot.qq.annotation.Controllable;
import com.robot.qq.common.TemplateUtil;
import com.robot.qq.entity.CallbackMsg;
import com.robot.qq.entity.ReqResult;
import com.robot.qq.enums.MsgTypeEnum;
import com.robot.qq.enums.TagEnum;
import com.robot.qq.util.SpringBeanUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试用-controller
 *
 * @author wangzk
 */
@RequestMapping("/msg")
@RestController
@Controllable(tag = TagEnum.BASE_FUNCTION) // 默认启用
public class MsgController {

    private final TemplateUtil xsTemplate;

    public MsgController(TemplateUtil xsTemplate) {
        this.xsTemplate = xsTemplate;
    }

    @PostMapping
    @Controllable
    public ReqResult newMsg(@RequestBody CallbackMsg message, HttpServletRequest request) throws Exception {
        message.setMqMsg(URLDecoder.decode(message.getMqMsg(), "UTF-8"));
        String mqMsg = message.getMqMsg();
        String flag = "获取好友列表";
        if (message.getMqType().equals(MsgTypeEnum.MSG_TYPE_RRI_EDN.getCode()) && flag.equals(mqMsg)) {
            //发送好友消息
            Map<String, Object> dataResult = xsTemplate.sendMsgEx(message.getMqRobot(),
                    0, MsgTypeEnum.MSG_TYPE_RRI_EDN.getCode(),
                    null, message.getMqFromQ(), "SEND:" + mqMsg);
            if (flag.equals(mqMsg)) {
                //获取好友列表
                Map<String, Object> map = new HashMap<>(10);
                map.put("c1", message.getMqRobot());
                Map<String, Object> friendsMap = xsTemplate.postUtils("Api_GetFriendList", map);
                System.out.println(friendsMap);
            }
        }
        return new ReqResult(1);
    }

    @GetMapping("/hello")
    @Controllable
    public ReqResult hello(CallbackMsg message, HttpServletRequest request){
        System.out.println("哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
        return new ReqResult(1);
    }


    @GetMapping("/world")
    @Controllable
    public ReqResult world(CallbackMsg message, HttpServletRequest request){
        System.out.println("呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵");
        return new ReqResult(1);
    }


}
