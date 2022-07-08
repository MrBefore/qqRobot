package com.robot.qq.common;

import com.robot.qq.entity.DataResult;
import com.robot.qq.entity.PushMessage;
import com.robot.qq.util.HttpApiUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 调用模板工具类
 *
 * @author wangzk
 */
@Component
public final class TemplateUtil {
    @Value("${robot.host}")
    private String host;
    @Value("${robot.port}")
    private String port;
    @Value("${robot.token}")
    private String token;


    /**
     * 通用发消息-Get
     *
     * @param function String 要调用的函数英文名
     * @param params   Map 参数列表
     * @return DataResult 出参
     */
    public DataResult getUtils(String function, Map<String, Object> params) {
        return HttpApiUtil.sendGet(host, port, token, function, params);
    }

    /**
     * 通用发消息-Post
     *
     * @param function String 要调用的函数英文名
     * @param params   Map 参数列表
     * @return DataResult 出参
     */
    public Map<String, Object> postUtils(String function, Map<String, Object> params) {
        return HttpApiUtil.sendPost(host, port, new PushMessage(function, token, params));
    }

    /**
     * 记录日志到框架
     *
     * @param info
     * @return
     */
    public DataResult logInfo(String info) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("c1", info);
        return HttpApiUtil.sendGet(host, port, token, "Api_OutPut", params);
    }

    /**
     * Post封装参数
     *
     * @param robotQ  要使用的机器人QQ
     * @param type    消息类型
     * @param group   要发送的群号
     * @param targetQ 要发送的QQ，如果发的是群，要置空
     * @param msg     要发送的消息内容
     * @return Map 参数列表
     */
    public Map<String, Object> sendMsg(String robotQ, int type, String group, String targetQ, String msg) {
        Map<String, Object> params = new LinkedHashMap<>(5);
        params.put("c1", robotQ);
        params.put("c2", type);
        params.put("c3", group);
        params.put("c4", targetQ);
        params.put("c5", msg);
        PushMessage pushMessage = new PushMessage("Api_SendMsg", token, params);

        return HttpApiUtil.sendPost(host, port, pushMessage);
    }

    /**
     * Post封装参数-匿名
     *
     * @param robotQ    要使用的机器人QQ
     * @param anonymous 匿名
     * @param type      消息类型
     * @param group     要发送的群号
     * @param targetQ   要发送的QQ，如果发的是群，要置空
     * @param msg       要发送的消息内容
     * @return Map 参数列表
     */
    public Map<String, Object> sendMsgEx(String robotQ, int anonymous, int type, String group, String targetQ, String msg) {
        Map<String, Object> params = new LinkedHashMap<>(7);
        params.put("c1", robotQ);
        params.put("c2", anonymous);
        params.put("c3", type);
        params.put("c4", group);
        params.put("c5", targetQ);
        params.put("c6", msg);
        params.put("c7", -1);
        PushMessage pushMessage = new PushMessage("Api_SendMsgEx", token, params);

        return HttpApiUtil.sendPost(host, port, pushMessage);
    }


    /**
     * Post封装参数
     *
     * @param robotQ 要使用的机器人QQ
     * @param type   消息类型
     * @param group  要发送的群号或QQ
     * @param url    要发送的图片路径
     * @return Map 参数列表
     */
    public Map<String, Object> sendPhoto(String robotQ, int type, String group, String url) {
        Map<String, Object> params = new LinkedHashMap<>(5);
        params.put("c1", robotQ);
        params.put("c2", type);
        params.put("c3", group);
        params.put("c4", url);
        PushMessage pushMessage = new PushMessage("Api_UpLoadPic", token, params);

        return HttpApiUtil.sendPost(host, port, pushMessage);
    }

}
