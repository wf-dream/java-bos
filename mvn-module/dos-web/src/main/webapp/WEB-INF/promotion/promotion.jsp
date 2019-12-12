<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
	<%
	String path=request.getContextPath();
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>宣传任务</title>
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/default.css">
<script type="text/javascript" src="<%=basePath%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/crud.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/date.js"></script>
</head>
<body>
	<!-- 搜索框 -->
	<div class="datagrid-toolbar" style="height: 40px;">
		<form id="searchForm" method="post">
			标题:<input type="text" name="title; "/>
			<a id="search" class="easyui-linkbutton" href="#" icon="icon-search">查询</a>
		</form>
	</div>

	<!-- 列表 -->
	<table id="list"></table>
	<!-- 按钮列表 -->
	<div id="toolbar">
		<a id="saveBtn" href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-save'">添加</a> 
		<a id="editBtn" href="#"
			class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a> 
		<a id="delBtn" href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove'">删除</a>
	</div>

	<script type="text/javascript">
		$("#list").datagrid({
			url:'findall',
			columns:[ [ {
				field : 'id',
				title : '编号',
				width : 100,
				align : 'center',
				checkbox : true
			}, {
				field : 'title',
				title : '宣传概要（标题）',
				width : 200,
				align : 'center'
			}, {
				field : 'titleImg',
				title : '宣传图片',
				width : 200,
				align : 'center',
				formatter:function(value,row,index){
					if(value!=null&&value!=''){
						return "<img src='"+value+"'  style='width:200px; height:100px'/>";
					}else{
						return "";
					}
				}
			}, {
				field : 'startDate',
				title : '发布时间',
				width : 200,
				align : 'center',
				formatter:function(value,row,index){
					if(value!=null && value!=''){
						return dateFormat(value);
					}else{
						return "";
					}
				}
			}, {
				field : 'endDate',
				title : '失效时间',
				width : 200,
				align : 'center',
				formatter:function(value,row,index){
					if(value!=null && value!=''){
						return dateFormat(value);
					}else{
						return "";
					}
				}
			}, {
				field : 'status',
				title : '状态',
				width : 100,
				align : 'center',
				formatter:function(value,row,index){
					if(value==1){
						return "<font color='green'>有效</font>";
					}else{
						return "<font color='red'>无效</font>";
					}
			}
			} ] ],
			pagination:true,
			toolbar:"#toolbar"
		});
	
		
		$("#saveBtn").click(function(){			
			window.location.href="add";
		});
	</script>
</body>
</html>