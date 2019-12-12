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
import com.damain.Standar;
import com.service.StandarService;

@Controller
@RequestMapping("standard")
public class StandarCtrl {
	@Resource
	private StandarService standarService;
	
	@RequestMapping("list")
	public ModelAndView list() {	
		ModelAndView mav=new ModelAndView();		
		mav.setViewName("StandarJsp/standarlist");		
		return mav;
	}
	
	@RequestMapping(value="findall",produces="text/json;charset=utf-8")
	@ResponseBody
	public String findall(HttpServletRequest request,String page,String rows) {
		//数据
		String name=request.getParameter("name");
		String min_weight=request.getParameter("min_weight");
		String max_weight=request.getParameter("max_weight");
		//查询所有数据
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("page", page);
		paramMap.put("rows", rows);
		paramMap.put("name", name);
		paramMap.put("min_weight", min_weight);
		paramMap.put("max_weight", max_weight);
		//将集合传回
		List<Standar> list=standarService.findall(paramMap);			
		//获取总记录数
		Long count=standarService.countStandar();
		//向easyui中封装数据
		Map<String, Object> map=new HashMap<>();
		map.put("total", count);
		map.put("rows", list);
		String json=JSON.toJSONString(map);
		return json;
	}
	
	
	@RequestMapping(value="insert",produces="text/json;charset=utf-8")
	@ResponseBody
	public String insert(Standar standar){		
		Map<String, Object> map=new HashMap<>();
		try {
			if(standar.getId()==null){
				//保存standard保存
				standarService.insert(standar);
			}else{
				//修改收派信息
				standarService.update(standar);
			}			
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
			System.out.println(map);
			String json=JSON.toJSONString(map);	
			return json;		
	}
	
	@RequestMapping(value="findId",produces = "text/json;charset=utf-8")
	@ResponseBody
	public String findId(Integer id) {
		Standar standar=standarService.findId(id);	
		String json=JSON.toJSONString(standar);	
		return json;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public String delete(String ids) {
		Map<String, Object> map=new HashMap<>();
		String[] strs=ids.split(",");
		try {
			standarService.delete(strs);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		String json=JSON.toJSONString(map);
		return json;
	}
	
	@RequestMapping(value="getStandardAll",produces = "text/json;charset=utf-8")
	@ResponseBody
	public String getStandardAll() {
		List<Standar> list=standarService.getStandardAll();
		System.out.println(list);
		String json=JSON.toJSONString(list);
		return json;
	}

}
