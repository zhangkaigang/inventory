package com.inventory.web.executor.protocol;

import com.inventory.web.executor.request.RequestParamObject;

/**
 * 调用方法执行者协议转换接口，通过实现该接口完成各种服务端调用协议的转换
 * Created by zkaigang on 2018/7/31.
 */
public interface FunctionExecutorProtocol {
    Object applyFunction(RequestParamObject param) throws Throwable;
}
