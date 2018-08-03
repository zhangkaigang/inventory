package com.inventory.web.executor.request;

/**
 * 调用实际执行的接口类，实现了具体接口的调用
 * Created by zkaigang on 2018/7/31.
 */
public interface FunctionCallApply {
    /**
     * 服务调用请求
     * @param param
     * @return
     */
    Object call(RequestParamObject param) throws Throwable;


    /**
     * 返回当前支持的协议类型
     * @return
     */
    String getSupportProtocol();
}
