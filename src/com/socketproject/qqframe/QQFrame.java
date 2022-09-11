package com.socketproject.qqframe;

import com.socketproject.qqserver.service.QQServer;

import java.io.IOException;

/**
 * 项目名：    QQServer
 * 文件名：    QQFrame
 * 创建时间：   2022/9/8 21:38
 *
 * @author crazy Chen
 * 描述：  启动后台的服务    TODO
 */
public class QQFrame {
    public static void main(String[] args) throws IOException {
        new QQServer();
    }
}
