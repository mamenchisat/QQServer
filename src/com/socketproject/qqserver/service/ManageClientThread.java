package com.socketproject.qqserver.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 项目名：    QQServer
 * 文件名：    ManageClientThread
 * 创建时间：   2022/9/8 21:21
 *
 * @author crazy Chen
 * 描述：      TODO
 */
public class ManageClientThread {
    private static HashMap<String, ServerConnectClientThread> hashMap = new HashMap<>();

    //添加线程对象到hm集合
    public static void addClientThread(String userId, ServerConnectClientThread serverConnectClientThread) {
        hashMap.put(userId, serverConnectClientThread);
    }

    //根据userId返回
    public static ServerConnectClientThread getServerClientThread(String userId) {
        return hashMap.get(userId);
    }

    //这里编写方法，可以返回在线用户列表
    public static String getOnLineUser() {
        String onLineUser = "";
        for (String key : hashMap.keySet()) {
            onLineUser += key + " ";
        }
        return onLineUser;
    }
    public static List<String> getOnLineUserList() {
        List<String> onlineUser = new ArrayList<String>();
        for (String key : hashMap.keySet()) {
            onlineUser.add(key);
        }
        return onlineUser;
    }
}
