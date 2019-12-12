package com.serviceimpl.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.damain.system.Role;
import com.mapper.system.RoleMapper;
import com.service.system.RoleService;

@Service
@Transactional
public class RoleServiceimpl implements RoleService {

	@Resource
	private RoleMapper roleMapper;
	
	@Override
	public List<Role> findall(Map<String, Object> paramMap) {
		int pageNum=Integer.parseInt(paramMap.get("page").toString())-1;
		int rows=Integer.parseInt(paramMap.get("rows").toString());
		int startNum=pageNum*rows;
		paramMap.put("startNum", startNum);
		paramMap.put("rows", rows);
		return roleMapper.findall(paramMap);
	}

	@Override
	public Long countRole() {		
		return roleMapper.countRole();
	}

	@Override
	public Role edit(Integer id) {
		return roleMapper.edit(id);
	}

	@Override
	public void insert(Role role) {
		roleMapper.insert(role);
	}

	@Override
	public void update(Role role) {
		roleMapper.update(role);
	}

	@Override
	public void delete(String[] strs) {
		roleMapper.delete(strs);
	}

	@Override
	public void roleBindresource(String roleId, String resourceId) {
		int rid=Integer.parseInt(roleId);
		//清空之前的资源
		roleMapper.deleteResource(rid);
		
		String[] resids=resourceId.split(",");
		for(int i=0;i<resids.length;i++){
			//绑定资源
			roleMapper.roleBindresource(rid, Integer.parseInt(resids[i]));
		}
	}

}
