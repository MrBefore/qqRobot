package com.robot.qq.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


/**
 * 回调实体
 *
 * @author wangZq
 */
@Data
public class CallbackMsg {
    /**
     * 用于判定哪个QQ接收到该消息
     */
    @JsonProperty("MQ_robot")
    private String mqRobot;

    /**
     * 接收到消息类型，该类型可在[常量列表]中查询具体定义
     */
    @JsonProperty("MQ_type")
    private Integer mqType;

    /**
     * 此参数在不同情况下，有不同的定义
     */
    @JsonProperty("MQ_type_sub")
    private Integer mqTypeSub;

    /**
     * 此消息的来源，如：群号、讨论组ID、临时会话QQ、好友QQ等
     */
    @JsonProperty("MQ_fromID")
    private String mqFromId;

    /**
     * 主动发送这条消息的QQ，踢人时为踢人管理员QQ
     */
    @JsonProperty("MQ_fromQQ")
    private String mqFromQ;

    /**
     * 被动触发的QQ，如某人被踢出群，则此参数为被踢出人QQ
     */
    @JsonProperty("MQ_passiveQQ")
    private String mqPassiveQ;

    /**
     * （此参数将被URL UTF8编码，您收到后需要解码处理）此参数有多重含义，常见为：对方发送的消息内容，但当消息类型为 某人申请入群，则为入群申请理由,当消息类型为收到财付通转账、收到群聊红包、收到私聊红包时为原始json，详情见[特殊消息]章节
     */
    @JsonProperty("MQ_msg")
    private String mqMsg;

    /**
     * 撤回别人或者机器人自己发的消息时需要用到
     */
    @JsonProperty("MQ_msgSeq")
    private String mqMsgSeq;

    /**
     * 撤回别人或者机器人自己发的消息时需要用到
     */
    @JsonProperty("MQ_msgID")
    private String mqMsgId;

    /**
     * UDP收到的原始信息，特殊情况下会返回JSON结构（入群事件时，这里为该事件data）
     */
    @JsonProperty("MQ_msgData")
    private String mqMsgData;

    /**
     * 对方发送该消息的时间戳，引用回复消息时需要用到
     */
    @JsonProperty("MQ_timestamp")
    private String mqTimestamp;

}
