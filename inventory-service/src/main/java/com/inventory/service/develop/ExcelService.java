package com.inventory.service.develop;

import com.inventory.dto.Record;

import java.util.List;
import java.util.Map;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/7/9
 * @Version:1.0
 */
public interface ExcelService {

    List<Record> checkData(List<String[]> dataList, List<Integer> errorRowList);

    List<Map<String, Object>> parseData(List<String[]> dataList);

    void insertData(List<Map<String, Object>> dataList);

}
