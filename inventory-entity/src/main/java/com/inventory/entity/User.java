package com.inventory.entity;

/**
 * 用户类
 * Created by zkaigang on 2018/7/26.
 */
public class User {
    // 主键
    private int id;
    // 用户名
    private String userName;
    // 密码
    private String password;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
