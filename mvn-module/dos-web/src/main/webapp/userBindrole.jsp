<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
	<%
	String path=request.getContextPath();
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>		
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户绑定角色</title>
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/default.css">
<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>js/form.js"></script>
<script type="text/javascript" src="<%=basePath%>js/crud.js"></script>
</head>
<body>
		
	<!-- 列表显示 -->
	<table id="list"></table>
	
	<!-- 按钮列表 -->
	<div id="toolbar">		
		<a id="saveBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">绑定资源</a>
	</div>
			
	<script type="text/javascript">
	$(function(){	
		$('#list').datagrid({	
			url:'role/findall',    
		columns:[ [ {
			field : "id",
			checkbox : true
		}, {
			field : "name",
			title : "角色名",
			width : 120,
			align : "center"
		}, {
			field : "keyword",
			title : "角色关键字",
			width : 120,
			align : "center"
		}, {
			field : "description",
			title : "备注",
			width : 120,
			align : "center"
		} ] ],
		pagination:true,
		toolbar:'#toolbar'
	});
		
	$("#saveBtn").click(function(){
		//获取用户ID
		var userId="${param.userId}";
		//获取选中角色
		var rows=$('#list').datagrid("getSelections");
		var roleArry=new Array();
		//遍历选中行
		$(rows).each(function(i){
			roleArry.push(rows[i].id);
		});
		var roleIds=roleArry.join(",");
		$.post("user/userBindrole",{userId:userId,roleIds:roleIds},function(data){
			if(data.success){
				$.messager.alert("提示","用户绑定角色成功","info");
			}else{
				$.messager.alert("提示","用户绑定角色失败","error");
			}
		},"json");	
	});					
});
	</script>
</body>
</html>