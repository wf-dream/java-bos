package com.mapper;

import java.util.List;
import java.util.Map;

import com.damain.Courier;

public interface CourierMapper {
	public List<Map<String, Object>> findall(Map<String, Object> map);
	
	public Long countCourier();
	
	public Courier edit(Integer id);
	
	public void insert(Courier courier);
	
	public void update(Courier courier);
	
	public void delete(String strs[]);
	
	public List<Courier> getCourierAll();
}
