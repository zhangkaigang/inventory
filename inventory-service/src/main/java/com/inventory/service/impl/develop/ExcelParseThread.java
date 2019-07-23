package com.inventory.service.impl.develop;

import com.inventory.service.system.EnumsService;
import com.inventory.util.DateUtil;
import com.inventory.util.SpringUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/7/11
 * @Version:1.0
 */
public class ExcelParseThread implements Runnable{

    private EnumsService enumsService = (EnumsService)SpringUtil.getBeanByClass(EnumsService.class);

    private static final String REGEX = "[`~!@#$%^&*()\\-+={}':;,\\[\\]<>/?￥¥%…（）_+|【】‘；：”“’。，、？\\s]";
    private static final Pattern P = Pattern.compile(REGEX);
    protected static final Date D = new GregorianCalendar(1900,0,0).getTime();


    private CountDownLatch parseThreadCountDown;
    private int parseStartIndex;
    private int parseEndIndex;
    private int threadNum;
    private List<String[]> dataList;
    private List<Map<String,Object>> mappingList;
    private Map<Integer, List<Map<String, Object>>> threadDataMap;
    private int mappingNum;

    public ExcelParseThread(Map<String, Object> paramMap) {
        this.parseThreadCountDown = (CountDownLatch)MapUtils.getObject(paramMap, "parseThreadCountDown");
        this.parseStartIndex = MapUtils.getInteger(paramMap, "parseStartIndex");
        this.parseEndIndex = MapUtils.getInteger(paramMap, "parseEndIndex");
        this.dataList = (List<String[]>)MapUtils.getObject(paramMap, "dataList");
        this.threadNum = MapUtils.getInteger(paramMap, "threadNum");
        this.mappingList = (List<Map<String,Object>>) MapUtils.getObject(paramMap, "mappingList");
        this.threadDataMap = (Map<Integer, List<Map<String, Object>>>)MapUtils.getObject(paramMap, "threadDataMap");
        this.mappingNum = MapUtils.getInteger(paramMap, "mappingNum");
    }

    @Override
    public void run() {
        List<Map<String, Object>> dataMapList = new ArrayList<>();
        // 封装数据
        for (; parseStartIndex < parseEndIndex; parseStartIndex++) {
            String[] dataArray = dataList.get(parseStartIndex);
            // 保证有序方便动态插入，所以用LinkedHashMap
            Map<String, Object> dataMap = new LinkedHashMap<>();
            setDataMap(dataArray, dataMap, mappingNum);
            dataMapList.add(dataMap);
        }
        threadDataMap.put(threadNum, dataMapList);
        parseThreadCountDown.countDown();
    }

    private void setDataMap(String[] dataArray, Map<String, Object> dataMap, int mappingNum) {
        int dl = 0;
        for (int i = 0; i < mappingNum; i++) {
            String tempValue = "";
            if(i < dataArray.length){
                tempValue = dataArray[i];
            }
            Map<String, Object> map = mappingList.get(i);
            String columnName = MapUtils.getString(map, "COLUMN_NAME","");
            String dataType = MapUtils.getString(map, "DATA_TYPE","");
            String enumKey = MapUtils.getString(map, "ENUM_KEY","");
            /*if("CREATE_DATE".equalsIgnoreCase(columnName) || "ID".equalsIgnoreCase(columnName)){
                // 需要自动生成的
                dl++;
                continue;
            }*/
            if(StringUtils.isNotEmpty(enumKey)){
                // 判断字段是否是枚举值
                setEnumKeyValue(columnName, dataMap,  tempValue, enumKey);
            }else{
                switch (dataType){
                    case "int" :
                        dataMap.put(columnName, numberFilter(tempValue));
                        break;
                    case "date" :
                        dataMap.put(columnName, formatDate(tempValue));
                        break;
                    case "varchar" :
                    default:
                        dataMap.put(columnName, tempValue);
                        break;
                }
            }
        }
    }

    private void setEnumKeyValue(String columnName,
                                 Map<String, Object> dataMap,  String tempValue,
                                 String enumKey){
        String value = tempValue.trim();
        String enumsId = enumsService.getEnumsIdByKeywordAndDesc(enumKey + ","+ value);
        dataMap.put(columnName, "".equals(enumsId) ? value : enumsId);
    }

    /**
     * 过滤特殊字符
     * @param str
     * @return
     */
    private String numberFilter(String str) {
        if(StringUtils.isNotEmpty(str)){
            Matcher m = P.matcher(str);
            return m.replaceAll("").trim();
        }
        return "";
    }

    private String formatDate(String originalDate) {
        String originalDate2 = "";
        if(originalDate.length() == 5){
            originalDate2 = DateUtil.format(add(D, Calendar.DAY_OF_MONTH,Integer.valueOf(originalDate)-1),"yyyy-MM-dd");
        }
        return originalDate2;
    }

    private Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

}
