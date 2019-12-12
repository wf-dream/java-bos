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
<title>资源管理</title>
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/default.css">
<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>js/form.js"></script>

<script type="text/javascript">
		$(function(){
			$("#list").treegrid({
				url:'findall',
				//定义关键字段来标识树节点
				idField:'id',   
				//树节点字段
			    treeField:'name',
				columns:[ [ {
					field : 'id',
					title : '编号',
					align : 'center'
				},{
					field : 'name',
					title : '资源名称',
					//width : 90,
					align : 'center'
				},{
					field : 'grantKey',
					title : '权限码',
					//width : 90,
					align : 'center'
				}, {
					field : 'page',
					title : '资源访问路径',
					//width : 185,
					align : 'center'
				},{
					field : 'resourceType',
					title : '资源类型',
					//width : 90,
					align : 'center'
				},{
					field : 'icon',
					title : '图标',
					//width : 90,
					align : 'center'
				}, {
					field : 'pId',
					title : '父资源编号',
					//width : 185,
					align : 'center'
				},{
					field : 'open',
					title : '是否展开',
					//width : 185,
					align : 'center'
				}
				] ],
				pagination:true,
			   toolbar:'#toolbar',
			   pageSize:100,
			   pageList:[100,200]
			});
		});
	
	</script>

</head>
<body>
	<!-- 列表显示 -->
	<table id="list"></table>
	
	<!-- 按钮列表 -->
	<div id="toolbar">
		<a id="saveBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">添加</a>
		<a id="editBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
		<a id="delBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
	</div>
	
	<!-- 编辑表单 -->
	<div id="editWin" class="easyui-window" data-options="title:'资源编辑',closed:true" style="width:600px;top:50px;left:200px">
			<!-- 按钮区域 -->
			<div class="datagrid-toolbar">
				<a id="save" class="easyui-linkbutton" href="#" icon="icon-save">保存</a>
			</div>
			<!-- 编辑区域 -->
			<div>
			<form id="editForm" method="post">
			<!-- 提供隐藏域ID -->
			<input type="hidden" name="id"/>
			<table width="80%" align="center" class="table-edit">
						<tr>
							<td>资源名称</td>
							<td>
								<input type="text" name="name" 
									class="easyui-validatebox" data-options="required:true" />
							</td>
						</tr>
						<tr>
							<td>资源类型</td>
							<td>
								<select name="resourceType" class="easyui-combobox" data-options="editable:false,width:150">
			                        <option value="0">菜单</option>
			                        <option value="1">按钮</option>
			                    </select>
							</td>
						</tr>
						<tr>
							<td>菜单页面路径</td>
							<td>
								<input type="text" name="pageUrl" 
										class="easyui-validatebox" data-options="required:true" />
							</td>
						</tr>
						<tr>
							<td>菜单图标</td>
							<td>
								<input type="text" name="icon" />
							</td>
						</tr>
						<tr>
							<td>排序</td>
							<td>
								<input name="seq" value="0"  class="easyui-numberspinner"  required="required" data-options="editable:false">
							</td>
						</tr>
						<tr>
							<td>是否展示</td>
							<td>
								<input type="radio" name="open" value="true"/>展开
								<input type="radio" name="open" value="false"/>不展开
							</td>
						</tr>
						<tr>
							<td>上级资源</td>
							<td>
								<select id="parentResId" name="pId"></select>
							</td>
						</tr>
						<tr>
							<td>备注</td>
							<td>
								<textarea rows="5" cols="25" name="description"></textarea>
							</td>
						</tr>					
		  </table>
		  </form>
		  </div>
	</div>
	
</body>
</html>