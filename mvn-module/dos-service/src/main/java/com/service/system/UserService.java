package com.service.system;

import java.util.List;
import java.util.Map;

import com.damain.system.User;

public interface UserService {

	public List<User> findall(Map<String, Object> paramMap);
	
	public Long countUser();
	
	public User edit(Integer id);
	
	public void insert(User user);
	
	public void update(User user);
	
	public void delete(String strs[]);
	
	public void userBindrole(String userId,String roleId);

	public User userlogin(String username, String password);
	
	public User getUserByName(String username);
	
}
