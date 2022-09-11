package com.socketproject.qqserver.service;

import com.socketproject.qqcommon.Message;
import com.socketproject.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

/**
 * 项目名：    QQServer
 * 文件名：    ServerConnectClientThread
 * 创建时间：   2022/9/8 21:11
 *
 * @author crazy Chen
 * 描述：   该类的对象和某个客户端保持通讯，必须有个socket属性   TODO
 */
public class ServerConnectClientThread extends Thread {
    private Socket socket;
    private String userId;//连接到这个服务端的用户id

    public ServerConnectClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("服务器和客户端" + userId + "保持通讯，读取数据。。。");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
                //后面会使用message
                if (message.getMesType().equals(MessageType.MESSAGE_GET_ONLINE_FRIEND)) {
                    //客户端要在线用户列表
                    System.out.println("用户 " + message.getSender() + " 要在线用户列表");
                    String onLineUser = ManageClientThread.getOnLineUser();
                    //返回
                    Message message2 = new Message();
                    message2.setMesType(MessageType.MESSAGE_RET_ONLINE_FRIEND);
                    message2.setContent(onLineUser);
                    //写入到数据通道
                    ObjectOutputStream oos = new ObjectOutputStream(ManageClientThread.getServerClientThread(userId).socket.getOutputStream());
                    oos.writeObject(message2);

                } else if (message.getMesType().equals(MessageType.MESSAGE_CLINE_EXIT)) {
                    System.out.println(message.getSender() + " 要退出系统");
                    ManageClientThread.getServerClientThread(userId).socket.close();
                    ManageClientThread.addClientThread(userId, null);
                    break;
                } else if (message.getMesType().equals(MessageType.MESSAGE_COMM_MES)) {
                    if (message.getReceiver().equals("all")) {
                        List<String> onLineUserList = ManageClientThread.getOnLineUserList();
                        onLineUserList.remove(message.getSender());
                        for (String onLineUser : onLineUserList) {
                            ObjectOutputStream oos = new ObjectOutputStream(ManageClientThread.getServerClientThread(onLineUser).socket.getOutputStream());
                            oos.writeObject(message);
                        }

                    } else {
                        ObjectOutputStream oos = new ObjectOutputStream(ManageClientThread.getServerClientThread(message.getReceiver()).socket.getOutputStream());
                        oos.writeObject(message);
                    }


                } else if (message.getMesType().equals(MessageType.MESSAGE_FILE_MES)) {
                    OutputStream outputStream = ManageClientThread.getServerClientThread(message.getReceiver()).socket.getOutputStream();
                    new ObjectOutputStream(outputStream).writeObject(message);


                } else {
                    System.out.println("其它类型的数据，暂时不处理");
                }

            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
