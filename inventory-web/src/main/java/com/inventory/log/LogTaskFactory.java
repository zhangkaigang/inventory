package com.inventory.log;

import com.inventory.dao.system.LogDao;
import com.inventory.po.system.BusinessLog;
import com.inventory.po.system.LoginLog;
import com.inventory.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * @Description:日志操作任务创建工厂
 * @Author:zhang.kaigang
 * @Date:2019/6/24
 * @Version:1.0
 */
public class LogTaskFactory {

    private static LogDao logDao = (LogDao)SpringUtil.getBeanByClass(LogDao.class);

    private static Logger logger = LoggerFactory.getLogger(LogManager.class);

    /**
     * 创建业务日志
     * @param businessLog
     * @return
     */
    public static TimerTask addBusinessLog(final BusinessLog businessLog){
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    logDao.addBusinessLog(businessLog);
                } catch (Exception e) {
                    logger.error("创建业务日志异常!", e);
                }
            }
        };
    }

    public static TimerTask addLoginLog(final LoginLog loginLog){
        return new TimerTask(){
            @Override
            public void run() {
                try{
                    logDao.addLoginLog(loginLog);
                }catch (Exception e){
                    logger.error("创建登录日志异常！", e);
                }
            }
        };
    }

}
