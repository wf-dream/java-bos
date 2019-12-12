package com.ctrl;

import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriUtils;

import com.alibaba.fastjson.JSON;
import com.damain.Area;
import com.service.AreaService;
import com.util.FileUtils;

@Controller()
@RequestMapping("area")
public class AreaCtrl {

	@Resource
	private AreaService areaService;
	
	@RequestMapping("list")
	public ModelAndView list() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("area/area");
		return mav;
	}
	
	@RequestMapping(value="findall",produces="text/json;charset=utf-8")
	@ResponseBody
	public String findall(HttpServletRequest request,String page,String rows) {
		 String province=request.getParameter("province");
		 String city=request.getParameter("city");
		 String distrcit=request.getParameter("distrcit");
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("province", province);
		paramMap.put("city", city);
		paramMap.put("distrcit", distrcit);
		paramMap.put("page", page);
		paramMap.put("rows", rows);
		List<Area> list=areaService.findall(paramMap);
		Long count=areaService.count();
		Map<String, Object> map=new HashMap<>();
		map.put("total", count);
		map.put("rows", list);		
		String json=JSON.toJSONString(map);
		return json;
	}
	
	@RequestMapping(value="save",produces="text/json;charset=utf-8")
	@ResponseBody
	public String save(Area area) {
		Map<String, Object> map=new HashMap<>();
		try {
			if(area.getId()==null){
				areaService.insert(area);
			}else{
				areaService.update(area);
			}			
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}		
		String json=JSON.toJSONString(map);	
		return json;
	}
	
	@RequestMapping(value="edit",produces="text/json;charset=utf-8")
	@ResponseBody
	public String edit(Integer id) {
		Area area=areaService.edit(id);
		String json=JSON.toJSONString(area);
		return json;
	}
	
	@RequestMapping(value="delete",produces = "text/json;charset=utf-8")
	@ResponseBody
	public String delete(String ids) {
		Map<String, Object> map=new HashMap<>();
		String[] strs=ids.split(",");
		try {
			areaService.delete(strs);
			map.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
		}
		String json=JSON.toJSONString(map);		
		return json;
	}
	
	@RequestMapping(value="improtExcel",produces = "text/json;charset=utf-8")
	@ResponseBody
	public String improtExcel(@RequestParam MultipartFile file,HttpServletRequest request) {		
		Map<String, Object> map=new HashMap<>();
		//获取文件名称
		String fileName=file.getOriginalFilename();
		//获取文件路径
		String location=request.getSession().getServletContext().getRealPath("upload");
		//拼接路径
		String url=location+"/"+System.currentTimeMillis()+fileName;
		try {
			//将文件放入路径		
			file.transferTo(new File(url));
			List<Area> list=FileUtils.xssfWork(url);
			areaService.saveList(list);			
			map.put("success", true);			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);			
		}		
		String json=JSON.toJSONString(map);	
		return json;
	}
	
	@RequestMapping("exportExcel")
	public void exportExcel(String province,String city,String distrcit,HttpServletResponse response) {		
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("province", province);
		paramMap.put("city", city);
		paramMap.put("distrcit", distrcit);
		List<Area> list=areaService.AreaList(paramMap);
		//创建一个工作簿
		HSSFWorkbook hssfWorkbook=new HSSFWorkbook();
		//创建页面
		HSSFSheet sheet=hssfWorkbook.createSheet();
		//创建第一行
		HSSFRow fRow=sheet.createRow(0);
		//对第一行进行设置列（表头）
		fRow.createCell(0).setCellValue("区域编码");
		fRow.createCell(1).setCellValue("省份");
		fRow.createCell(2).setCellValue("城市");
		fRow.createCell(3).setCellValue("区域");	
		fRow.createCell(4).setCellValue("邮编");
		fRow.createCell(5).setCellValue("城市编码");
		fRow.createCell(6).setCellValue("区域简码");	
		for(int i=1;i<list.size();i++){
			//获取行
			HSSFRow row=sheet.createRow(i);
			//获取集合中的对象
			Area area=list.get(i-1);
			//进行列赋值
			row.createCell(0).setCellValue(area.getAreacode());
			row.createCell(1).setCellValue(area.getProvince());
			row.createCell(2).setCellValue(area.getCity());
			row.createCell(3).setCellValue(area.getDistrcit());
			row.createCell(4).setCellValue(area.getPostcode());
			row.createCell(5).setCellValue(area.getCitycode());
			row.createCell(6).setCellValue(area.getShortcode());			
		}
		try {
			OutputStream out=response.getOutputStream();
			//文件名
			String fileName="区域信息表.xls";
			//下载弹出框
			response.setHeader("Content-Disposition", "attachment;filename="+UriUtils.encode(fileName, "utf-8"));
			hssfWorkbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="getShowName",produces = "text/json;charset=utf-8")
	@ResponseBody
	public String getShowName() {
		List<Area> list=areaService.getShowName();
		String json=JSON.toJSONString(list);		
		return json;
	}
	
}
