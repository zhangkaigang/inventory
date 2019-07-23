package com.inventory.dao.develop;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/7/10
 * @Version:1.0
 */
public interface ExcelDao {

    List<Map<String,Object>>  getExcelTableMapping(@Param("tableName") String tableName,
                                                   @Param("excelType") String excelType);

    int batchInsertData(@Param("mappingList") List<Map<String,Object>> mappingList,
                        @Param("dataList") List<Map<String, Object>> dataList);

}
