package com.inventory.controller.develop;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.inventory.controller.BaseController;
import com.inventory.dto.Record;
import com.inventory.service.basic.CustomerService;
import com.inventory.service.develop.ExcelService;
import com.inventory.util.CommonConstants;
import com.inventory.util.ExcelListener;
import com.sun.xml.internal.rngom.parse.host.Base;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Description:Excel控制器
 * @Author:zhang.kaigang
 * @Date:2019/7/3
 * @Version:1.0
 */
@Controller
@RequestMapping("/excel")
public class ExcelController extends BaseController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ExcelService excelService;

    private XSSFWorkbook xssfWorkbook;
    private List<Record> recordInfoList;
    private List<Integer> errorList;

    /**
     * 跳转到列表页面
     * @return
     */
    @RequestMapping(value = "/excelList")
    public String excelList(){
        return "/develop/excelList";
    }

    /**
     * 导入页面
     * @return
     */
    @RequestMapping(value = "/importExcelPage")
    public String excelImportPage(){
        return "/develop/importExcel";
    }

    @RequestMapping(value = "/importExcel")
    @ResponseBody
    public Object importExcel(HttpServletRequest request, HttpServletResponse response){
        try{
            response.setContentType("text/html;charset=UTF-8");
            String fileName = request.getParameter("fileName");
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile fileExcel = multipartRequest.getFile("fileExcel");
            InputStream inputStream = fileExcel.getInputStream();
            if(StringUtils.isEmpty(fileName)){
                fileName = fileExcel.getOriginalFilename();
            }
            // 上传的先放到临时目录
            String path = System.getProperty("java.io.tmpdir");
            File file = new File(path + "/"+ UUID.randomUUID()+"/");
            if (!file.exists()) {
                file.mkdirs();
            }
            File diskFile = new File(file.getPath() + "/" + fileName);
            fileExcel.transferTo(diskFile);
            FileInputStream fileInputStream = new FileInputStream(diskFile);
            xssfWorkbook = new XSSFWorkbook(fileInputStream);
            ExcelListener excelListener = new ExcelListener(excelService);
            EasyExcelFactory.readBySax( new BufferedInputStream(inputStream), new Sheet(1, 0), excelListener);
            recordInfoList = excelListener.getRecordList();
            if(recordInfoList != null && !recordInfoList.isEmpty()){
                // 有错误数据
                errorList = excelListener.getErrorRowList();
                return processResult(CommonConstants.ERROR);
            }
            return processResult(CommonConstants.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return processResult(CommonConstants.ERROR);
        }

    }

    /**
     * 跳转到导入日志
     * @return
     */
    @RequestMapping(value = "/importLogPage")
    public String importLogPage(){
        return "/develop/importLog";
    }

    /**
     * 查看错误日志信息
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getErrorInfoList")
    public void getErrorInfoList(HttpServletRequest request, HttpServletResponse response){
        List<Map<String,Object>> arrayList = new ArrayList<>();
        for (int i = 0; i < recordInfoList.size(); i++) {
            Record record = recordInfoList.get(i);
            Map<String, Object> hashMap = new HashMap<>();
            if("00".equals(record.getErrCode())){
                List errList = record.getErrList();
                for (int j = 0; j < errList.size(); j++) {
                    Map<String,String> object = (Map<String,String>)errList.get(j);
                    if(object!=null){
                        hashMap.put("errorMsg","第"+object.get("no")+"行"+object.get("msg"));
                        hashMap.put("no","数据错误");
                    }
                    arrayList.add(hashMap);
                }
            }
        }
        JSONObject resultArray = new JSONObject();
        resultArray.put(CommonConstants.LAYUI_CODE, 0);
        resultArray.put(CommonConstants.LAYUI_DATA, JSONArray.fromObject(arrayList));
        writeResponse(resultArray, response);
    }

    /**
     * 导出错误日志
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportErrorExcel")
    public void exportErrorExcel(HttpServletRequest request,HttpServletResponse response){
        try {
            String fileName = "错误数据";
            XSSFSheet sheet = xssfWorkbook.getSheet("导入");
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 3; i < lastRowNum; i++) {
                boolean isError = dealSheet(errorList, i);
                if(isError){
                    continue;
                }
                XSSFRow row = sheet.getRow(i);
                if(row!=null){
                    sheet.removeRow(row);
                }
            }
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename="+ fileName+".xlsx");
            ServletOutputStream os = response.getOutputStream();
            xssfWorkbook.write(os);
            xssfWorkbook.close();
            os.close();
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }

    }

    private boolean dealSheet(List<Integer> errorList, int i) {
        boolean isError = false;
        for (int j = 0; j < errorList.size(); j++) {
            if(errorList.get(j)==i+1){
                isError = true;
                break;
            }
        }
        return isError;
    }

}
