package com.serviceimpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.damain.Courier;
import com.mapper.CourierMapper;
import com.service.CourierService;

@Service
@Transactional
public class CourierServiceimpl implements CourierService {

	@Resource
	private CourierMapper courierMapper;
	
	@Override
	public List<Map<String, Object>> findall(Map<String, Object> paramMap) {	
		int pageNum =Integer.parseInt(paramMap.get("page").toString())-1;
		int rows=Integer.parseInt(paramMap.get("rows").toString());
		int startNum=pageNum*rows;
		paramMap.put("startNum", startNum);
		paramMap.put("rows", rows);
		return courierMapper.findall(paramMap);
	}
	
	public Long countCourier() {
		return courierMapper.countCourier();
	}

	@Override
	public void insert(Courier courier) {
		courierMapper.insert(courier);
		
	}

	@Override
	public Courier edit(Integer id) {		
		return courierMapper.edit(id);
	}

	@Override
	public void update(Courier courier) {
		courierMapper.update(courier);
		
	}

	@Override
	public void delete(String[] strs) {
		courierMapper.delete(strs);
		
	}

	@Override
	public List<Courier> getCourierAll() {
		return courierMapper.getCourierAll();
	}

}
