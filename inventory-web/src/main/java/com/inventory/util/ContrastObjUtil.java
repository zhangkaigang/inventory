package com.inventory.util;

import com.inventory.annotation.FieldMeta;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description:比较对象的工具类，如果要通用需要去掉FieldMeta然后放在common下
 * @Author:zhang.kaigang
 * @Date:2019/6/25
 * @Version:1.0
 */
public class ContrastObjUtil {

    private static Logger logger = LoggerFactory.getLogger(ContrastObjUtil.class);

    /**
     * 获取Obj对象的fieldName属性的值
     * @param obj
     * @param fieldName
     * @return
     */
    /**
     * 获取Obj对象的fieldName属性的值
     * @param obj
     * @param fieldName
     * @return
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        Object fieldValue = null;
        if (null == obj) {
            return null;
        }
        Method[] methods = obj.getClass().getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (!methodName.startsWith("get")) {
                continue;
            }
            if (methodName.startsWith("get") && methodName.substring(3).toUpperCase().equals(fieldName.toUpperCase())) {
                try {
                    fieldValue = method.invoke(obj, new Object[] {});
                }
                catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    continue;
                }
            }
        }
        return fieldValue;
    }

    /**
     * 普通的两个对象比较，得到修改的内容
     * @param newSource 新的对象
     * @param oldSource 旧对象
     * @return 返回差异内容
     */
    public static String changeContent(Object newSource, Object oldSource) {

        // 变更信息message
        StringBuilder changeMessage = new StringBuilder();
        if (null == newSource || null == oldSource) {
            return null;
        }
        // 取出新的class类
        Class<?> newSourceClass = newSource.getClass();
        // 类的所有声明的字段
        Field[] newSourceFields = newSourceClass.getDeclaredFields();
        for (Field newField : newSourceFields) {
            String fieldName = newField.getName();
            // 如果是引用对象，则退出本次循环。继续下次
            String typeStr = String.valueOf(newField.getType());
            if (typeStr.contains("com") || typeStr.contains("List")) {
                continue;
            }
            // 获取新的Field值
            String newValue = getFieldValue(newSource, fieldName) == null ? "" : getFieldValue(newSource, fieldName).toString();
            // 获取对应的旧的targetField值
            String oldValue = getFieldValue(oldSource, fieldName) == null ? "" : getFieldValue(oldSource, fieldName).toString();
            if (StringUtils.isEmpty(newValue) && StringUtils.isEmpty(oldValue)) {
                continue;
            }
            FieldMeta fieldMeta = newField.getAnnotation(FieldMeta.class);
            if (fieldMeta != null && !newValue.equals(oldValue)) {
                if (StringUtils.isNotEmpty(fieldMeta.name())) {
                    changeMessage.append(fieldMeta.name())
                            .append("由“")
                            .append(oldValue)
                            .append("”")
                            .append("修改成“")
                            .append(newValue)
                            .append("”，");
                }
            }
        }
        return changeMessage.toString();

    }
}
