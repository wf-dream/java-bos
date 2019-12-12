package com.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.damain.system.User;
import com.service.system.UserService;

@Controller
@RequestMapping("user")
public class UserCtrl {

	@Resource
	private UserService userService;
	
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("user/user");
		return mav;
	}
	
	@RequestMapping(value="findall",produces="text/json;charset=utf-8")
	@ResponseBody
	public String findall(HttpServletRequest request,String page,String rows) {		
		String username=request.getParameter("username");
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("page", page);
		paramMap.put("rows", rows);
		paramMap.put("username", username);
		List<User> list=userService.findall(paramMap);
		Long count=userService.countUser();
		Map<String, Object> map=new HashMap<>();
		map.put("total", count);
		map.put("rows", list);
		String json=JSON.toJSONString(map);
		return json;
	}
	
	@RequestMapping(value="edit",produces="text/json;charset=utf-8")
	@ResponseBody
	public String edit(Integer id) {
		User user=userService.edit(id);
		String json=JSON.toJSONString(user);	
		return json;
	}
	
	@RequestMapping(value="save",produces="text/json;charset=utf-8")
	@ResponseBody
	public String save(User user) {
		Map<String, Object> map=new HashMap<>();
		try {
			if (user.getId()==null){
				userService.insert(user);
			}else{
				userService.update(user);
			}
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		String json=JSON.toJSONString(map);
		return json;
	}
	
	@RequestMapping(value="delete",produces="text/json;charset=utf-8")
	@ResponseBody
	public String delete(String ids) {
		Map<String, Object> map=new HashMap<>();
		String[] strs=ids.split(",");
		try {
			userService.delete(strs);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		String json=JSON.toJSONString(map);
		return json;
	}
	
	//用户绑定角色
	@RequestMapping(value="userBindrole",produces="text/json;charset=utf-8")
	@ResponseBody
	public String userBindrole(String userId,String roleIds) {
		Map<String, Object> map=new HashMap<>();
		try {
			userService.userBindrole(userId, roleIds);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", true);
		}
		String json=JSON.toJSONString(map);
		return json;
	}
	
	//员工登录
	@RequestMapping(value="userlogin",produces="text/json;charset=utf-8")
	@ResponseBody
	public String userlogin(HttpServletRequest request,String username,String password,String validcode) {
		Map<String, Object> map=new HashMap<>();
		String code=request.getSession().getAttribute("key").toString();
		//判断验证码是否正确
		if (!code.equals(validcode)) {
			map.put("success", false);
			map.put("msg", "验证码错误");
		}else{
			//获取subject
			Subject subject=SecurityUtils.getSubject();
			//设置登录的用户名和密码
			UsernamePasswordToken token=new UsernamePasswordToken(username,password);
			try {
				subject.login(token);
				//取出当前用户
				Object userlogin=subject.getPrincipal();
				request.getSession().setAttribute("userlogin", userlogin);
				map.put("success", true);
			} catch (UnknownAccountException e) {
				map.put("success", false);
				map.put("msg", "认证失败,用户名错误");
			}catch (IncorrectCredentialsException e) {
				map.put("success", false);
				map.put("msg", "认证失败,密码错误");
			}catch (Exception e) {
				map.put("success", false);
				map.put("msg",e.getMessage());
			}
		}
		String json=JSON.toJSONString(map);
		return json;
	}
	
	
}
