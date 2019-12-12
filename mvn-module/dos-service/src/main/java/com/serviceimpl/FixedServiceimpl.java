package com.serviceimpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.damain.Fixed;
import com.mapper.FixedMapper;
import com.service.FixedService;

@Service
@Transactional
public class FixedServiceimpl implements FixedService{

	@Resource
	private FixedMapper fixedMapper;
	
	@Override
	public List<Map<String, Object>> findall(Map<String, Object> paramMap) {
		int pageNum =Integer.parseInt(paramMap.get("page").toString())-1;
		int rows=Integer.parseInt(paramMap.get("rows").toString());
		int startNum=pageNum*rows;
		paramMap.put("startNum", startNum);
		paramMap.put("rows", rows);
		return fixedMapper.findall(paramMap);
	}

	@Override
	public Long countFixed() {	
		return fixedMapper.countFixed();
	}

	@Override
	public Fixed edit(Integer id) {		
		return fixedMapper.edit(id);
	}

	@Override
	public void insert(Fixed fixed) {
		fixedMapper.insert(fixed);
		
	}

	@Override
	public void update(Fixed fixed) {
		fixedMapper.update(fixed);
		
	}

	@Override
	public void delete(String[] strs) {
		fixedMapper.delete(strs);
		
	}

	@Override
	public List<Fixed> getFixedAreaName() {
		return fixedMapper.getFixedAreaName();
	}

}
