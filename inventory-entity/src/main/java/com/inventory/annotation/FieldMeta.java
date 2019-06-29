package com.inventory.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:自定义注解，在VO上使用该注解，通过反射得到注解字段
 * @Author:zhang.kaigang
 * @Date:2019/6/27
 * @Version:1.0
 */
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target({ElementType.FIELD, ElementType.METHOD})//定义注解的作用目标**作用范围字段、枚举的常量/方法
@Documented//说明该注解将被包含在javadoc中
public @interface FieldMeta {

    /**
     * 描述
     * @return
     */
    String name() default "";

    /**
     * 对应列
     * @return
     */
    String column() default "";

    /**
     * 枚举值描述对应变量
     * @return
     */
    String enumColumn() default "";
}
