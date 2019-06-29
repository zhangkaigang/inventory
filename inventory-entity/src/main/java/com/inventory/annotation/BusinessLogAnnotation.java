package com.inventory.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:业务日志注解类
 * @Author:zhang.kaigang
 * @Date:2019/6/24
 * @Version:1.0
 */
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})//定义注解的作用目标**作用范围字段
@Documented//说明该注解将被包含在javadoc中
public @interface BusinessLogAnnotation {

    /**
     * 业务的名称,例如:"修改菜单"
     */
    String name() default "";

    /**
     *被修改的实体的变量，比如loginName
     * @return
     */
    String key() default "";

    /**
     * 描述key
     * @return
     */
    String keyDesc() default "";

    /**
     * key对应表字段名
     * @return
     */
    String column() default "";

    /**
     * 表名
     * @return
     */
    String table() default "";
}
