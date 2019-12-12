package com.service.system;

import java.util.List;
import java.util.Map;

import com.damain.system.Resourcee;
import com.damain.system.User;

public interface ResourceeService {

	public List<Resourcee> findall(Map<String, Object> paramMap);
	
	public Long countResourcee();

	public List<Map<String, Object>> loginMenu(Integer id);
	
	public List<Map<String, Object>> getMenuByUserId(Integer id);

}
