package com.inventory.controller.system;

import com.github.pagehelper.PageInfo;
import com.inventory.controller.BaseController;
import com.inventory.po.system.BusinessLog;
import com.inventory.po.system.LoginLog;
import com.inventory.service.system.LogService;
import com.inventory.util.CommonConstants;
import com.inventory.vo.system.UserVO;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:日志控制器
 * @Author:zhang.kaigang
 * @Date:2019/6/26
 * @Version:1.0
 */
@Controller
@RequestMapping("/log")
public class LogController extends BaseController {

    @Autowired
    private LogService logService;

    /**
     * 跳转到业务日志列表页面
     * @return
     */
    @RequestMapping(value = "/businessLogList")
    public String businessLogList(){
        return "/log/businessLogList";
    }

    /**
     * 查询业务日志，分页
     * @param currentPage
     * @param pageSize
     * @param businessLog
     * @param response
     */
    @RequestMapping(value = "/queryBusinessLogList")
    public void queryBusinessLogList(@RequestParam(value = "page", defaultValue="1") Integer currentPage,
                                     @RequestParam(value = "limit", defaultValue="10") Integer pageSize,
                                     BusinessLog businessLog,
                                     HttpServletResponse response){
        //拼装分页信息
        Map<String, Object> pageMap = new HashMap();
        pageMap.put(CommonConstants.CURRENT_PAGE, currentPage);
        pageMap.put(CommonConstants.PAGE_SIZE, pageSize);
        PageInfo<BusinessLog> pageInfo = logService.queryBusinessLogList(pageMap, businessLog);
        JSONObject resultObject = resultJSONObject(pageInfo);
        writeResponse(resultObject, response);
    }

    /**
     * 查看业务日志详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryBusinessLogDetailById")
    @ResponseBody
    public Object queryBusinessLogDetailById(@RequestParam("id") Integer id){
        BusinessLog businessLog = logService.queryBusinessLogDetailById(id);
        return processResult(CommonConstants.SUCCESS, businessLog);
    }

    /**
     * 清空业务日志
     * @return
     */
    @RequestMapping(value = "/clearBusinessLog")
    @ResponseBody
    @RequiresPermissions("businessLog:clear")
    public Object clearBusinessLog(){
        try{
            logService.clearBusinessLog();
            return processResult(CommonConstants.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return processResult(CommonConstants.ERROR);
        }
    }


    /**
     * 跳转到登录日志列表页面
     * @return
     */
    @RequestMapping(value = "/loginLogList")
    public String loginLogList(){
        return "/log/loginLogList";
    }

    /**
     * 查询登录日志，分页
     * @param currentPage
     * @param pageSize
     * @param loginLog
     * @param response
     */
    @RequestMapping(value = "/queryLoginLogList")
    public void queryLoginLogList(@RequestParam(value = "page", defaultValue="1") Integer currentPage,
                                     @RequestParam(value = "limit", defaultValue="10") Integer pageSize,
                                     LoginLog loginLog,
                                     HttpServletResponse response){
        //拼装分页信息
        Map<String, Object> pageMap = new HashMap();
        pageMap.put(CommonConstants.CURRENT_PAGE, currentPage);
        pageMap.put(CommonConstants.PAGE_SIZE, pageSize);
        PageInfo<LoginLog> pageInfo = logService.queryLoginLogList(pageMap, loginLog);
        JSONObject resultObject = resultJSONObject(pageInfo);
        writeResponse(resultObject, response);
    }

    /**
     * 清空登录日志
     * @return
     */
    @RequestMapping(value = "/clearLoginLog")
    @ResponseBody
    @RequiresPermissions("loginLog:clear")
    public Object clearLoginLog(){
        try{
            logService.clearLoginLog();
            return processResult(CommonConstants.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return processResult(CommonConstants.ERROR);
        }
    }
}
