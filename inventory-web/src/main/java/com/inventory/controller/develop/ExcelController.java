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
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
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
            errorList = excelListener.getErrorRowList();
            return null;
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            return processResult(CommonConstants.ERROR);
        }

    }

}
