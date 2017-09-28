package com.dhf.quartz;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Scheduler 四开球拉儿
 * QuartzJob.java
 * @author denghf
 * 2017年8月14日 下午4:21:07
 */
public class QuartzJob implements Job{
	private Log log =  LogFactoryImpl.getLog(QuartzJob.class);
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		com.dhf.quartz.Job job = (com.dhf.quartz.Job) context.getMergedJobDataMap().get("btcMap");
		
		try {
			//利用反射执行方法
			Class cls = Class.forName(job.getBeanClass());
			
			//1. 获取类中带有方法签名的mul方法,getMethod第一个参数为方法名,第二个参数为mul的参数类型数组
			//Method method = clz.getMethod("mul", new Class[]{double.class,double.class});
			Method method = cls.getMethod(job.getName(), new Class[]{String.class});
			
			//invoke 方法的第一个参数是被调用的对象,这里是静态方法故为null,第二个参数为给将被调用的方法传入的参数
			//第一种：反射执行的方法 getPrimaryKey() 改成静态的
			//第二种：在执行方法前先实例化类。m.invoke(mothed,null)改为m.invoke(c.newInstance(),null)或者m.invoke(new PrimaryKeyUtils(),null)
			method.invoke(cls.newInstance(), job.getNum());
			
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
