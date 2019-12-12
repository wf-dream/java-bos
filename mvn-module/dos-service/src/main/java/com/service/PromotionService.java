package com.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.damain.Promotion;

public interface PromotionService {
		
	public List<Promotion> findall(Map<String, Object> paramap);
	
	public Long countPromotion();
	
	public void promotionSave(Promotion promotion);
	
	public void delete(String[] strs);
	
	@GET
	@Path("listByPage")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Promotion> listByPage();
	
	//定时任务
	public void promotionQuartz(Date date);
}
