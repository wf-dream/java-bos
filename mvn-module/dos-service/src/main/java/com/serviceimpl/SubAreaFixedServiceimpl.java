package com.serviceimpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.damain.SubAreaFixed;
import com.mapper.SubAreaFixedMapper;
import com.service.SubAreaFixedService;

@Service
@Transactional
public class SubAreaFixedServiceimpl implements SubAreaFixedService {

	@Resource
	private SubAreaFixedMapper subAreaFixedMapper;
	
	@Override
	public List<Map<String, Object>> findall(Map<String, Object> paramMap) {
		int pageNum =Integer.parseInt(paramMap.get("page").toString())-1;
		int rows=Integer.parseInt(paramMap.get("rows").toString());
		int startNum=pageNum*rows;
		paramMap.put("startNum", startNum);
		paramMap.put("rows", rows);
		return subAreaFixedMapper.findall(paramMap);	
	}

	@Override
	public Long countSub() {		
		return subAreaFixedMapper.countSub();
	}

	@Override
	public SubAreaFixed edit(Integer id) {
		return subAreaFixedMapper.edit(id);
	}

	@Override
	public void insert(SubAreaFixed subAreaFixed) {
		subAreaFixedMapper.insert(subAreaFixed);		
	}
	

	@Override
	public void update(SubAreaFixed subAreaFixed) {
		subAreaFixedMapper.update(subAreaFixed);		
	}
	

}
