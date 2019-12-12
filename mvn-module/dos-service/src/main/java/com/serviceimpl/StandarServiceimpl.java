package com.serviceimpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.damain.Standar;
import com.mapper.StandardMapper;
import com.service.StandarService;

@Service
public class StandarServiceimpl implements StandarService {

	@Resource
	private StandardMapper standardMapper;
	
	@Override
	public List<Standar> findall(Map<String, Object> paramMap) {
		int pageNum=Integer.parseInt(paramMap.get("page").toString())-1;
		int rows=Integer.parseInt(paramMap.get("rows").toString());
		int startNum=pageNum*rows;
		paramMap.put("startNum", startNum);
		paramMap.put("rows", rows);
		return standardMapper.findall(paramMap);
	}

	@Override
	public Long countStandar() {		
		return standardMapper.countStandard();
	}

	@Override
	public void insert(Standar standar) {
		standardMapper.insert(standar);		
	}

	@Override
	public Standar findId(Integer id) {		
		return standardMapper.findId(id);
	}

	@Override
	public void update(Standar standar) {
		standardMapper.update(standar);		
	}

	@Override
	public void delete(String[] strs) {
		standardMapper.delete(strs);
	}

	@Override
	public List<Standar> getStandardAll() {
		return standardMapper.getStandardAll();
	}

}
