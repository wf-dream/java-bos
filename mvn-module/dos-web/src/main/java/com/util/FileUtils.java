package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.damain.Area;

public class FileUtils {

	public static List<Area> xssfWork(String url) {
		List<Area> list=new ArrayList<>();
		try {
			//解析xls文件
			HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(new File(url)));
			//获取表格第一页sheet
			HSSFSheet sheet=workbook.getSheetAt(0);
			//获取所有的行
			int rows=sheet.getPhysicalNumberOfRows();
			for(int i=1;i<rows;i++){				
				Area area=new Area();
				String province="";
				String city="";
				String distrcit="";
				
				//获取每一行
				HSSFRow row=sheet.getRow(i);
				//获取所有列
				int cells=row.getPhysicalNumberOfCells();
				//遍历列
				for(int j=0;j<cells;j++){
					//获取单列
					HSSFCell cell=row.getCell(j);
					if(j==0){
						String areaCode=cell.getStringCellValue();
						area.setAreacode(areaCode);
					}else if (j==1) {
						province=cell.getStringCellValue();
						area.setProvince(province);
					}else if (j==2) {
						city=cell.getStringCellValue();
						area.setCity(city);
					}else if (j==3) {
						distrcit=cell.getStringCellValue();
						area.setDistrcit(distrcit);
					}else if (j==4){
						String postcode=cell.getStringCellValue();
						area.setPostcode(postcode);
					}					
				}
				//生成区域编码
				String[] strs=PinYin4jUtils.getHeadByString(province+city+distrcit);
				String shortcode=PinYin4jUtils.stringArrayToString(strs);
				area.setShortcode(shortcode);
				
				//城市编码
				String[] citys=PinYin4jUtils.stringToPinyin(city);
				String citycode=PinYin4jUtils.stringArrayToString(citys);
				area.setCitycode(citycode);
				
				//将对象放入集合中
				list.add(area);
			}	
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	
}
