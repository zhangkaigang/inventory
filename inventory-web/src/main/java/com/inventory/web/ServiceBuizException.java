package com.inventory.web;

/**
 * 业务处理异常类定义
 * Created by zkaigang on 2018/7/31.
 */
public class ServiceBuizException extends RuntimeException{

    private Object data;

    private String resultStat;

    public Object getData() {
        return data;
    }


    /**
     *
     */
    private static final long serialVersionUID = -4774734110153475630L;

    public ServiceBuizException() {
        super();
    }


    public ServiceBuizException(String message, Object data) {
        this(message);
        this.data = data;
    }

    public ServiceBuizException(String message, Object data, String resultStat) {
        super(message);
        this.data = data;
        this.resultStat = resultStat;
    }

    public ServiceBuizException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceBuizException(String message) {
        super(message);
    }

    public ServiceBuizException(Throwable cause) {
        super(cause);
    }

    public String getResultStat() {
        return resultStat;
    }

}
