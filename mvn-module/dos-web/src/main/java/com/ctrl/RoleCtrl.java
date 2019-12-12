package com.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.damain.system.Role;
import com.service.system.RoleService;

@Controller
@RequestMapping("role")
public class RoleCtrl {

	@Resource
	private RoleService roleService;
	
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("role/role");
		return mav;
	}
	
	@RequestMapping(value="findall",produces = "text/json;charset=utf-8")
	@ResponseBody
	public String findall(HttpServletRequest request,String page,String rows) {
		String name=request.getParameter("name");
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("page", page);
		paramMap.put("rows", rows);
		paramMap.put("name", name);
		List<Role> list=roleService.findall(paramMap);
		Long count=roleService.countRole();
		Map<String, Object> map=new HashMap<>();
		map.put("total", count);
		map.put("rows", list);
		String json=JSON.toJSONString(map);
		return json;
	}
	
	@RequestMapping(value="edit",produces = "text/json;charset=utf-8")
	@ResponseBody
	public String edit(Integer id) {
		Role role=roleService.edit(id);
		String json=JSON.toJSONString(role);
		return json;
	}
		
	@RequestMapping(value="save",produces = "text/json;charset=utf-8")
	@ResponseBody
	public String save(Role role) {
		Map<String, Object> map=new HashMap<>();
		try {
			if (role.getId()==null) {
				roleService.insert(role);
			}else{
				roleService.update(role);
			}
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", true);
		}
		String json=JSON.toJSONString(map);
		return json;
	}
	
	@RequestMapping(value="delete",produces = "text/json;charset=utf-8")
	@ResponseBody
	public String delete(String ids) {
		Map<String, Object> map=new HashMap<>();
		String[] strs=ids.split(",");
		try {
			roleService.delete(strs);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		String json=JSON.toJSONString(map);
		return json;
	}
	
	//角色绑定资源
	@RequestMapping(value="roleBindresource",produces = "text/json;charset=utf-8")
	@ResponseBody
	public String roleBindresource(String resIds,String roleId) {
		System.out.println(resIds);		
		Map<String, Object> map=new HashMap<>();
		try {
			roleService.roleBindresource(roleId, resIds);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);			
		}
		String json=JSON.toJSONString(map);	
		return json;
	}
	
	
}
