package com.mapper.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.damain.system.Role;

public interface RoleMapper {

	public List<Role> findall(Map<String, Object> paramMap);
	
	public Long countRole();
	
	public Role edit(Integer id);
	
	public void insert(Role role);
	
	public void update(Role role);
	
	public void delete(String[] strs);
	
	public void roleBindresource(@Param("roleId")int roleId,@Param("resourceId")int resourceId);
	
	public void deleteResource(int roleId);
	
}
