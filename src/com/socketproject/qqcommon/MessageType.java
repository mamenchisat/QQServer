package com.socketproject.qqcommon;

/**
 * 项目名：    QQServer
 * 文件名：    MessageType
 * 创建时间：   2022/9/8 15:36
 *
 * @author crazy Chen
 * 描述：  消息类型    TODO
 */
public interface MessageType {
    String MESSAGE_LOGIN_SUCCEEDED = "1";//登录成功
    String MESSAGE_LOGIN_FAILED = "2";//登录失败
    String MESSAGE_COMM_MES = "3";//普通信息包
    String MESSAGE_GET_ONLINE_FRIEND = "4";//要求返回在线用户列表
    String MESSAGE_RET_ONLINE_FRIEND = "5";//返回的用户列表
    String MESSAGE_CLINE_EXIT = "6";//客户端请求退出
    String MESSAGE_FILE_MES = "7";
}
