package com.demo;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuarztDemo {

	public static void main(String[] args) throws Exception {
		
		//创建任务
		JobDetail job=JobBuilder.newJob(MyJob.class).build();
		//什么时候执行(执行5次，每过3秒钟后执行一次)
		Trigger trigger=TriggerBuilder
				.newTrigger()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForTotalCount(5, 3))
				.build();
		//创建一个schedule工厂
		SchedulerFactory factory=new StdSchedulerFactory();
		Scheduler scheduler=factory.getScheduler();
		scheduler.scheduleJob(job, trigger);
		
		//启动
		scheduler.start();
	}
}
