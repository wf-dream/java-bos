package com.mapper;

import java.util.List;
import java.util.Map;

import com.damain.Area;

public interface AreaMapper {

	public List<Area> findall(Map<String, Object> paramMap);
	
	public Long count();
	
	public void insert(Area area);
	
	public Area edit(Integer id);
	
	public void update(Area area);
	
	public void delete(String[] strs);
	
	public void save(Area area);
	
	public List<Area> AreaList(Map<String, Object> map);
	
	public List<Area> getShowName();
}
