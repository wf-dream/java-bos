package com.mapper.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.damain.system.User;

public interface UserMapper {

	public List<User> findall(Map<String, Object> paramMap);
	
	public Long countUser();
	
	public User edit(Integer id);
	
	public void insert(User user);
	
	public void update(User user);
	
	public void delete(String strs[]);
	
	public void userBindrole(@Param("userId")int userId,@Param("roleId")int roleId);
	
	public void deleteRole(int userId);

	public User userlogin(@Param("username")String username, @Param("password")String password);
	
	public User getUserByName(String username);

}
