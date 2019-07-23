package com.inventory.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.inventory.dto.Record;
import com.inventory.service.basic.CustomerService;
import com.inventory.service.develop.ExcelService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Description:easyexcel监听
 * @Author:zhang.kaigang
 * @Date:2019/7/9
 * @Version:1.0
 */
public class ExcelListener extends AnalysisEventListener {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    // 数据集合
    private List<String[]> dataList = new ArrayList<String[]>();
    private List<Record> recordList = new ArrayList<>();
    // 导入失败数据行集合
    private List<Integer> errorRowList = Collections.synchronizedList(new ArrayList<Integer>());

    private ExcelService excelService;

    public ExcelListener(ExcelService excelService) {
        this.excelService = excelService;
    }

    @Override
    public void invoke(Object object, AnalysisContext analysisContext) {
        // 获取数据并封装数据
        Sheet currentSheet = analysisContext.getCurrentSheet();
        int sheetNo = currentSheet.getSheetNo();
        boolean isNull = true;
        if(sheetNo == 1){
            // sheet取第一个
            List<String> dataListTemp = (List<String>)object;
            String[] dataArr = new String[dataListTemp.size()];
            for (int i = 0, len = dataListTemp.size(); i < len; i++){
                dataArr[i] = dataListTemp.get(i);
                if(StringUtils.isNotEmpty(dataArr[i])){
                    isNull = false;
                }
            }
            if(!isNull){
                // 不为空则放入数据集合
                dataList.add(dataArr);
            }
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // excel导入模板前三行不是正式数据
        dataList.remove(2);
        dataList.remove(1);
        // 解析完成之后进行校验
        recordList = excelService.checkData(dataList, errorRowList);
        // 校验通过进行导入，校验不过直接返回
        if(recordList.isEmpty()){
            dataList.remove(0);
            List<Map<String, Object>> parseData = excelService.parseData(dataList);
            excelService.insertData(parseData);
        }
    }

    public List<Record> getRecordList() {
        return recordList;
    }

    public List<Integer> getErrorRowList() {
        return errorRowList;
    }
}
