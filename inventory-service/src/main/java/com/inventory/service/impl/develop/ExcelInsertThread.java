package com.inventory.service.impl.develop;

import com.inventory.dao.develop.ExcelDao;
import com.inventory.util.SpringUtil;
import org.apache.commons.collections.MapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/7/12
 * @Version:1.0
 */
public class ExcelInsertThread implements Runnable{

    private ExcelDao excelDao = (ExcelDao)SpringUtil.getBeanByClass(ExcelDao.class);

    private CountDownLatch threadCountDown;
    private int startIndex;
    private int endIndex;
    private List<Map<String, Object>> parseDataList;
    private List<Map<String,Object>> mappingList;

    public ExcelInsertThread(Map<String, Object> paramMap) {
        this.threadCountDown =(CountDownLatch) MapUtils.getObject(paramMap, "threadCountDown");
        this.startIndex = MapUtils.getInteger(paramMap, "startIndex");
        this.endIndex = MapUtils.getInteger(paramMap, "endIndex");
        this.parseDataList = (List<Map<String, Object>>)MapUtils.getObject(paramMap, "parseDataList", "");
        this.mappingList = (List<Map<String,Object>>) MapUtils.getObject(paramMap, "mappingList");
    }

    @Override
    public void run() {
        List<Map<String, Object>> subList = parseDataList.subList(startIndex, endIndex);
        excelDao.batchInsertData(mappingList, subList);
        threadCountDown.countDown();
    }
}
