package com.inventory.web;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * Spring启动之后设置Spring上下文
 * Created by zkaigang on 2018/7/31.
 */
public abstract class FrameWorkApplicationContextAware {
    /**
     * 获取Spring上下文
     * @return
     */
    public static final ApplicationContext getApplicationContext(){
        return FrameWorkApplicationContext.getApplicationContext();
    }

    public static final Object getBean(String name) throws BeansException {

        return FrameWorkApplicationContext.getBean(name);
    }

    public static final <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {

        return FrameWorkApplicationContext.getBeansOfType(type);
    }

    public static final Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType)
            throws BeansException{
        return FrameWorkApplicationContext.getBeansWithAnnotation(annotationType);
    }


    public static final <T> T getBean(String name, Class<T> requiredType)
            throws BeansException {

        return FrameWorkApplicationContext.getBean(name, requiredType);
    }


    public static final <T> T getBean(Class<T> requiredType) throws BeansException {

        return FrameWorkApplicationContext.getBean(requiredType);
    }


    public static final Object getBean(String name, Object... args) throws BeansException {

        return FrameWorkApplicationContext.getBean(name, args);
    }


    public static final boolean containsBean(String name) {

        return FrameWorkApplicationContext.containsBean(name);
    }


    public static final boolean isSingleton(String name)
            throws NoSuchBeanDefinitionException {

        return FrameWorkApplicationContext.isSingleton(name);
    }


    public static final boolean isPrototype(String name)
            throws NoSuchBeanDefinitionException {

        return FrameWorkApplicationContext.isPrototype(name);
    }


    @SuppressWarnings("rawtypes")
    public static final boolean isTypeMatch(String name, Class targetType)
            throws NoSuchBeanDefinitionException {

        return FrameWorkApplicationContext.isTypeMatch(name, targetType);
    }


    public static final Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return FrameWorkApplicationContext.getType(name);
    }

    public static final String[] getAliases(String name) {
        return FrameWorkApplicationContext.getAliases(name);
    }
}
