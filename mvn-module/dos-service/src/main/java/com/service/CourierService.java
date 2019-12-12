package com.service;

import java.util.List;
import java.util.Map;

import com.damain.Courier;

public interface CourierService {
	public List<Map<String, Object>> findall(Map<String, Object> paramMap);
	
	public Long countCourier();
	
	public void insert(Courier courier);
	
	public Courier edit(Integer id);
	
	public void update(Courier courier);
	
	public void delete(String strs[]);
	
	public List<Courier> getCourierAll();
	
}
