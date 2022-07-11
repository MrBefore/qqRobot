package com.robot.qq.service.impl;

import com.robot.qq.annotation.Controllable;
import com.robot.qq.controller.SendMsgController;
import com.robot.qq.entity.CallbackMsg;
import com.robot.qq.entity.ReqResult;
import com.robot.qq.enums.MsgTypeEnum;
import com.robot.qq.enums.OrderEnum;
import com.robot.qq.service.ManagementService;
import com.robot.qq.service.SendToGroupService;
import com.robot.qq.service.SendToUserService;
import com.robot.qq.util.SpringBeanUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author sunhao
 * @data 2022/7/11
 */
@Service
public class ManagementServiceImpl implements ManagementService {
    private final String REGEX_CONTROL = "指令：.+";
    private final String REGEX_CONTROL_PRE = "指令：";
    @Autowired
    private SendToUserService toUserService;

    @Autowired
    private SendToGroupService toGroupService;
    /**
     * 控制功能启用与禁用
     *
     * @param message：CallbackMsg
     */
    @Override
    public ReqResult control(CallbackMsg message) {
        if (Pattern.matches(REGEX_CONTROL, message.getMqMsg())){
            String order = message.getMqMsg().replaceAll(REGEX_CONTROL_PRE, "");
            if (order.equals(OrderEnum.DISABLE_ALL.getCode())){
                disable();
                return this.getReqResult(message, "禁用成功");
            }else if (order.equals(OrderEnum.ENABLE_ALL.getCode())){
                enable();
                return this.getReqResult(message, "启用");
            }
        }
        return new ReqResult(1);
    }

    /**
     * 禁用所有功能：禁用所有类及其内的方法
     *
     */
    @Override
    public void disable() {
        List<Object> beans = SpringBeanUtils.getBean();
        for (Object obj : beans) {
            Class<?> targetClass = AopUtils.getTargetClass(obj);
            // 将类注解设置为禁用
            Controllable annotation = targetClass.getAnnotation(Controllable.class);
            this.changeValue(annotation, 0);
            // 将类中的方法全部设置为禁用
            Method[] declaredMethods = targetClass.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                if (declaredMethod.isAnnotationPresent(Controllable.class)){
                    Controllable annotation1 = declaredMethod.getAnnotation(Controllable.class);
                    this.changeValue(annotation1, 0);
                }
            }
        }
    }

    /**
     * 启用所有功能
     */
    @Override
    public void enable() {
        List<Object> beans = SpringBeanUtils.getBean();
        for (Object obj : beans) {
            Class<?> targetClass = AopUtils.getTargetClass(obj);
            // 将类注解设置为禁用
            Controllable annotation = targetClass.getAnnotation(Controllable.class);
            this.changeValue(annotation, 1);
            // 将类中的方法全部设置为禁用
            Method[] declaredMethods = targetClass.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                if (declaredMethod.isAnnotationPresent(Controllable.class)){
                    Controllable annotation1 = declaredMethod.getAnnotation(Controllable.class);
                    this.changeValue(annotation1, 1);
                }
            }
        }
    }

    /**
     * 禁用除某方法或某类外的所有功能，当排除类时，参数形式为：MsgController.class
     * 当排除方法时，参数形式为：MsgController.class.getMethod("test", CallbackMsg.class, HttpServletRequest.class)
     * 当排除类时，实际上将跳过对该类中注解值（包括类注解合方法注解）的修改而使用其默认值
     * 当排除方法时，实际上将跳过对该方法的注解值的修改而使用其默认值
     *
     * @param aClass Class或Method对象
     */
    @Override
    public void disableExcept(GenericDeclaration aClass) {
        List<Object> beans = SpringBeanUtils.getBean();
        for (Object obj : beans) {
            Class<?> targetClass = AopUtils.getTargetClass(obj);
            // 将类注解设置为禁用
            Controllable annotation = targetClass.getAnnotation(Controllable.class);
            if (aClass instanceof Class<?> && aClass.equals(targetClass)){
                continue;
            }else {
                this.changeValue(annotation, 0);
            }
            // 将类中的方法全部设置为禁用
            Method[] declaredMethods = targetClass.getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                if (declaredMethod.isAnnotationPresent(Controllable.class)){
                    Controllable annotation1 = declaredMethod.getAnnotation(Controllable.class);
                    if (!(aClass instanceof Method) || !aClass.equals(declaredMethod)){
                        this.changeValue(annotation1, 0);
                    }
                }
            }
        }
    }


    /**
     * 修改注解值
     * @param proxy 获取到的注解
     * @param value 0或1：设置启用与禁用
     */
    private void changeValue(Object proxy, int value){
        try {
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(proxy);
            Field declaredField = invocationHandler.getClass().getDeclaredField("memberValues");
            declaredField.setAccessible(true);
            Map memberValues = (Map) declaredField.get(invocationHandler);
            memberValues.put("value", value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
}
