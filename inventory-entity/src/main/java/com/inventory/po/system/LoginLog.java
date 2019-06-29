package com.inventory.po.system;

/**
 * @Description:登录日志
 * @Author:zhang.kaigang
 * @Date:2019/6/29
 * @Version:1.0
 */
public class LoginLog {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 日志名称：登录日志、退出日志
     */
    private String logName;

    /**
     * 用户名称
     */
    private String loginName;

    /**
     * 登录ip
     */
    private String ip;

    /**
     * 登录时间
     */
    private String createDate;

    //--冗余
    private String createDateStart;
    private String createDateEnd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateDateStart() {
        return createDateStart;
    }

    public void setCreateDateStart(String createDateStart) {
        this.createDateStart = createDateStart;
    }

    public String getCreateDateEnd() {
        return createDateEnd;
    }

    public void setCreateDateEnd(String createDateEnd) {
        this.createDateEnd = createDateEnd;
    }
}
