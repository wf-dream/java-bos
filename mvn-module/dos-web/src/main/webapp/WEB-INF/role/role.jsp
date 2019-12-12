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
<title>角色管理</title>
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
			角色名：<input type="text" name="name"/>
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
		<a id="bindResBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">绑定资源</a>
	</div>
	
	<!-- 编辑表单 -->
	<div id="editWin" class="easyui-window" data-options="title:'角色编辑',closed:true" style="width:600px;top:50px;left:200px">
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
							<td>角色名</td>
							<td>
								<input type="text" name="name" 
									class="easyui-validatebox" data-options="required:true" />
							</td>
						</tr>
						<tr>
							<td>角色关键词</td>
							<td>
								<input type="text" name="keyword" 
										class="easyui-validatebox" data-options="required:true" />
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
	
	<!-- 绑定资源窗口 -->
	<div id="bindResWin" class="easyui-window" data-options="title:'资源绑定',closed:true" style="width:600px;top:50px;left:200px;height:400px;">		
	</div>

	<script type="text/javascript">
	$(function(){	
		$('#list').datagrid({	
			url:'findall',    
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
		
		$("#bindResBtn").click(function(){		
			var rows=$("#list").datagrid("getSelections");
			if(rows.length!=1){
				$.messager.alert("提示","绑定资源必须选中一行","warning");
				return;
			}
			//把资源嵌套入绑定资源的界面中，并将角色ID传递到嵌套的页面
			var content="<iframe src='../roleBindresource.jsp?roleId="+rows[0].id+"' style='width:100%;height:100%;' frameborder=0></iframe>";
			//窗体中content显示的内容
			$("#bindResWin").window({
				content:content
			});			
			$("#bindResWin").window('open');
		});
});
	</script>
</body>
</html>