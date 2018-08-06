package com.inventory.entity;

/**
 * 用户类
 * Created by zkaigang on 2018/7/26.
 */
public class User {
    // 主键
    private int id;
    // 登录名
    private String loginName;
    // 密码
    private String password;
    // 姓名
    private String name;
    // 创建时间
    private String createDate;
    // 删除状态
    private String deleteState;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(String deleteState) {
        this.deleteState = deleteState;
    }
}
