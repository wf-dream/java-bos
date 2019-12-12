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
import com.damain.Courier;
import com.service.CourierService;

@Controller
@RequestMapping(value="courier",produces="text/json;charset=utf-8")
public class CourierCtrl {

	@Resource
	private CourierService courierService;
	
	@RequestMapping("list")
	@ResponseBody
	public ModelAndView list() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("courier/courier");		
		return mav;
	}
	
	@RequestMapping("findall")
	@ResponseBody
	public String findall(HttpServletRequest request,String page,String rows) {
		String courierNum=request.getParameter("courierNum");
		String cname=request.getParameter("cname");
		Map<String, Object> paramMap=new HashMap<>();		
		paramMap.put("page", page);
		paramMap.put("rows", rows);
		paramMap.put("courierNum", courierNum);
		paramMap.put("value", cname);
		List<Map<String, Object>> list=courierService.findall(paramMap);
		Long count=courierService.countCourier();
		Map<String, Object> map=new HashMap<>();
		map.put("total", count);
		map.put("rows", list);		
		String json=JSON.toJSONString(list);
		return json;		
	}
	
	@RequestMapping(value="edit",produces = "text/json;charset=utf-8")
	@ResponseBody
	public String edit(Integer id) {
		Courier courier=courierService.edit(id);
		String json=JSON.toJSONString(courier);		
		return json;
	}
	
	@RequestMapping("save")
	@ResponseBody
	public String save(Courier courier) {
		Map<String, Object> map=new HashMap<>();		
		try {
			if(courier.getId()==null){
				courierService.insert(courier);
			}else{
				courierService.update(courier);
			}
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
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
			courierService.delete(strs);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		String json=JSON.toJSONString(map);		
		return json;
	}
	
	@RequestMapping(value="getCourierAll",produces = "text/json;charset=utf-8")
	@ResponseBody
	public String getCourierAll() {
		List<Courier> list=courierService.getCourierAll();			
		String json=JSON.toJSONString(list);
		return json;
	}
	
}
