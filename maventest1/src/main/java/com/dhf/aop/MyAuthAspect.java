package com.dhf.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class MyAuthAspect {
	public Object isAspect(ProceedingJoinPoint joinPoint) throws Throwable{
			Class<?> targetClass = joinPoint.getTarget().getClass();
			String name = joinPoint.getSignature().getName();
			System.out.println("进入切面类的"+name+"方法");
			
			//得到该方法的访问权限
	        String methodAccess = AnnotationParse.parse(targetClass, name);
	        
	        boolean flag = false;
	        if("denghf".equals(methodAccess)){
	        	flag = true;
	        }else{
	        	flag = false;
	        }
	        
	        if (flag) {
	        	Object object = null;
	        	try {
	        		object = joinPoint.proceed();//调用目标方法 
				} catch (Exception e) {
					// TODO: handle exception
					//return "redirect:/user/findAllUser";
					return "redirect:/shiro/error";
				}
	        	return object;
	        } else {
	            System.out.println("你没有权限");
	            return "redirect:/shiro/error";
	        }
	}
}
