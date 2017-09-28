package com.dhf.aop;

import java.lang.reflect.Method;


public class AnnotationParse {
    /**
     * 解析注解
     * 
     * @param targetClass
     *            目标类的class形式
     * @param methodName
     *            在客户端调用哪个方法,methodName就代表哪个方法
     * @return
     * @throws Exception
     */
    public static String parse(Class<?> targetClass, String methodName) throws Exception {
    	System.out.println("切面进入:"+targetClass);
        String methodAccess = "";
        Method[] methods = targetClass.getMethods();
        Method method = null;
        
        for(Method m:methods){
        	if(methodName.equals(m.getName())){
        		method = m;
        		break;
        	}
        }
        if(method.isAnnotationPresent(AnnotationCustom.class)){
        	AnnotationCustom a = method.getAnnotation(AnnotationCustom.class);
        	methodAccess = a.value();
        	System.out.println("方法自定义注解:"+methodAccess);
        }
        return methodAccess;
    }
}
