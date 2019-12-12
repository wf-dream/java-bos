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
<title>Insert title here</title>
</head>
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/form.js"></script>
<script type="text/javascript" src="<%=basePath%>js/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/themes/default/easyui.css">

<script type="text/javascript">
	$(function(){
		//显示所有列表
		$('#list').datagrid({
			url:'findall',
			columns:[[
			    {field:'id',title:'编号',width:100},
			    {field:'name',title:'收派标准',width:100},
			    {field:'min_weight',title:'最小重量',width:100},
			    {field:'max_weight',title:'最大重量',width:100},
			    {field:'min_length',title:'最小长度',width:100},
			    {field:'max_length',title:'最大长度',width:100},
			        ]],
			pagination:true,
			toolbar:'#toolbar'
		});
		//查询列表
		$("#seach").click(function(){
			$("#list").datagrid("load",getFormData("seachForm"));
		});
		//添加收派标准事件
		$("#saveBtn").click(function(){
			//清除表单数据
			$("#editForm").form('clear');
			//打开添加的窗口页面
			$("#editwin").window('open');
		})
		
		//保存收派标准
		$("#save").click(function(){
			$("#editForm").form('submit',{			
				url:'insert',
				onSubmit:function(){
					return $("#editForm").form('validate');
				},success:function(data){
					data=eval("("+data+")")
					if(data.success){
						$("#editForm").form('clear');
						$("#editwin").window('close');
						$("#list").datagrid('reload');
						$.messager.alert('提示','保存成功','info');
					}else{
						$.messager.alert('提示','保存失败','error');
					}
				}
			});
		});
		
		
		//点击修改收派标准
		$("#editBtn").click(function(){
			//获取选中行
			var rows=$("#list").datagrid("getSelections");
			if(rows.length!=1){
				$.messager.alert("提示","修改操作必须选中一行","warning");
				return;
			}
			//获取要修改数据的ID
			var id=rows[0].id;
			//加载表单数据	
			$("#editForm").form('load','findId?id='+id);
			//打开窗口
			$("#editwin").window('open');
		});

		//删除
		$("#delBtn").click(function(){
			var rows=$("#list").datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert("提示","删除操作至少选中一行","warning");				
			}
			$.messager.confirm('提示','您确认想要删除记录吗？',function(value){
				if(value){
					var ids=new Array();
					for(var i=0;i<rows.length;i++){
						ids.push(rows[i].id);						
					}
					var idss=ids.join(",");
					//ajax请求
					$.post("delete",{ids:idss},function(data){
						data=eval("("+data+")");
						if(data.success){
							//刷新表格数据
							$("#list").datagrid("reload");
							$.messager.alert("提示","删除成功","info");
						}else{
							$.messager.alert("提示","删除失败","error");
						}
					});
				}
			});
						
		});
		
	});	
</script>

<body>
	<!-- 查询列表 -->
	<form id="seachForm" method="post">
		收派标准:<input type="text" name="name"/>
		最小重量:<input type="text" name="min_weight"/>
		最大重量:<input type="text" name="max_weight"/>		
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
	<!-- 编辑窗口 -->
	<div id="editwin" class="easyui-window" data-options="title:'收费标准编辑',closed:true" style="width:600px;top:50px;left:200px">
		<div class="datagrid-toolbar">
			<a id="save" class="easyui-linkbutton" href="#" icon="icon-save">保存</a>
		</div>
		<!-- 编辑区域 -->
		<div>
			<form id="editForm" method="post">
			<input type="hidden" name="id"/>
			<table width="80%" align="center" class="table-edit">
				<tr>
					<td>收派标准名称</td>
					<td><input type="text" name="name" class="easyui-validatebox" data-options="required:true"/></td>
				</tr>
				<tr>
					<td>最小重量</td>
					<td><input type="text" name="min_weight" class="easyui-validatebox" data-options="required:true"/></td>
				</tr>
				<tr>
					<td>最大重量</td>
					<td><input type="text" name="max_weight" class="easyui-validatebox" data-options="required:true"/></td>
				</tr>
				<tr>
					<td>最小长度</td>
					<td><input type="text" name="min_length" class="easyui-validatebox" data-options="required:true"/></td>
				</tr>
				<tr>
					<td>最大长度</td>
					<td><input type="text" name="max_length" class="easyui-validatebox" data-options="required:true"/></td>
				</tr>			
			</table>
			</form>
		</div>
	

	</div>
</body>
</html>