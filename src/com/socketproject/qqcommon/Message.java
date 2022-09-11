package com.socketproject.qqcommon;

import java.io.Serializable;

/**
 * 项目名：    QQServer
 * 文件名：    Message
 * 创建时间：   2022/9/8 15:29
 *
 * @author crazy Chen
 * 描述：   表示客户端和服务器端通讯时的消息对象   TODO
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    private String sender; //发送者
    private String receiver;//接收者
    private String content;//内容


    private String mesType;

    private byte[] fileBiBytes;
    private int fileLen = 0;
    private String src;
    private String dest;

    public byte[] getFileBiBytes() {
        return fileBiBytes;
    }

    public void setFileBiBytes(byte[] fileBiBytes) {
        this.fileBiBytes = fileBiBytes;
    }

    public int getFileLen() {
        return fileLen;
    }

    public void setFileLen(int fileLen) {
        this.fileLen = fileLen;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getSender() {
        return sender;
    }

    public String getMesType() {
        return mesType;
    }

    public void setMesType(String mesType) {
        this.mesType = mesType;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    private String sendTime;//发送时间
}
