package com.socketproject.qqserver.service;

import com.socketproject.qqcommon.Message;
import com.socketproject.qqcommon.MessageType;
import com.socketproject.qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * 项目名：    QQServer
 * 文件名：    QQServer
 * 创建时间：   2022/9/8 20:56
 *
 * @author crazy Chen
 * 描述：   这个是服务器，在监听9999端口，等待客户端连接，并保持通讯   TODO
 */
public class QQServer {
    private ServerSocket serverSocket = null;

    //创建一个集合，存放多个用户，如果这些用户登录，就任务是合法的
    private static HashMap<String, User> validUsers = new HashMap<>();

    //在静态代码块初始化，类加载会自动运行
    static {
        validUsers.put("100", new User("100", "123456"));
        validUsers.put("200", new User("200", "123456"));
        validUsers.put("300", new User("300", "123456"));

    }

    //验证用户是否有效
    private boolean checkUser(String userId, String password) {
        User user = validUsers.get(userId);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }

    public QQServer() throws IOException {
        System.out.println("服务器在9999端口监听。。。");

        try {
            serverSocket = new ServerSocket(9999);
            while (true) {//监听是循环的，当和某个客户端连接后，会继续监听
                Socket socket = serverSocket.accept();
                //得到关联对象输入流
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                User u = (User) ois.readObject();
                //创建一个Message对象，准备回复客户端
                Message message = new Message();
                //得到对象输出流
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                //验证
                if (checkUser(u.getUserId(), u.getPassword())) {
                    message.setMesType(MessageType.MESSAGE_LOGIN_SUCCEEDED);
                    //将message对象回复
                    oos.writeObject(message);
                    oos.flush();
                    //创建一个线程和客户端保持通讯，该线程持有socket对象
                    ServerConnectClientThread serverConnectClientThread = new ServerConnectClientThread(socket, u.getUserId());
                    // 启动线程
                    serverConnectClientThread.start();
                    //把该线程对象放入一个集合中进行管理
                    ManageClientThread.addClientThread(u.getUserId(), serverConnectClientThread);

                } else {
                    System.out.println("用户" + u.getUserId() + "登录失败！");
                    message.setMesType(MessageType.MESSAGE_LOGIN_FAILED);
                    oos.writeObject(message);
                    socket.close();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            //如果服务端退出了while循环，说明不再监听
            serverSocket.close();
        }
    }
}
