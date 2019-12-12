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
<title>用户管理</title>
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
	<!-- 搜索框 -->
	<div>
		<form id="seachForm" method="post">
			用户名：<input type="text" name="username"/>
			<a id="seach" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
		</form>
	</div>
	
	<!-- 列表显示 -->
	<table id="list"></table>
	
	<!-- 按钮列表 -->
	<div id="toolbar">
		<a id="saveBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">添加</a>
		<a id="editBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
		<a id="delBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
		<a id="bindRoleBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">绑定角色</a>
	</div>
	
	<!-- 编辑表单 -->
	<div id="editWin" class="easyui-window" data-options="title:'用户编辑',closed:true" style="width:600px;top:50px;left:200px">
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
							<td>登录用户名称</td>
							<td>
								<input type="text" name="username" 
									class="easyui-validatebox" data-options="required:true" />
							</td>
						</tr>
						<tr>
							<td>登录密码</td>
							<td>
								<input type="password" name="password" 
									class="easyui-validatebox" data-options="required:true" />
							</td>
						</tr>
						<tr>
							<td>真实姓名</td>
							<td>
								<input type="text" name="nickname" 
										class="easyui-validatebox" data-options="required:true" />
							</td>
						</tr>
						<tr>
							<td>性别</td>
							<td>
								<input type="radio" name="gender" value="男"/>男
								<input type="radio" name="gender" value="女"/>女
							</td>
						</tr>
						<tr>
							<td>电话</td>
							<td>
								<input type="text" name="telephone" class="easyui-validatebox" data-options="required:true" />
							</td>
						</tr>
						<tr>
							<td>状态</td>
							<td>
								<input type="radio" name="station" value="1"/>启用
								<input type="radio" name="station" value="0"/>禁用
							</td>
						</tr>
						<tr>
							<td>备注</td>
							<td>
								<textarea rows="5" cols="25" name="remark"></textarea>
							</td>
						</tr>
		  </table>
		  </form>
		  </div>
	</div>
	
	
	<!-- 绑定角色窗口 -->
	<div id="bindRoleWin" class="easyui-window" data-options="title:'资源绑定',closed:true" style="width:400px;top:50px;left:200px;height:300px;">
	</div>

	<script type="text/javascript">
		$(function(){
			$('#list').datagrid({
				url:'findall',    
			//表格的列数据
			columns:[ [ {
				field : "id",
				checkbox : true
			}, {
				field : "username",
				title : "登录用户名",
				width : 120,
				align : "center"
			}, {
				field : "nickname",
				title : "真实姓名",
				width : 120,
				align : "center"
			}, {
				field : "gender",
				title : "性别",
				width : 120,
				align : "center"
			}, {
				field : "telephone",
				title : "电话",
				width : 120,
				align : "center"
			}, {
				field : "station",
				title : "用户状态",
				width : 120,
				align : "center"
			},{
				field : "remark",
				title : "备注",
				width : 120,
				align : "center"
			} ] ],
			pagination:true,
			toolbar:'#toolbar' 
		});
			
			$("#bindRoleBtn").click(function(){
				var rows=$('#list').datagrid("getSelections");
				if(rows.length!=1){
					$.messager.alert("提示","绑定角色必须选中一行","warning");
					return;
				}
				//把资源嵌套入绑定资源的界面中，并将角色ID传递到嵌套的页面
				var content="<iframe src='../userBindrole.jsp?userId="+rows[0].id+"' style='width:100%;height:100%;' frameborder=0></iframe>";
				//窗体中content显示的内容
				$("#bindRoleWin").window({
					content:content
				});			
				$("#bindRoleWin").window('open');
				
			});
  });		
	</script>
</body>
</html>