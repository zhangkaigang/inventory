package com.inventory.web;

import com.inventory.util.CommonConstants;

import static com.inventory.util.CommonConstants.SUCCESS;

/**
 * Created by zkaigang on 2018/7/31.
 */
public class ProcessResult {

    /**
     * 处理结果状态
     */
    private String resultStat;

    /**
     * 处理结果返回信息
     */
    private String mess;

    /**
     * 处理结果回调函数
     */
    private String callBack;

    /**
     *处理完成返回数据
     */
    private Object data;

    public ProcessResult() {
        this.resultStat = CommonConstants.SUCCESS;
    }

    public ProcessResult(String resultStat,String mess){
        this.resultStat = resultStat;
        this.mess = mess;
    }

    public ProcessResult(String resultStat){
        this.resultStat = resultStat;
    }

    public ProcessResult(String resultStat,String mess, Object data){
        this.resultStat = resultStat;
        this.mess = mess;
        this.data = data;
    }

    public String getResultStat() {
        return resultStat;
    }



    public void setResultStat(String resultStat) {
        this.resultStat = resultStat;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String getCallBack() {
        return callBack;
    }

    public void setCallBack(String callBack) {
        this.callBack = callBack;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
