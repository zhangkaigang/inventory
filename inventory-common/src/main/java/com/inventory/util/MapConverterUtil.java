package com.inventory.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:转换器：map和javabean的互相转换
 * @Author:zhang.kaigang
 * @Date:2019/6/27
 * @Version:1.0
 */
public class MapConverterUtil {

    private static Logger logger = LoggerFactory.getLogger(MapConverterUtil.class);

    /**
     * map转bean
     * @param source   map属性
     * @param instance 要转换成的bean
     * @return 该bean
     */
    public static <T> T convertMap2Bean(Map<String, Object> source, Class<T> instance) {
        try {
            T object = instance.newInstance();
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(object, source.get(field.getName()));
            }
            return object;
        }
        catch (InstantiationException | IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * List<Map>转List<Javabean>
     * @param listMap
     * @param instance
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> convertListMap2ListBean(List<Map<String, Object>> listMap, Class<T> instance) {
        try {
            List<T> beanList = new ArrayList<T>();
            for (int i = 0, n = listMap.size(); i < n; i++) {
                Map<String, Object> map = listMap.get(i);
                T bean = convertMap2Bean(map, instance);
                beanList.add(bean);
            }
            return beanList;
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }




}
