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
import com.damain.system.Resourcee;
import com.damain.system.User;
import com.service.system.ResourceeService;

@Controller
@RequestMapping("resourcee")
public class ResourceeCtrl {

	@Resource
	private ResourceeService resourceeService;
	
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("resource/resource");
		return mav;
	}
	
	@RequestMapping(value="findall",produces = "text/json;charset=utf-8")
	@ResponseBody
	public String findall(String page,String rows) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("page", page);
		paramMap.put("rows", rows);
		List<Resourcee> list=resourceeService.findall(paramMap);
		Long count=resourceeService.countResourcee();
		Map<String, Object> map=new HashMap<>();
		map.put("total", count);
		map.put("rows", list);
		String json=JSON.toJSONString(map);
		return json;
	}
	
	//获取菜单节点
	@RequestMapping(value="loginMenu",produces = "text/json;charset=utf-8")
	@ResponseBody
	public String loginMenu(HttpServletRequest request) {
		User user=(User) request.getSession().getAttribute("userlogin");
		List<Map<String, Object>> list=resourceeService.loginMenu(user.getId());
		String json=JSON.toJSONString(list);
		return json;
	}
	
	
}
