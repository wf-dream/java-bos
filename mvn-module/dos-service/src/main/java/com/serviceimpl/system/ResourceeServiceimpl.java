package com.serviceimpl.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.damain.system.Resourcee;
import com.damain.system.User;
import com.mapper.system.ResourceeMapper;
import com.service.system.ResourceeService;

@Service
@Controller
public class ResourceeServiceimpl implements ResourceeService {

	@Resource
	private ResourceeMapper resourceeMapper;
	
	@Override
	public List<Resourcee> findall(Map<String, Object> paramMap) {
		int pageNum=Integer.parseInt(paramMap.get("page").toString())-1;
		int rows=Integer.parseInt(paramMap.get("rows").toString());
		int startNum=pageNum*rows;
		paramMap.put("startNum", startNum);
		paramMap.put("rows", rows);
		return resourceeMapper.findall(paramMap);
	}

	@Override
	public Long countResourcee() {
		return resourceeMapper.countResourcee();
	}

	@Override
	public List<Map<String, Object>> loginMenu(Integer id) {
		return resourceeMapper.loginMenu(id);
	}

	@Override
	public List<Map<String, Object>> getMenuByUserId(Integer id) {
		return resourceeMapper.getMenuByUserId(id);
	}

}
