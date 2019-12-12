package com.service;

import java.util.List;
import java.util.Map;

import com.damain.Standar;

public interface StandarService {

	public List<Standar> findall(Map<String, Object> paramMap);
	
	public Long countStandar();
	
	public void insert(Standar standar);
	
	public Standar findId(Integer id);
	
	public void update(Standar standar);
	
	public void delete(String[] strs);
	
	public List<Standar> getStandardAll();
}
