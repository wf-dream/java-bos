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
import com.damain.SubAreaFixed;
import com.service.SubAreaFixedService;

@Controller
@RequestMapping("subareafixed")
public class SubAreaFixedCtrl {

	@Resource
	private SubAreaFixedService subAreaFixedService;
	
	@RequestMapping("list")
	@ResponseBody
	public ModelAndView list() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("subareafixed/subareafixed");				
		return mav;
	}
	
	
	@RequestMapping(value="findall",produces="text/json;charset=utf-8")
	@ResponseBody
	public String findall(HttpServletRequest request,String page,String rows) {
		String KeyWords=request.getParameter("KeyWords");
		String AssitKeyWords=request.getParameter("AssitKeyWords");
		Map<String, Object> paramMap=new HashMap<>();		
		paramMap.put("page", page);
		paramMap.put("rows", rows);
		paramMap.put("KeyWords", KeyWords);
		paramMap.put("value", AssitKeyWords);
		List<Map<String, Object>> list=subAreaFixedService.findall(paramMap);
		Long count=subAreaFixedService.countSub();
		Map<String, Object> map=new HashMap<>();
		map.put("total", count);
		map.put("rows", list);		
		String json=JSON.toJSONString(list);
		return json;		
	}
	
	@RequestMapping(value="edit",produces="text/json;charset=utf-8")
	@ResponseBody
	public String edit(Integer id) {
		SubAreaFixed subAreaFixed=subAreaFixedService.edit(id);
		String json=JSON.toJSONString(subAreaFixed);		
		return json;
	}
	
	@RequestMapping(value="save",produces="text/json;charset=utf-8")
	@ResponseBody
	public String save(SubAreaFixed subAreaFixed) {
		System.out.println(subAreaFixed);
		Map<String, Object> map=new HashMap<>();
		try {
			if(subAreaFixed.getId()==null){
				subAreaFixedService.insert(subAreaFixed);	
				
			}else{
				subAreaFixedService.update(subAreaFixed);
			}
			map.put("success", true);
		} catch (Exception e) {			
			e.printStackTrace();
			map.put("success", false);
		}
		String json=JSON.toJSONString(map);
		return json;
	}
	
	
}
