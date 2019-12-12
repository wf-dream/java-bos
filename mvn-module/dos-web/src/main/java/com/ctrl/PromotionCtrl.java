package com.ctrl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.damain.Promotion;
import com.service.PromotionService;

@Controller
@RequestMapping("promotion")
public class PromotionCtrl {

	@Resource
	private PromotionService promotionService;
	
	@RequestMapping("list")
	public ModelAndView list(){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("promotion/promotion");
		return mav;
	}
	
	@RequestMapping("add")
	public ModelAndView add() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("promotion/promotion_add");
		return mav;
	}
	
	@RequestMapping(value="findall",produces = "text/json;charset=utf-8")
	@ResponseBody
	public String findall(HttpServletRequest request,String page,String rows) {
		Map<String, Object> paramMap=new HashMap<>();
		String title=request.getParameter("title");		
		paramMap.put("page", page);
		paramMap.put("rows", rows);
		paramMap.put("title", title);
		List<Promotion> list=promotionService.findall(paramMap);
		Long count=promotionService.countPromotion();
		Map<String, Object> map=new HashMap<>();
		map.put("total", count);
		map.put("rows", list);
		String json=JSON.toJSONString(map);		
		return json;		
	}
	
	@RequestMapping(value="promotionSave",produces = "text/json;charset=utf-8")
	@ResponseBody
	public ModelAndView promotionSave(HttpServletRequest request,MultipartFile titleImgFile) {
		ModelAndView modelAndView=new ModelAndView();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			String title=request.getParameter("title");			
			String activeScope=request.getParameter("activeScope");
			String startDate=request.getParameter("startDate");
			String endDate=request.getParameter("endDate");		
			String description=request.getParameter("description");		
			
			Promotion promotion=new Promotion();
			
			promotion.setTitle(title);	
			promotion.setActiveScope(activeScope);				
			promotion.setStartDate(sdf.parse(startDate));
			promotion.setEndDate(sdf.parse(endDate));
			promotion.setStatus(1);
			promotion.setDescription(description);
			
			//获取图片名称
			String fileName=titleImgFile.getOriginalFilename();
			//截取图片后缀
			String extName=fileName.substring(fileName.lastIndexOf("."));
			//图片新名称
			String newName=System.currentTimeMillis()+extName;
			//获取文件路径
			String url=request.getSession().getServletContext().getRealPath("uploadImg")+"/"+newName;
			//将文件存入文件夹
			titleImgFile.transferTo(new File(url));
			promotion.setTitleImg(request.getContextPath()+"/uploadImg/"+newName);
			promotionService.promotionSave(promotion);
			modelAndView.setViewName("promotion/promotion");
		} catch (Exception e) {		
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@RequestMapping(value="delete",produces = "text/json;charset=utf-8")
	@ResponseBody
	public String delete(String ids) {		
		Map<String, Object> map=new HashMap<>();
		String[] strs=ids.split(",");
		try {
			promotionService.delete(strs);
			map.put("success", true);
		} catch (Exception e) {		
			e.printStackTrace();
			map.put("success", false);
		}
		String json=JSON.toJSONString(map);
		return json;
	}
	
	
}
