package com.inventory.web.executor.request;

import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * 请求参数封装结构,包含基本的请求信息封装
 * Created by zkaigang on 2018/7/31.
 */
public class RequestParamObject {
    public static final String PROTOCOL_DEFAULT = "default";

    private String service;

    private String method;

    private String protocol;

    private Object[] params;



    /**
     * 创建请求对象
     * @param service 调用的服务
     * @param method 调用服务方法
     * @param protocol 协议
     * @param params 调用服务参数
     */
    public RequestParamObject(String service, String method, String protocol, Object[] params) {
        if(!StringUtils.hasText(protocol)){
            protocol = PROTOCOL_DEFAULT;
        }
        this.service = service;
        this.method = method;
        this.protocol = protocol;
        this.params = params;
    }



    public RequestParamObject(String service, String method, Object[] params) {
        this(service, method, null, params);
    }



    public RequestParamObject(String service, String method, String protocol) {
        this(service, method, protocol, null);
    }



    public RequestParamObject(String service, String method) {
        this(service, method, null, null);
    }


    public String getService() {
        return service;
    }

    public String getMethod() {
        return method;
    }

    public String getProtocol() {
        return protocol;
    }

    public Object[] getParams() {
        return params;
    }



    @Override
    public String toString() {
        return "RequestParamObject [service=" + service + ", method=" + method + ", protocol=" + protocol + ", params=" + Arrays.toString(params) + "]";
    }
}
