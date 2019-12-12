package com.job;


import java.util.Date;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.service.PromotionService;

public class PromotionJob implements Job {

	@Resource
	private PromotionService promotionService;
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//更改任务状态	
		promotionService.promotionQuartz(new Date());	
	}

}
