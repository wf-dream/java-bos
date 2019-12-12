package com.service;

import java.util.List;
import java.util.Map;

import com.damain.Area;

public interface AreaService {

	public List<Area> findall(Map<String, Object> paramMap);
	
	public Long count();
	
	public void insert(Area area);
	
	public void update(Area area);
	
	public Area edit(Integer id);
	
	public void delete(String[] strs);
	
	public void saveList(List<Area> list);
	
	public List<Area> AreaList(Map<String, Object> map);
	
	public List<Area> getShowName();
}
