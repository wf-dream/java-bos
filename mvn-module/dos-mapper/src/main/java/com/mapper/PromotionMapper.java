package com.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.damain.Promotion;

public interface PromotionMapper {

	public List<Promotion> findall(Map<String, Object> paramap);
	
	public Long countPromotion();
	
	public void promotionSave(Promotion promotion);
	
	public void delete(String strs[]);
	
	public List<Promotion> listByPage();
	
	public void promotionQuartz(Date date);
}
