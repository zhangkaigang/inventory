package com.inventory.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * Created by zkaigang on 2018/7/31.
 */
@Service
public class FrameWorkApplicationContext implements ApplicationContextAware {
    public static final Logger log = LoggerFactory.getLogger(FrameWorkApplicationContext.class);

    private static ApplicationContext context;


    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        context = applicationContext;
        log.info("set app spring context success.");
    }

    /**
     * 获取Spring上下文
     * @return
     */
    public static final ApplicationContext getApplicationContext(){
        return context;
    }

    public static final Object getBean(String name) throws BeansException {
        return context.getBean(name);
    }

    public static final <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {

        return context.getBeansOfType(type);
    }

    public static final Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType)
            throws BeansException{
        return context.getBeansWithAnnotation(annotationType);
    }

    public static final <T> T getBean(String name, Class<T> requiredType)
            throws BeansException {

        return context.getBean(name, requiredType);
    }


    public static final <T> T getBean(Class<T> requiredType) throws BeansException {

        return context.getBean(requiredType);
    }


    public static final Object getBean(String name, Object... args) throws BeansException {

        return context.getBean(name, args);
    }


    public static final boolean containsBean(String name) {

        return context.containsBean(name);
    }


    public static final boolean isSingleton(String name)
            throws NoSuchBeanDefinitionException {

        return context.isSingleton(name);
    }


    public static final boolean isPrototype(String name)
            throws NoSuchBeanDefinitionException {

        return context.isPrototype(name);
    }


    @SuppressWarnings("rawtypes")
    public static final boolean isTypeMatch(String name, Class targetType)
            throws NoSuchBeanDefinitionException {

        return context.isTypeMatch(name, targetType);
    }


    public static final Class<?> getType(String name) throws NoSuchBeanDefinitionException {

        return context.getType(name);
    }

    public static final String[] getAliases(String name) {

        return context.getAliases(name);
    }

}
