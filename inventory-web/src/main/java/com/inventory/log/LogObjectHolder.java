package com.inventory.log;

import com.inventory.util.SpringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Description:被修改的bean临时存放处
 * @Author:zhang.kaigang
 * @Date:2019/6/27
 * @Version:1.0
 */
@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class LogObjectHolder {

    private Object oldObject = null;

    private Object newObject = null;

    public Object getOldObject() {
        return oldObject;
    }

    public void setOldObject(Object oldObject) {
        this.oldObject = oldObject;
    }

    public Object getNewObject() {
        return newObject;
    }

    public void setNewObject(Object newObject) {
        this.newObject = newObject;
    }

    public static LogObjectHolder me() {
        return (LogObjectHolder)SpringUtil.getBeanByClass(LogObjectHolder.class);
    }

}
