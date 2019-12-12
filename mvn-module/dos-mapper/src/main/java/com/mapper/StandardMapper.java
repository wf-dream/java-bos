package com.mapper;

import java.util.List;
import java.util.Map;


import com.damain.Standar;

public interface StandardMapper {

	public List<Standar> findall(Map<String, Object> paramMap);	
	
	public Long countStandard();
	
	public void insert(Standar standar);
	
	public Standar findId(Integer id);
	
	public void update(Standar standar);
	
	public void delete(String[] strs);
	
	public List<Standar> getStandardAll();
	
}
