package com.dhf.quartz;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 定时调度器
 * SchedulerJob.java
 * @author denghf
 * 2017年8月14日 下午3:59:57
 */
public class SchedulerJob {

	private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	
	public static void addJob(String time,Job job,String taskname){
		try {
			Scheduler sched = schedulerFactory.getScheduler();
			JobDetail jd = new JobDetail(taskname, "BTCGroup", QuartzJob.class);// 任务名，任务组，任务执行类
			jd.getJobDataMap().put("btcMap", job);
			
			CronTrigger trigger = new CronTrigger(taskname, "BTCTrigger");// 触发器名,触发器组
			trigger.setCronExpression(time);// 触发器时间设定
			sched.scheduleJob(jd, trigger);
			
			// 启动
			if (!sched.isShutdown()) {
				sched.start();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
}
