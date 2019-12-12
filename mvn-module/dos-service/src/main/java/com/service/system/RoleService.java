package com.service.system;

import java.util.List;
import java.util.Map;

import com.damain.system.Role;

public interface RoleService {

	public List<Role> findall(Map<String, Object> paramMap);
	
	public Long countRole();
	
	public Role edit(Integer id);
	
	public void insert(Role role);
	
	public void update(Role role);
	
	public void delete(String[] strs);
	
	public void roleBindresource(String roleId,String resourceId);
}
