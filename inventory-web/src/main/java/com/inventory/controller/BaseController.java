package com.inventory.controller;

import com.github.pagehelper.PageInfo;
import com.inventory.util.CommonConstants;
import com.inventory.util.ProcessResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:公共控制器封装类
 * @Author:zhang.kaigang
 * @Date:2019/4/18 14:56
 * @Version:1.0
 */

public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 返回处理结果信息
     * @param result
     * @return
     */
    protected Object processResult(String result){
        ProcessResult processResult = new ProcessResult(result);
        return processResult;
    }


    /**
     * 返回处理结果信息
     * @param result
     * @param message
     * @return
     */
    protected Object processResult(String result, String message){
        ProcessResult processResult = new ProcessResult(result, message);
        return processResult;
    }

    /**
     * 返回处理信息
     * @param result
     * @param data
     * @return
     */
    protected Object processResult(String result, Object data){
        ProcessResult processResult = new ProcessResult(result,  data);
        return processResult;
    }

    /**
     * 返回处理结果信息
     * @param result
     * @param message
     * @param data
     * @return
     */
    protected Object processResult(String result, String message, Object data){
        ProcessResult processResult = new ProcessResult(result, message, data);
        return processResult;
    }

    /**
     *  为response提供Json格式的返回数据
     */
    protected void writeResponse(Object obj, HttpServletResponse response) {
        try {
            // 设置编码
            response.setContentType("text/html;charset=utf-8");
            JSONObject jsonObj =  JSONObject.fromObject(obj);
            String mes  = jsonObj.toString();
            PrintWriter writer = response.getWriter();
            writer.write(mes);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

    protected Map<String, Object> getPageMap(HttpServletRequest request){
        Map<String, Object> pageMap = new HashMap();
        int currentPage =  Integer.parseInt(request.getParameter(CommonConstants.LAYUI_PAGE));
        int pageSize = Integer.parseInt(request.getParameter(CommonConstants.LAYUI_LIMIT));
        pageMap.put(CommonConstants.CURRENT_PAGE, currentPage);
        pageMap.put(CommonConstants.PAGE_SIZE, pageSize);
        return pageMap;
    }

    protected JSONObject resultJSONObject(PageInfo pageInfo){
        JSONObject resultObject = new JSONObject();
        resultObject.put(CommonConstants.LAYUI_CODE, 0);
        resultObject.put(CommonConstants.LAYUI_COUNT, pageInfo.getTotal());
        resultObject.put(CommonConstants.LAYUI_DATA, JSONArray.fromObject(pageInfo.getList()));

        return resultObject;
    }

}
