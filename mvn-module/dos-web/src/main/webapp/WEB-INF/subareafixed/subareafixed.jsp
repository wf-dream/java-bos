<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String path=request.getContextPath();
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>快递员信息</title>
</head>
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/form.js"></script>
<script type="text/javascript" src="<%=basePath%>js/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/themes/default/easyui.css">

<script type="text/javascript" src="<%=basePath%>js/crud.js"></script>

     <script type="text/javascript">
	$(function(){
		 //加载数据列表
		 $('#list').datagrid({    
			 	//发送请求地址
			   url:'findall',    
			   columns:[ [ {
					field : 'id',
					checkbox : true,
				},{
					field : 'province',
					title : '省',
					width : 120,
					align : 'center'
					
				}, {
					field : 'city',
					title : '市',
					width : 120,
					align : 'center'
					
				}, {
					field : 'distrcit',
					title : '区',
					width : 120,
					align : 'center'
					
				},{
					field : 'FixedAreaName',
					title : '所属定区',
					width : 120,
					align : 'center'
				},  {
					field : 'StartNum',
					title : '起始号',
					width : 100,
					align : 'center'
				}, {
					field : 'EndNum',
					title : '终止号',
					width : 100,
					align : 'center'
				} , {
					field : 'KeyWords',
					title : '关键字',
					width : 120,
					align : 'center'
				}, {
					field : 'AssitKeyWords',
					title : '辅助关键字',
					width : 100,
					align : 'center'
				} ] ],
				pagination:true,
			   toolbar:'#toolbar' 
			});
		
	});
</script>


<body>
	<!-- 查询列表 -->
	<form id="seachForm" method="post">
		关键字:<input type="text" name=KeyWords/>
		辅助关键字:<input type="text" name="AssitKeyWords"/>			
		<input id="seach" type="button" value="查询"/>	
	</form>
	<!-- 按钮列表 -->
	<div id="toolbar">
		<a id="saveBtn" href="javascript:void()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">添加</a>
		<a id="editBtn" href="javascript:void()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
		<a id="delBtn" href="javascript:void()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
	</div>
	<!-- 数据列表 -->
	<table id="list"></table> 
	
	<!-- 编辑表单 -->
	<div id="editWin" class="easyui-window" data-options="title:'分区编辑',closed:true" style="width:700px;top:50px;left:200px">
			<!-- 按钮区域 -->
			<div class="datagrid-toolbar">
				<a id="save" class="easyui-linkbutton" href="#" icon="icon-save">保存</a>
			</div>
			<!-- 编辑区域 -->
			<div>
			<form id="editForm" method="post">
			<!--提供隐藏域 装载id -->
			<input type="hidden" name="id" />
			<table class="table-edit" width="80%" align="center">
						<tr>
							<td>所属区域</td>
							<td>
								<select id="AreaId" name="AreaId" class="easyui-combobox" data-options="url:'../area/getShowName',valueField:'areacode',textField:'showname'"  style="width:170px;">   
								</select>																
							</td>
							<td>所属定区</td>
							<td>
								<select id="FixedAreaId" name="FixedAreaId" class="easyui-combobox" data-options="url:'../fixed/getFixedAreaName',valueField:'id',textField:'fixedAreaName'"  style="width:170px;"> 
							</td>
						</tr>
						<tr>
							<td>起始号</td>
							<td>
								<input type="text" name="startNum" class="easyui-validatebox" required="true" />
							</td>
							<td>中止号</td>
							<td>
								<input type="text" name="endNum" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>关键词</td>
							<td>
								<input type="text" name="keyWords" class="easyui-validatebox" required="true" />
							</td>
							<td>辅助关键词</td>
							<td>
								<input type="text" name="AssitKeyWords" class="easyui-validatebox" required="true" />
							</td>
						</tr>
					</table>
		  </form>
		  </div>
	</div>
	
</body>
</html>