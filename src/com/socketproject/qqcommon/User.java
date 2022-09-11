package com.socketproject.qqcommon;

import java.io.Serializable;

/**
 * 项目名：    QQServer
 * 文件名：    User
 * 创建时间：   2022/9/8 15:29
 *
 * @author crazy Chen
 * 描述：  表示一个用户信息    TODO
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;//增加兼容性
    private String userId;
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
