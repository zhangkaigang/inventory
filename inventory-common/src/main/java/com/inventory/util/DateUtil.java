package com.inventory.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:TODO
 * @Author:zhang.kaigang
 * @Date:2019/7/11
 * @Version:1.0
 */
public class DateUtil {

    public static String format(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
        String s1 = simpledateformat.format(date);
        return s1;
    }
}
