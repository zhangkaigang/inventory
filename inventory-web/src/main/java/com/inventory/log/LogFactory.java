package com.inventory.log;

import com.inventory.po.system.BusinessLog;
import com.inventory.po.system.LoginLog;

/**
 * @Description:日志对象创建工厂
 * @Author:zhang.kaigang
 * @Date:2019/6/24
 * @Version:1.0
 */
public class LogFactory {

    /**
     * 创建业务日志对象
     * @param logName
     * @param userId
     * @param className
     * @param methodName
     * @param columnName
     * @param tableName
     * @param message
     * @return
     */
    public static BusinessLog createBusinessLog(String logName, Integer userId, String className, String methodName,
                                                String columnName, String tableName, String message, String detail){
        BusinessLog businessLog = new BusinessLog();
        businessLog.setLogName(logName);
        businessLog.setUserId(userId);
        businessLog.setClassName(className);
        businessLog.setMethodName(methodName);
        businessLog.setColumnName(columnName);
        businessLog.setTableName(tableName);
        businessLog.setMessage(message);
        businessLog.setDetail(detail);

        return businessLog;
    }

    /**
     * 创建登录日志对象
     * @param logName
     * @param loginName
     * @param ip
     * @return
     */
    public static LoginLog createLoginLog(String logName, String loginName, String ip){
        LoginLog loginLog = new LoginLog();
        loginLog.setLogName(logName);
        loginLog.setLoginName(loginName);
        loginLog.setIp(ip);

        return loginLog;
    }


}
