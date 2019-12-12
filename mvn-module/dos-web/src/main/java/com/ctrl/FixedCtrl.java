package com.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.damain.Fixed;
import com.domain.Customer;
import com.service.FixedService;

@Controller
@RequestMapping("fixed")
public class FixedCtrl {

	@Resource
	private FixedService fixedService;

	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("fixed/fixed");
		return mav;
	}

	@RequestMapping(value = "findall", produces = "text/json;charset=utf-8")
	@ResponseBody
	public String findall(HttpServletRequest request, String page, String rows) {
		String FixedAreaName = request.getParameter("FixedAreaName");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("page", page);
		paramMap.put("rows", rows);
		paramMap.put("FixedAreaName", FixedAreaName);
		List<Map<String, Object>> list = fixedService.findall(paramMap);
		Long count = fixedService.countFixed();
		Map<String, Object> map = new HashMap<>();
		map.put("total", count);
		map.put("rows", list);
		String json = JSON.toJSONString(list);
		return json;
	}

	@RequestMapping(value = "edit", produces = "text/json;charset=utf-8")
	@ResponseBody
	public String edit(Integer id) {
		Fixed fixed = fixedService.edit(id);
		String json = JSON.toJSONString(fixed);
		return json;
	}

	@RequestMapping(value = "save", produces = "text/json;charset=utf-8")
	@ResponseBody
	public String save(Fixed fixed) {
		Map<String, Object> paramMap = new HashMap<>();
		try {
			if (fixed.getId() == null) {
				fixedService.insert(fixed);
			} else {
				fixedService.update(fixed);
			}
			paramMap.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			paramMap.put("success", false);
		}
		String json = JSON.toJSONString(paramMap);
		return json;
	}

	@RequestMapping(value = "delete", produces = "text/json;charset=utf-8")
	@ResponseBody
	public String delete(String ids) {
		Map<String, Object> map = new HashMap<>();
		String[] strs = ids.split(",");
		try {
			fixedService.delete(strs);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		String json = JSON.toJSONString(map);
		return json;
	}

	@RequestMapping(value = "findNoAasociateCutomer", produces = "text/json;charset=utf-8")
	@ResponseBody
	public String findNoAasociateCutomer() {
		List<Customer> list = (List<Customer>) WebClient
				.create("http://localhost:9081/crm-web/service/customerService/findNoAassociaCustomer")
				.type(MediaType.APPLICATION_JSON)
				.accept("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
				.getCollection(Customer.class);
		String json = JSON.toJSONString(list);
		return json;
	}

	@RequestMapping(value = "findHasAasociateCustomerByFixedAreaId", produces = "text/json;charset=utf-8")
	@ResponseBody
	public String findHasAasociateCustomerByFixedAreaId(String id) {		
		int fixed_area_id = Integer.parseInt(id.toString());	
		List<Customer> list = (List<Customer>) WebClient
				.create("http://lo"
						+ "calhost:9081/crm-web/service/customerService/findHasAassociaCustomerByFixedAreaId/"
						+ fixed_area_id)
				.accept("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
				.type(MediaType.APPLICATION_JSON).getCollection(Customer.class);
		String json = JSON.toJSONString(list);
		
		return json;
	}
	
	//关联客户
	@RequestMapping("assoctiateCutomerByFxiedArea")
	@ResponseBody
	public String assoctiateCutomerByFxiedArea(String customerIds,String FixedAreaId) {	
		
		Map<String, Object> map=new HashMap<>();
		try {
			WebClient.create("http://localhost:9081/crm-web/service/customerService/assoctiateCutomerByFxiedArea?customerIds="
		+customerIds+"&FixedAreaId="+FixedAreaId).put(null);
			map.put("success", true);
		} catch (Exception e) {			
			e.printStackTrace();
			map.put("success", false);
		}		
		String json=JSON.toJSONString(map);
		return json;
	}
	
	@RequestMapping(value="getFixedAreaName", produces = "text/json;charset=utf-8")
	@ResponseBody
	public String getFixedAreaName() {
		System.out.println(1);
		List<Fixed> list=fixedService.getFixedAreaName();
		String json=JSON.toJSONString(list);
		System.out.println(json);
		return json;
	}
	
}