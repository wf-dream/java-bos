package com.mapper;

import java.util.List;
import java.util.Map;

import com.damain.SubAreaFixed;

public interface SubAreaFixedMapper {

	public List<Map<String, Object>> findall(Map<String, Object> map);
	
	public Long countSub();
	
	public SubAreaFixed edit(Integer id);		
	
	public void insert(SubAreaFixed subAreaFixed);
	
	public void update(SubAreaFixed subAreaFixed);

}
