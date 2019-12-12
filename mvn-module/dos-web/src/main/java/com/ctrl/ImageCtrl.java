package com.ctrl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("image")
public class ImageCtrl {

	@RequestMapping("upload")
	@ResponseBody
	public String upload(HttpServletRequest request,MultipartFile imgFile) {		
		Map<String, Object> map=new HashMap<String, Object>();
		//获取文件名字
		String fileName=imgFile.getOriginalFilename();
		//截取文件后缀
		String extName=fileName.substring(fileName.lastIndexOf("."));
		//时间戳
		String time=System.currentTimeMillis()+"";
		//文件上传后的名字
		String newName=time+extName;
		//文件路径
		String url=request.getSession().getServletContext().getRealPath("uploadImg")+"/"+newName;
		try {
			imgFile.transferTo(new File(url));
			String contextPath=request.getContextPath()+"/"+"uploadImg"+"/"+newName;
			map.put("error", 0);
			map.put("url", contextPath);
		} catch (IOException e) {		
			e.printStackTrace();
			map.put("error", 1);
			map.put("message", e.getMessage());
		}
		String json=JSON.toJSONString(map);
		return json;
	}
	
	@RequestMapping(value="manager",produces = "text/json;charset=utf-8")
	@ResponseBody
	public String manager(HttpServletRequest request) {
		//可以显示的图片扩展名
		String[] fileTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};		
		//获取upload目录的绝对路径
		String currentPath=request.getSession().getServletContext().getRealPath("uploadImg");
		//设置upload目录
		File currentPathFile = new File(currentPath);					
		//遍历目录取的文件信息
		//设计一个List集合存放需要返回的所有文件列表信息
		List<Hashtable> fileList = new ArrayList<Hashtable>();
		//listFiles():　获取目录下的所有文件
		if(currentPathFile.listFiles() != null) {
			//遍历所有文件列表
			for (File file : currentPathFile.listFiles()) {
				//创建Hashtable集合存放每个文件的信息
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				
				//********开始：封装每个文件的信息 *********
				//获取文件名称
				String fileName = file.getName();
				//判断是否为文件夹
				if(file.isDirectory()) {
					//是否为文件夹
					hash.put("is_dir", true);
					//是否还有子文件
					hash.put("has_file", (file.listFiles() != null));
					//文件大小
					hash.put("filesize", 0L);
					//是否为图片
					hash.put("is_photo", false);
					//文件类型
					hash.put("filetype", "");
				} else if(file.isFile()){  
					//文件			
					//获取文件的后缀名称
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					//是否为目录
					hash.put("is_dir", false);
					//是否还有子文件
					hash.put("has_file", false);
					//文件大小
					hash.put("filesize", file.length());
					//是否为图片
					hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
					//文件类型
					hash.put("filetype", fileExt);
				}
				//文件名称
				hash.put("filename", fileName);
				//文件最后更新时间
				hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
				//********结束：封装每个文件的信息*******

				//把每个文件信息对象放入到List集合中
				fileList.add(hash);
			}
		}
		//获取文件的相对路径
		String currentUrl=request.getContextPath()+"/uploadImg/";
		//把upload目录下的所有文件以json格式返回给kindeditor
		Map<String, Object> map=new HashMap<>();
		//上一级目录的路径(设置为""，则不设置)
		map.put("moveup_dir_path", "");
		//当前目录的路径
		map.put("current_dir_path", "");
		//upload上传目录的相对项目的路径
		map.put("current_url", currentUrl);
		//总文件数量
		map.put("total_count", fileList.size());
		//返回所有文件信息
		map.put("file_list", fileList);
		
		String json=JSON.toJSONString(map);
		return json;
	}
	
	@RequestMapping(value="deleteImg",produces = "text/json;charset=utf-8")
	@ResponseBody
	public String deleteImg(HttpServletRequest request,String url) {
		System.out.println(url);
		System.out.println(url.substring(url.lastIndexOf("uploadImg")));
		//获取文件的绝对路径
		String fileurl=request.getSession().getServletContext().getRealPath(url.substring(url.lastIndexOf("uploadImg")));
		//用集合装入函数需要返回的值
		Map<String, Object> map=new HashMap<>();
		//获取文件
		File file=new File(fileurl);
		if(file!=null && !file.equals("")){
			file.delete();
			map.put("success", 1);			
		}else {
			map.put("success", -1);
		}		
		
		String json=JSON.toJSONString(map);
				return json;
	}
	
}
