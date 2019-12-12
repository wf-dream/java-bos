package com.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.damain.Promotion;
import com.mapper.PromotionMapper;
import com.service.PromotionService;

@Service
@Transactional
public class PromotionServiceimpl implements PromotionService {

	@Resource
	private PromotionMapper promotionMapper;
	
	@Override
	public List<Promotion> findall(Map<String, Object> paramap) {
		int pageNum=Integer.parseInt(paramap.get("page").toString())-1;
		int rows=Integer.parseInt(paramap.get("rows").toString());
		int startNum=pageNum*rows;
		paramap.put("startNum", startNum);
		paramap.put("rows", rows);
		return promotionMapper.findall(paramap);
	}

	@Override
	public Long countPromotion() {		
		return promotionMapper.countPromotion();
	}

	@Override
	public void promotionSave(Promotion promotion) {
		promotionMapper.promotionSave(promotion);		
	}

	@Override
	public void delete(String[] strs) {
		promotionMapper.delete(strs);		
	}

	@Override
	public List<Promotion> listByPage() {	
		return promotionMapper.listByPage();
	}

	//定时任务
	@Override
	public void promotionQuartz(Date date) {		
		promotionMapper.promotionQuartz(date);
	}

}
