package com.inventory.controller;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zkaigang
 */
public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *  为response提供Json格式的返回数据
     */
    protected void writeResponse(Object obj, HttpServletResponse response) {
        try {
            // 设置编码
            response.setContentType("text/html;charset=utf-8");
            JSONObject jsonObj =  JSONObject.fromObject(obj);
            String mes  = jsonObj.toString();
            PrintWriter writer = response.getWriter();
            writer.write(mes);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }
}
