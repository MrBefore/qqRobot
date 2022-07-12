package com.robot.qq.controller;

import com.robot.qq.annotation.Controllable;
import com.robot.qq.entity.CallbackMsg;
import com.robot.qq.entity.ReqResult;
import com.robot.qq.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * @author sunhao
 * @data 2022/7/11
 * 用于管理机器人相关功能
 */
@RestController
@RequestMapping("/management")
public class ManagementController {
    @Autowired
    ManagementService managementService;

    @PostMapping("/control")
    public ReqResult searchImage(@RequestBody CallbackMsg message, HttpServletRequest request) throws Exception {
        message.setMqMsg(URLDecoder.decode(message.getMqMsg(), "utf-8"));
        return managementService.control(message);
    }

}
