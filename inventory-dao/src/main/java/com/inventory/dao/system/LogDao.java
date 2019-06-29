package com.inventory.dao.system;

import com.inventory.po.system.BusinessLog;
import com.inventory.po.system.LoginLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:业务日志DAO层
 * @Author:zhang.kaigang
 * @Date:2019/6/24
 * @Version:1.0
 */
public interface LogDao {

    /**
     * 新增业务日志
     * @param businessLog
     * @return
     */
    int addBusinessLog(BusinessLog businessLog);

    /**
     * 查询业务日志
     * @return
     */
    List<BusinessLog> queryBusinessLogList(@Param("businessLog") BusinessLog businessLog);

    /**
     * 根据id查询业务日志信息
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
     * 新增登录日志
     * @param loginLog
     * @return
     */
    int addLoginLog(LoginLog loginLog);

    /**
     * 查询登录日志
     * @return
     */
    List<LoginLog> queryLoginLogList(@Param("loginLog") LoginLog loginLog);

    /**
     * 清空登录日志
     * @return
     */
    int clearLoginLog();
}
