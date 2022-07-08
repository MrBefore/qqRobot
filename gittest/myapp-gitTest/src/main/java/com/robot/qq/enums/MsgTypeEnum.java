package com.robot.qq.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


/**
 * 消息类型枚举
 *
 * @author wangzk
 */
@NoArgsConstructor
@AllArgsConstructor
public enum MsgTypeEnum {

    //消息类型_未定义	-1
    MSG_TYPE_NO_DEFINE(-1),

    //消息类型_好友	1
    MSG_TYPE_RRI_EDN(1),

    //消息类型_群	2
    MSG_TYPE_GROUP(2),

    //消息类型_讨论组	3
    MSG_TYPE_DISCUSS(3),

    //消息类型_群临时会话	4
    MSG_TYPE_GROUP_TEMP(4),

    //消息类型_讨论组临时会话	5
    MSG_TYPE_DISCUSS_TEMP(5),

    //消息类型_在线临时会话	6
    MSG_TYPE_ONLINE_TEMP(6),

    //消息类型_被单向添加好友	1000
    MSG_TYPE_BE_ADD_FRIEND_SINGLE(1000),

    //消息类型_被请求添加好友	1001
    MSG_TYPE_BE_ADD_FRIEND(1001),

    //消息类型_好友状态改变	1002
    MSG_TYPE_FRIEND_STATUS_CHANGE(1002),

    //消息类型_被删除好友	1003
    MSG_TYPE_BE_DEL_FRIEND(1003),

    //消息类型_好友签名变更	1004
    MSG_TYPE_FRIEND_SIGN_CHANGE(1004),

    //消息类型_说说被评论	1005
    MSG_TYPE_POST_BE_COMMENT(1005),

    //消息类型_好友正在输入	1006
    MSG_TYPE_FRIEND_INPUT_ING(1006),

    //消息类型_好友今天首次发起会话	1007
    MSG_TYPE_FRIEND_FRIEND_CHAT(1007),

    //消息类型_被好友抖动	1008
    MSG_TYPE_BE_SHIVER(1008),

    //消息类型_好友文件接收	1009
    MSG_TYPE_FRIEND_FILE_RECEIVE(1009),

    //消息类型_好友视频接收	1010
    MSG_TYPE_FRIEND_VIDEO_RECEIVE(1010),

    //消息类型_某人申请加入群	2001
    MSG_TYPE_BE_INVITE_TO_GROUP(2001),

    //消息类型_某人被邀请加入群	2002
    MSG_TYPE_ONE_BE_INVITE_GROUP(2002),

    //消息类型_某人已被邀请入群	20021
    MSG_TYPE_HAS_BE_INVITE_GROUP(20021),

    //消息类型_我被邀请加入群	2003
    MSG_TYPE_ME_BE_INVITE_GROUP(2003),

    //消息类型_某人被批准加入了群	2005
    MSG_TYPE_ONE_BE_AGREE_GROUP(2005),

    //消息类型_某人退出群	2006
    MSG_TYPE_ONE_QUIT_GROUP(2006),

    //消息类型_某人被管理移除群	2007
    MSG_TYPE_ONE_BE_QUIT(2007),

    //消息类型_某群被解散	2008
    MSG_TYPE_GROUP_BE_QUI(2008),

    //消息类型_某人成为管理	2009
    MSG_TYPE_ONE_BECOME_MANAGER(2009),

    //消息类型_某人被取消管理	2010
    MSG_TYPE_ONE_BE_CANCEL_MANAGER(2010),

    //消息类型_群名片变动	2011
    MSG_TYPE_GROUP_CARD_CHANGE(2011),

    //消息类型_群名变动	2012
    MSG_TYPE_GROUP_NICK_CHANGE(2012),

    //消息类型_群公告变动	2013
    MSG_TYPE_GROUP_NOTICE_CHANGE(2013),

    //消息类型_对象被禁言	2014
    MSG_TYPE_ONE_BE_BAN(2014),

    //消息类型_对象被解除禁言	2015
    MSG_TYPE_ONE_QUIT_BE_BAN(2015),

    //消息类型_群管开启全群禁言	2016
    MSG_TYPE_OPEN_GROUP_ALL_BAN(2016),

    //消息类型_群管关闭全群禁言	2017
    MSG_TYPE_CLOSE_GROUP_ALL_BAN(2017),

    //消息类型_群管开启匿名聊天	2018
    MSG_TYPE_OPEN_ANON_CHAT(2018),

    //消息类型_群管关闭匿名聊天	2019
    MSG_TYPE_CLOSE_ANON_CHAT(2019),

    //消息类型_群撤回消息	2020
    MSG_TYPE_GROUP_RECALL_MSG(2020),

    //消息类型_群文件接收	2021
    MSG_TYPE_GROUP_FILE_RECEIVE(2021),

    //消息类型_群视频接收	2022
    MSG_TYPE_GROUP_VIDEO_RECEIVE(2022),

    //消息类型_好友语音接收	3001
    MSG_TYPE_FRIEND_VOICE_RECEIVE(3001),

    //消息类型_群语音接收	3002
    MSG_TYPE_GROUP_VOICE_RECEIVE(3002),

    //消息类型_框架即将重启	10001
    MSG_TYPE_FRAME_WILL_REBOOT(10001),

    //消息类型_框架加载完成	10000
    MSG_TYPE_FRAME_LOAD_OK(10000),

    //消息类型_添加了一个新的帐号	11000
    MSG_TYPE_FRAME_ADD_NEW_QQ(11000),

    //消息类型_QQ登录完成	11001
    MSG_TYPE_FRAME_QQ_LOGIN_OK(11001),

    //消息类型_QQ被手动离线	11002
    MSG_TYPE_FRAME_QQ_LEAVE_HAND(11002),

    //消息类型_QQ被强制离线	11003
    MSG_TYPE_FRAME_QQ_FORCE_LEAVE(11003),

    //消息类型_QQ长时间无响应或掉线	11004
    MSG_TYPE_QQ_LONG_NO_RESPONSE(11004),

    //消息类型_本插件载入	12000
    MSG_TYPE_PLUGIN_LOAD(12000),

    //消息类型_插件被启用	12001
    MSG_TYPE_PLUGIN_BE_QI(12001),

    //消息类型_插件被禁用	12002
    MSG_TYPE_PLUGIN_BE_BAN(12002),

    //消息类型_插件被点击	12003
    MSG_TYPE_PLUGIN_BE_CLICK(12003),

    //消息类型_收到财付通转账	80001
    MSG_TYPE_RECEIVE_ACCOUNT(80001),

    ;
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
