package com.inventory.log;

import com.inventory.annotation.BusinessLogAnnotation;
import com.inventory.po.system.BusinessLog;
import com.inventory.util.ContrastObjUtil;
import com.inventory.util.MapConverterUtil;
import com.inventory.vo.system.UserVO;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:日志记录切面类
 * @Author:zhang.kaigang
 * @Date:2019/6/24
 * @Version:1.0
 */
@Component
@Aspect
public class BusinessLogAop {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 访问的类
    private Class clazz;

    // 访问的方法
    private Method method;

    /**
     * 切入点为带有BusinessLogAnnotation注解的方法
     */
    @Pointcut(value = "@annotation(com.inventory.annotation.BusinessLogAnnotation)")
    public void cutService() {
    }

    /**
     * 记录日志
     * @Before 是在所拦截方法执行之前执行一段逻辑，
     * @After 是在所拦截方法执行之后执行一段逻辑，
     * @Around 是可以同时在所拦截方法的前后执行一段逻辑
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("cutService()")
    public Object recordBusinessLog(ProceedingJoinPoint point) throws Throwable{
        // 先执行业务
        Object result = point.proceed();
        try {
            // 处理日志
            handleBusinessLog(point);
        } catch (Exception e) {
            logger.error("日志记录出错!", e);
        }
        return result;
    }

    /**
     * 处理日志
     * @param point
     * @throws NoSuchMethodException
     */
    private void handleBusinessLog(ProceedingJoinPoint point) throws NoSuchMethodException{
        // 当前用户没登录，不做日志处理
        UserVO userVO = (UserVO) SecurityUtils.getSubject().getPrincipal();
        if(userVO == null) {
            return;
        }

        // 具体要访问的类
        clazz = point.getTarget().getClass();
        // 获取访问的类的名字
        String className = clazz.getName();
        // 获取访问的方法名称
        String methodName = point.getSignature().getName();
        // 获取访问方法参数
        Object[] args = point.getArgs();

        // 获取具体执行的方法Method对象
        if(args == null || args.length == 0){
            // 只能获取无参数的方法
            method = clazz.getMethod(methodName);
        } else {
            // 要求controller中的参数需要包装类型，比如Integer不能是int
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < classArgs.length; i++){
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classArgs);
        }

        // 获取方法上面注解类BusinessLogAnnotation信息
        BusinessLogAnnotation businessLogAnnotation = (BusinessLogAnnotation)method.getAnnotation(BusinessLogAnnotation.class);
        String logName = businessLogAnnotation.name();
        String key = businessLogAnnotation.key();
        String keyDesc = businessLogAnnotation.keyDesc();
        String columnName = businessLogAnnotation.column();
        String tableName = businessLogAnnotation.table();
        StringBuilder message = new StringBuilder();
        // 获取现在的表单内容
        Map<String, Object> parameters = getRequestParameters();
        // 得到关键词对应的值，然后组装具体消息如：新增用户：zhangsan
        String keyValue = (String)parameters.get(key);
        message.append(logName).append("：").append(keyValue).append("(").append(keyDesc).append(")");
        String detail = "";
        if (logName.contains("修改")) {
            // 修改要额外处理前后差异
            // 先得到修改前对象
            Object oldObject = LogObjectHolder.me().getOldObject();
            // 得到修改后对象
            Object newObject = LogObjectHolder.me().getNewObject();
            detail = ContrastObjUtil.changeContent(newObject, oldObject);
        }
        BusinessLog businessLog = LogFactory.createBusinessLog(logName, userVO.getId(), className, methodName,
                columnName, tableName, message.toString(), detail);
        LogManager.me().executeLog(LogTaskFactory.addBusinessLog(businessLog));
    }

    /**
     * 获取传递的参数Map
     * @return
     */
    public static Map<String, Object> getRequestParameters() {
        HashMap<String, Object> values = new HashMap();
        HttpServletRequest request = getRequest();
        if (request == null) {
            return values;
        } else {
            Enumeration enums = request.getParameterNames();

            while(enums.hasMoreElements()) {
                String paramName = (String)enums.nextElement();
                String paramValue = request.getParameter(paramName);
                values.put(paramName, paramValue);
            }

            return values;
        }
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }
}
