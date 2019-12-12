package com.serviceimpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.damain.Area;
import com.mapper.AreaMapper;
import com.service.AreaService;

@Service
@Transactional
public class AreaServiceimpl implements AreaService {

	@Resource
	private AreaMapper areaMapper;
	
	@Override
	public List<Area> findall(Map<String, Object> paramMap) {
		int pageNum=Integer.parseInt(paramMap.get("page").toString())-1;
		int rows=Integer.parseInt(paramMap.get("rows").toString())-1;
		int startNum=pageNum*rows;
		paramMap.put("startNum", startNum);
		paramMap.put("rows", rows);
		return areaMapper.findall(paramMap);
	}

	@Override
	public Long count() {
		return areaMapper.count();
	}

	@Override
	public void insert(Area area) {
		areaMapper.insert(area);		
	}

	@Override
	public void update(Area area) {
		areaMapper.update(area);	
	}

	@Override
	public Area edit(Integer id) {		
		return areaMapper.edit(id);
	}

	@Override
	public void delete(String[] strs) {
		areaMapper.delete(strs);		
	}

	@Override
	public void saveList(List<Area> list) {
		for (Area area : list) {
			areaMapper.save(area);
		}		
	}

	@Override
	public List<Area> AreaList(Map<String, Object> map) {		
		return areaMapper.AreaList(map);
	}

	@Override
	public List<Area> getShowName() {		
		return areaMapper.getShowName();
	}

	
}
