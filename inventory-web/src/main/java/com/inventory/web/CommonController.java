package com.inventory.web;


import com.inventory.util.CommonConstants;
import com.inventory.web.executor.protocol.FunctionExecutorProtocol;
import com.inventory.web.executor.request.DefaultFunctionCallApply;
import com.inventory.web.executor.request.FunctionCallApply;
import com.inventory.web.executor.request.RequestParamObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zkaigang on 2018/7/31.
 */
@Controller
public class CommonController extends AbstractCommonController{

    private static Logger logger = LoggerFactory.getLogger(CommonController.class);

    /**
     * 调用远程方法参数
     */
    public static final String FUNCTION_OBJECT = "_callFunParams";
    private static final String PROTOCOL_SPLIT = ":";

    private FunctionExecutorProtocol protocol;

    /**
     * 调用服务端方法
     * @param request 参数_callFunParams为数组串化后的数组
     * @return
     */
    @RequestMapping("/commonFunction")
    @ResponseBody
    public Object callServerFunction(HttpServletRequest request){
        String freshStr = request.getParameter(FUNCTION_OBJECT);
        if(freshStr==null||freshStr.trim().length()==0){
            return proccessResult(request, CommonConstants.ERROR, "执行服务端方法必须指定类全名、方法名", request.getParameterMap());
        }

        try {
            JSONArray arrays = JSONArray.fromObject(freshStr);
            if(arrays.size()<2){
                return proccessResult(request, CommonConstants.ERROR, "执行服务端方法错误，必须指定类全名、方法名；传入参数["+freshStr+"]", freshStr);
            }
            if(arrays.size()==2){
                Object result = callFunction(arrays.getString(0), arrays.getString(1));
                return proccessResult(request, CommonConstants.SUCCESS, "执行服务端方法成功", result);
            }else{
                Object[] args = new Object[arrays.size()-2];
                for(int i=2;i<arrays.size();i++){
                    if(arrays.get(i) instanceof JSONObject && ((JSONObject)arrays.get(i)).isNullObject()){
                        continue;
                    }
                    args[i-2] = arrays.get(i);
                }
                Object result = callFunction(arrays.getString(0), arrays.getString(1),args);
                return proccessResult(request, CommonConstants.SUCCESS, "执行服务端方法成功", result);
            }
        } catch (Throwable e) {
            return handlerException(e);
        }

    }

    /**
     * 调用远程方法
     * @param className 调用的类名
     * @param method 调用的方法名
     * @param args 调用的参数，如果为空则不填
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Object callFunction(String className, String method, Object... args)
            throws Throwable {
        if(protocol==null){
            try {
                protocol = FrameWorkApplicationContextAware.getApplicationContext().getBean(FunctionExecutorProtocol.class);
            } catch (Exception e) {
                //no operation
            }
        }
        RequestParamObject requestObject = getProtocolParamObject(className, method, args);
        if(protocol!=null){
            try {
                return protocol.applyFunction(requestObject);
            } catch (Throwable e) {
                List<Object> list = null;
                if(args!=null){
                    list = new ArrayList<Object>(args.length);
                    Collections.addAll(list, args);
                }
                if(e instanceof InvocationTargetException){
                    e = ((InvocationTargetException)e).getTargetException();
                }
                if(e.getMessage()==null){
                    throw new RuntimeException("调用远程服务发生未知异常，服务类["+className+"]，方法["+method+"]，参数："+list+"\n 调用堆栈："+AbstractCommonController.handlerExceptionStack(e));
                }
                throw e;
            }
        }else{
            FunctionCallApply apply = new DefaultFunctionCallApply();
            return apply.call(requestObject);
        }
    }

    private RequestParamObject getProtocolParamObject(String className, String method, Object... args){
        int index = className.indexOf(PROTOCOL_SPLIT);
        if(index!=-1){
            String sName = className.substring(index+1);
            String protocol = className.substring(0, index);
            return new RequestParamObject(sName, method, protocol, args);
        }
        return new RequestParamObject(className, method, args);
    }
}
