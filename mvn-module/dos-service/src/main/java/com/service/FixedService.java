package com.service;

import java.util.List;
import java.util.Map;

import com.damain.Fixed;

public interface FixedService {

	public List<Map<String, Object>> findall(Map<String, Object> map);
	
	public Long countFixed();
	
	public Fixed edit(Integer id);
	
	public void insert(Fixed fixed);
	
	public void update(Fixed fixed);
	
	public void delete(String[] strs);
	
	public List<Fixed> getFixedAreaName();
	
}
