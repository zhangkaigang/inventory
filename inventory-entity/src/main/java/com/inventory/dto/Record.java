package com.inventory.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/7/9
 * @Version:1.0
 */
public class Record {

    private String errCode;
    private List<Map> errList;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public List<Map> getErrList() {
        return errList;
    }

    public void setErrList(String lineNo, String msg) {
        Map map = new HashMap();
        map.put("no", lineNo);
        map.put("msg", msg);
        errList.add(map);
    }
}
