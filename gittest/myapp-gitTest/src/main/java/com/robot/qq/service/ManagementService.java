package com.robot.qq.service;
import com.robot.qq.entity.CallbackMsg;
import com.robot.qq.entity.ReqResult;

import java.lang.reflect.GenericDeclaration;

public interface ManagementService {
    /**
     * 禁用所有功能
     */
    void disable();

    /**
     * 启用所有功能
     */
    void enable();

    /**
     * 禁用除指定类或方法外的所有功能
     * @param aClass Class或Method对象
     */
    void disableExcept(GenericDeclaration aClass);

    /**
     * 控制功能启用与禁用
     * @param message
     */
    ReqResult control(CallbackMsg message);
}
