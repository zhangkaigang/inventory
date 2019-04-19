package com.inventory.util;

/**
 * 处理结果类，主要向前台返回后台的信息
 * @author zkaigang
 */
public class ProcessResult {

    /**
     * 处理结果状态
     */
    private String result;

    /**
     * 处理结果返回信息
     */
    private String msg;

    /**
     * 处理结果返回数据
     */
    private Object data;

    public ProcessResult(){
        this.result = CommonConstants.SUCCESS;
    }

    public ProcessResult(String result){
        this.result = result;
    }

    public ProcessResult(String result, String msg){
        this.result = result;
        this.msg = msg;
    }

    public ProcessResult(String result, String msg, Object data){
        this.result = result;
        this.msg = msg;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMessage(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
