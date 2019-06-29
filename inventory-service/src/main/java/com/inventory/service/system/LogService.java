package com.inventory.service.system;

import com.github.pagehelper.PageInfo;
import com.inventory.po.system.BusinessLog;
import com.inventory.po.system.LoginLog;

import java.util.Map;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/6/26
 * @Version:1.0
 */
public interface LogService {

    /**
     * 查询业务日志，分页
     * @param pageMap
     * @return
     */
    PageInfo<BusinessLog> queryBusinessLogList(Map<String, Object> pageMap, BusinessLog businessLog);

    /**
     * 查询业务日志详情
     * @param id
     * @return
     */
    BusinessLog queryBusinessLogDetailById(int id);

    /**
     * 清空业务日志
     * @return
     */
    int clearBusinessLog();

    /**
     * 查询登录日志，分页
     * @param pageMap
     * @param loginLog
     * @return
     */
    PageInfo<LoginLog> queryLoginLogList(Map<String, Object> pageMap, LoginLog loginLog);

    /**
     * 清空登录日志
     * @return
     */
    int clearLoginLog();
}
