package com.inventory.web.executor.request;

import com.inventory.web.AbstractCommonController;
import com.inventory.web.FrameWorkApplicationContextAware;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 执行方法的调用通用实现
 * Created by zkaigang on 2018/7/31.
 */
public class DefaultFunctionCallApply implements FunctionCallApply {
    public Object call(RequestParamObject param) throws Throwable {

        Class<?> clazz = Class.forName(param.getService());

        Object bean = FrameWorkApplicationContextAware.getApplicationContext().getBean(clazz);

        if(bean==null){
            if(clazz.isInterface()){
                throw new NoSuchMethodException("调用远程服务，指定的接口["+param.getService()+"]不存在任何可供使用的实现类");
            }
            bean = clazz.newInstance();
        }

        Method[] methods = clazz.getMethods();

        Method em = null;

        Object[] args = param.getParams();
        for(Method m:methods){
            if(m.getName().equals(param.getMethod())){
                Type[] types = m.getGenericParameterTypes();
                if(args==null||args.length==0){
                    if(types.length==0){
                        em = m;
                        break;
                    }else{
                        continue;
                    }
                }
                if(types.length==args.length){
                    boolean mf = true;
                    for(int i=0;i<types.length;i++){
                        Object o = args[i];
                        if(o==null){
                            continue;
                        }
                        if(!types[i].toString().equals(o.getClass().toString())
                                &&
                                !("int".equals(types[i].toString())&&Integer.class.getCanonicalName().equals(o.getClass().getCanonicalName()))
                                &&
                                !("boolean".equals(types[i].toString())&&Boolean.class.getCanonicalName().equals(o.getClass().getCanonicalName()))
                                &&
                                !("char".equals(types[i].toString())&&Character.class.getCanonicalName().equals(o.getClass().getCanonicalName()))
                                &&
                                !("byte".equals(types[i].toString())&&Byte.class.getCanonicalName().equals(o.getClass().getCanonicalName()))
                                &&
                                !("long".equals(types[i].toString())&&Long.class.getCanonicalName().equals(o.getClass().getCanonicalName()))
                                &&
                                !("float".equals(types[i].toString())&&Float.class.getCanonicalName().equals(o.getClass().getCanonicalName()))
                                &&
                                !("short".equals(types[i].toString())&&Short.class.getCanonicalName().equals(o.getClass().getCanonicalName()))
                                &&
                                !("double".equals(types[i].toString())&&Double.class.getCanonicalName().equals(o.getClass().getCanonicalName()))
                                ){
                            try {
                                if(!((ParameterizedTypeImpl)types[i]).getRawType().isInstance(o)){
                                    mf = false;
                                    break;
                                }
                            } catch (Exception e) {
                                //参数无法转换的，认为不存在
                                mf = false;
                                break;
                            }

                        }
                    }
                    if(mf){em = m;break;}

                }
            }

        }


        if(em==null){
            List<Object> list = null;
            if(args!=null){
                list = new ArrayList<Object>(args.length);
                Collections.addAll(list, args);
            }
            throw new NoSuchMethodException("调用远程服务，指定的类["+param.getService()+"]不存在待调用的方法["+param.getMethod()+"],调用协议["+param.getProtocol()+"],参数："+list);
        }
        try {
            return em.invoke(bean, args);
        } catch (Throwable e) {
            List<Object> list = null;
            if(args!=null){
                list = new ArrayList<Object>(args.length);
                Collections.addAll(list, args);
            }
            if(e instanceof InvocationTargetException){
                e = ((InvocationTargetException)e).getTargetException();
            }
            if(e.getMessage()==null){
                throw new RuntimeException("调用远程服务发生未知异常，服务类["+param.getService()+"]，方法["+param.getMethod()+"]，调用协议["+param.getProtocol()+"]，参数："+list+"\n 调用堆栈："+ AbstractCommonController.handlerExceptionStack(e));
            }
            throw e;
        }
    }

    public String getSupportProtocol() {
        return RequestParamObject.PROTOCOL_DEFAULT;
    }

}
