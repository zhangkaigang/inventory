package com.inventory.web;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.inventory.util.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by zkaigang on 2018/7/31.
 */
public class AbstractCommonController {

    private static Logger logger = LoggerFactory.getLogger(AbstractCommonController.class);
    /**
     * JSONP支持
     */
    public static final String CALL_FUNCTION_NAME = "callback";
    public static final String RESULT_STAT_BUZ_EXCEPTION = "BUZ_EXCEPTION";

    /**
     * 返回处理结果信息
     * @param resultStat 处理结果状态
     * @param mess 处理结果返回信息
     * @param callBack 回调函数
     * @param data 返回数据集
     * @return
     */
    protected Object proccessResult(String resultStat,String mess, String callBack, Object data){
        ProcessResult result = new ProcessResult(resultStat, mess, data);
        result.setCallBack(callBack);
        return result;
    }

    /**
     * JsonP返回值类型的支持，callback参数名称为callback
     * @param request
     * @param resultStat
     * @param mess
     * @param data
     * @return
     */
    protected Object proccessResult(HttpServletRequest request, String resultStat,String mess, Object data){
        Object rst = proccessResult(resultStat, mess, data);
        String callbackFunName = request.getParameter(CALL_FUNCTION_NAME);
        if(StringUtils.hasText(callbackFunName)){
            return new JSONPObject(callbackFunName, rst);
        }
        return rst;
    }

    public static String handlerExceptionStack(Throwable e){
        logger.debug(e.getMessage());
        StringBuilder mbf = new StringBuilder("");
        StackTraceElement[] elements = e.getStackTrace();
        if(elements!=null){
            for(StackTraceElement ele:elements) mbf.append("\t").append(ele.toString()).append("\n");
        }else{
            mbf.append("null");
        }
        return mbf.toString();
    }

    protected String handlerStack(Throwable e){
        return handlerExceptionStack(e);
    }

    protected Object handlerException(Throwable e){
        return handlerException(e, null);
    }

    protected Object handlerException(Throwable e, String prepend){
        String mess = "";
        if(StringUtils.hasText(e.getMessage())){
            mess = e.getMessage();
        }else{
            if(e.getCause() != null && StringUtils.hasText(e.getCause().getMessage())){
                mess = e.getCause().getMessage();
            }
        }
        if(StringUtils.hasText(prepend)){
            mess = prepend + mess;
        }
        String uniqId = UUID.randomUUID().toString();
        if(e instanceof ServiceBuizException){
            if(StringUtils.hasText(((ServiceBuizException) e).getResultStat())){
                return proccessResult(((ServiceBuizException) e).getResultStat(), e.getMessage(), uniqId, ((ServiceBuizException) e).getData());
            }
            return proccessResult(RESULT_STAT_BUZ_EXCEPTION, e.getMessage(), uniqId, ((ServiceBuizException) e).getData());
        }
        return proccessResult(CommonConstants.ERROR, mess, uniqId, handlerStack(e));
    }

    /**
     * 返回处理结果信息
     * @param resultStat 处理结果状态，共三种:<code>ProcessResult.SUCCESS</code>, <code>ProcessResult.WARN</code>, <code>ProcessResult.ERROR</code>
     * @param mess 处理结果信息
     * @return
     */
    protected Object proccessResult(String resultStat,String mess, Object data){
        ProcessResult result = new ProcessResult(resultStat, mess);
        result.setData(data);
        return result;
    }
}
