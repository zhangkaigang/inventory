package com.inventory.service.impl.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.inventory.dao.system.LogDao;
import com.inventory.po.system.BusinessLog;
import com.inventory.po.system.LoginLog;
import com.inventory.po.system.User;
import com.inventory.service.system.LogService;
import com.inventory.util.CommonConstants;
import com.inventory.util.PoJoConverterUtil;
import com.inventory.vo.system.UserVO;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/6/26
 * @Version:1.0
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public PageInfo<BusinessLog> queryBusinessLogList(Map<String, Object> pageMap, BusinessLog businessLog) {
        int currentPage = MapUtils.getIntValue(pageMap, CommonConstants.CURRENT_PAGE);
        int pageSize = MapUtils.getIntValue(pageMap, CommonConstants.PAGE_SIZE);
        PageHelper.startPage(currentPage, pageSize);
        List<BusinessLog> businessLogList = logDao.queryBusinessLogList(businessLog);
        PageInfo<BusinessLog> pageInfo = new PageInfo<>(businessLogList);
        return pageInfo;
    }

    @Override
    public BusinessLog queryBusinessLogDetailById(int id) {
        BusinessLog businessLog = logDao.queryBusinessLogDetailById(id);
        return businessLog;
    }

    @Override
    public int clearBusinessLog() {
        return logDao.clearBusinessLog();
    }

    @Override
    public PageInfo<LoginLog> queryLoginLogList(Map<String, Object> pageMap, LoginLog loginLog) {
        int currentPage = MapUtils.getIntValue(pageMap, CommonConstants.CURRENT_PAGE);
        int pageSize = MapUtils.getIntValue(pageMap, CommonConstants.PAGE_SIZE);
        PageHelper.startPage(currentPage, pageSize);
        List<LoginLog> loginLogList = logDao.queryLoginLogList(loginLog);
        PageInfo<LoginLog> pageInfo = new PageInfo<>(loginLogList);
        return pageInfo;
    }

    @Override
    public int clearLoginLog() {
        return logDao.clearLoginLog();
    }
}
