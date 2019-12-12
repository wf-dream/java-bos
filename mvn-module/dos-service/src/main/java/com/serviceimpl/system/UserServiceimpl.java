package com.serviceimpl.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.damain.system.User;
import com.mapper.system.UserMapper;
import com.service.system.UserService;

@Service
@Transactional
public class UserServiceimpl implements UserService {

	@Resource
	private UserMapper userMapper;
	
	@Override
	public List<User> findall(Map<String, Object> paramMap) {
		int pageNum=Integer.parseInt(paramMap.get("page").toString())-1;
		int rows=Integer.parseInt(paramMap.get("rows").toString());
		int startNum=pageNum*rows;
		paramMap.put("startNum", startNum);
		paramMap.put("rows", rows);
		return userMapper.findall(paramMap);
	}

	@Override
	public Long countUser() {
		return userMapper.countUser();
	}

	@Override
	public User edit(Integer id) {
		return userMapper.edit(id);
	}

	@Override
	public void insert(User user) {
		userMapper.insert(user);
	}

	@Override
	public void update(User user) {
		userMapper.update(user);
	}

	@Override
	public void delete(String[] strs) {
		userMapper.delete(strs);
	}

	@Override
	public void userBindrole(String userId, String roleId) {
		int uid=Integer.parseInt(userId);
		//清除之前绑定的角色
		userMapper.deleteRole(uid);
		String[] rids=roleId.split(",");
		//绑定角色
		for(int i=0;i<rids.length;i++){
			userMapper.userBindrole(uid, Integer.parseInt(rids[i]));
		}
	}

	@Override
	public User userlogin(String username, String password) {
		return userMapper.userlogin(username, password);
	}

	@Override
	public User getUserByName(String username) {
		return userMapper.getUserByName(username);
	}

}
