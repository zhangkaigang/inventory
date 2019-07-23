package com.inventory.service.impl.develop;

import com.inventory.dao.basic.CustomerDao;
import com.inventory.dao.develop.ExcelDao;
import com.inventory.dto.Record;
import com.inventory.service.develop.ExcelService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/7/9
 * @Version:1.0
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private ExcelDao excelDao;

    // 必填字段列
    private static final int[] MUST_FIELD = {2, 3, 4, 6};
    // 唯一性字段列
    private static final int[] UNIQUE_FIELD = {4};

    private List<Integer> errorRowList;

    @Override
    public List<Record> checkData(List<String[]> dataList, List<Integer> errorRowList) {
        List<Record> recordList = new ArrayList<>();
        // 获取列字段数据
        String[] headArr = dataList.get(0);
        for(int i = 0, len = dataList.size(); i < len; i++){
            String[] dataArr = dataList.get(i);
            // 非空校验
            mustFieldCheck(i, recordList, dataArr, headArr, errorRowList);
            // 唯一性校验
//            uniqueFieldCheck(i, recordList, dataArr, headArr, errorRowList);
        }
        return recordList;
    }

    @Override
    public List<Map<String, Object>> parseData(List<String[]> dataList) {
        List<Map<String,Object>> mappingList = excelDao.getExcelTableMapping("basic_customer", "basic_customer");
        int mappingNum = mappingList.size();
        return parseDataThreads(dataList, mappingList, mappingNum);
    }

    @Override
    public void insertData(List<Map<String, Object>> parseDataList) {
        List<Map<String,Object>> mappingList = excelDao.getExcelTableMapping("basic_customer", "basic_customer");
        int mappingNum = mappingList.size();
        insertDataThreads(parseDataList, mappingList, mappingNum);
    }

    /**
     * 插入解析后的数据线程分配
     * @param parseDataList
     * @param mappingList
     * @param mappingNum
     */
    private void insertDataThreads(List<Map<String, Object>> parseDataList,List<Map<String,Object>> mappingList,int mappingNum){
        int parseDataNum = parseDataList.size();
        // 一个线程处理多少条数据,默认1000
        int count = 10;
        // 开启的线程数
        int runThreadSize = (parseDataNum / count) + 1;
        int startIndex;
        int endIndex;
        long cu = System.currentTimeMillis();
        // 线程池
        ThreadPoolExecutor producerPool = new ThreadPoolExecutor(5, 100, 100,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        // 创建一个计数器
        CountDownLatch threadCountDown = new CountDownLatch(runThreadSize);
        // 循环创建线程
        for (int i = 0; i < runThreadSize; i++) {
            if ((i + 1) == runThreadSize) {
                startIndex = i * count;
                endIndex = parseDataNum;
            } else {
                startIndex = i * count;
                endIndex = (i + 1) * count;
            }
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("threadCountDown", threadCountDown);
            paramMap.put("startIndex", startIndex);
            paramMap.put("endIndex", endIndex);
            paramMap.put("parseDataList", parseDataList);
            paramMap.put("mappingList", mappingList);
            paramMap.put("mappingNum", mappingNum);
            ExcelInsertThread thread = new ExcelInsertThread(paramMap);
            producerPool.submit(thread);
        }
        try {
            threadCountDown.await();
            producerPool.shutdown();
        } catch (InterruptedException e) {
            logger.error(e.getMessage(),e);
            Thread.currentThread().interrupt();
        }finally{
            if(System.currentTimeMillis()-cu > 30000 && !producerPool.isShutdown()){
                producerPool.shutdown();
            }
        }

    }

    /**
     * 解析数据线程分配
     * @param dataList
     * @param mappingList
     * @param mappingNum
     * @return
     */
    private List<Map<String, Object>>  parseDataThreads(List<String[]> dataList,List<Map<String,Object>> mappingList,int mappingNum){
        int dataNum = dataList.size();
        // 一个线程处理多少条数据,默认1000
        int count = 10;
        // 开启的线程数
        int parseThreadSize = (dataNum / count) + 1;
        int parseStartIndex;
        int parseEndIndex;
        // 创建错误信息list,在多线程中收集错误的数据
        errorRowList = Collections.synchronizedList(new ArrayList<Integer>());
        long cu = System.currentTimeMillis();
        // 线程池
        ThreadPoolExecutor producerPool = new ThreadPoolExecutor(5, 100, 100,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        // 创建一个计数器
        CountDownLatch parseThreadCountDown = new CountDownLatch(parseThreadSize);
        Map<Integer, List<Map<String, Object>>> threadDataMap = new ConcurrentHashMap<>();
        // 循环创建线程
        for (int i = 0; i < parseThreadSize; i++) {
            if ((i + 1) == parseThreadSize) {
                parseStartIndex = i * count;
                parseEndIndex = dataNum;
            } else {
                parseStartIndex = i * count;
                parseEndIndex = (i + 1) * count;
            }
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("parseThreadCountDown", parseThreadCountDown);
            paramMap.put("parseStartIndex", parseStartIndex);
            paramMap.put("parseEndIndex", parseEndIndex);
            paramMap.put("dataList", dataList);
            paramMap.put("threadNum", i);
            paramMap.put("mappingList", mappingList);
            paramMap.put("threadDataMap", threadDataMap);
            paramMap.put("mappingNum", mappingNum);
            ExcelParseThread excelParseThread = new ExcelParseThread(paramMap);
            producerPool.execute(excelParseThread);
        }
        try {
            logger.error("线程开始等待");
            parseThreadCountDown.await();
            producerPool.shutdown();
            logger.error("解析线程全部完成");
        } catch (InterruptedException e) {
            logger.error(e.getMessage(),e);
            Thread.currentThread().interrupt();
        }finally{
            if(System.currentTimeMillis()-cu>30000 && !producerPool.isShutdown()){
                producerPool.shutdown();
            }
        }
        List<Map<String, Object>> dataMapList = new ArrayList<>();
        for (int i = 0; i < parseThreadSize; i++) {
            List<Map<String, Object>> arrayList = threadDataMap.get(i);
            dataMapList.addAll(arrayList);
        }
        return dataMapList;
    }

    /**
     * 必填校验
     * @param rowNum
     * @param recordList
     * @param dataArr
     * @param headArr
     * @param errorRowList
     */
    private void mustFieldCheck(int rowNum, List<Record> recordList, String[] dataArr, String[] headArr, List<Integer> errorRowList){
        Record record = new Record();
        boolean isExist = false;
        // 设置错误类型
        record.setErrCode("00");
        for (int i = 0; i < MUST_FIELD.length; i++) {
            String data = dataArr[MUST_FIELD[i]-1];
            if(StringUtils.isEmpty(data)){
                isExist = true;
                errorRowList.add(rowNum + 3);
                record.setErrList(Integer.toString(rowNum + 3), headArr[MUST_FIELD[i]-1]+"列字段数据不能为空！！！");
            }
        }
        if(isExist){
            recordList.add(record);
        }
    }

    /**
     * 唯一性校验
     * @param rowNum
     * @param recordList
     * @param dataArr
     * @param headArr
     * @param errorRowList
     */
    private void uniqueFieldCheck(int rowNum, List<Record> recordList, String[] dataArr, String[] headArr, List<Integer> errorRowList) {
        Record record = new Record();
        // 设置错误类型
        record.setErrCode("01");
        boolean isUnique = true;
        for (int i = 0; i < UNIQUE_FIELD.length; i++) {
            // 得到需要校验唯一性的列值
            String data = dataArr[UNIQUE_FIELD[i]-1];
            int existNum = 0;
            if(UNIQUE_FIELD[i] == 4){
                existNum = customerDao.queryCountByTel(data);
            }
            if(existNum > 0){
                isUnique = false;
                errorRowList.add(rowNum + 3);
                record.setErrList(Integer.toString(rowNum + 3), headArr[UNIQUE_FIELD[i]-1]+" 列字段数据   "+data+" 已存在！！！");
            }
        }
        if(!isUnique){
            recordList.add(record);
        }
    }
}
