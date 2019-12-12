<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="favicon.ico" rel="icon" type="image/x-icon" />
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="ztree/js/jquery.ztree.core.min.js"></script>
		<link rel="stylesheet" type="text/css" href="js/themes/icon.css">
		<link id="easyuiTheme" rel="stylesheet" type="text/css" href="js/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="ztree/css/zTreeStyle/zTreeStyle.css">
		<!-- 导入portal资源 -->
		<script type="text/javascript" src="portal/jquery.portal.js"></script>
		<link rel="stylesheet" type="text/css" href="portal/portal.css">
		
		<script type="text/javascript">
			$(function(){
				//创建一个门户
				$('#pp').portal();  
				//抽取的面板部件
				var panels=[
			           {title: '公共栏',    
			        	    height:150,    
			        	    closable: true,    
			        	    collapsible: true,
			        	    href:"portal/gonggao.html"
						},
			           {title: '代办事宜',    
			        	    height:150,    
			        	    closable: true,    
			        	    collapsible: true,
			        	    href:"portal/daiban.html"
						},
			           {title: '预警信息',    
			        	    height:150,    
			        	    closable: true,    
			        	    collapsible: true,
			        	    href:"portal/yujing.html"
						},
			           {title: '系统BUG反馈',    
			        	    height:150,    
			        	    closable: true,    
			        	    collapsible: true,
			        	    href:"portal/bug.html"
						}				           
				           ];
				//向门户中添加面板部件
				for(var i=0;i<4;i++){
				var p = $('<div></div>').appendTo('body'); 
				p.panel(panels[i])
				$('#pp').portal('add', {    
				    panel: p,    
				    columnIndex: i%2    
				});  
				}
				
			});
		
		</script>
</head>
<body>
	<div id="pp" style="height:400px;">   
    	<div style="width:50%"></div>   
    	<div style="width:50%"></div>        
	</div>  
</body>
</html>