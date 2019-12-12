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
<title>区域管理资料</title>
</head>
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/form.js"></script>
<script type="text/javascript" src="<%=basePath%>js/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/themes/default/easyui.css">
<!-- 提取出来的js脚本部分（增删查改） -->
<script type="text/javascript" src="<%=basePath%>js/crud.js"></script>
<!-- ajax上传文件插件 -->
<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>

     <script type="text/javascript">
	$(function(){
		 //加载数据列表
		 $('#list').datagrid(
				 {    
			 	//发送请求地址
			   url:'findall',    
			   columns:[ [ {
					field : 'id',
					checkbox : true,
				},{
					field : 'province',
					title : '省',
					width : 184,
					align : 'center'
				}, {
					field : 'city',
					title : '市',
					width : 184,
					align : 'center'
				}, {
					field : 'distrcit',
					title : '区',
					width : 184,
					align : 'center'
				}, {
					field : 'postcode',
					title : '邮编',
					width : 184,
					align : 'center'
				}, {
					field : 'shortcode',
					title : '简码',
					width : 184,
					align : 'center'
				}, {
					field : 'citycode',
					title : '城市编码',
					width : 184,
					align : 'center'
				} ] ],
				pagination:true,
			   toolbar:'#toolbar' 
			});		
		 
		 //excel导入
		 $("#improtBtn").click(function(){
				$("#importWin").window('open');
			});
		
		 //上传文件
		 $("#startImport").click(function(){
			 //得到上传文件名
			 var fileName=$("#fileID").val();
			 //正则表达式（匹配后缀为xls或xlsx的文件）
			var reg=/^.+\.(xls|xlsx)$/;
			 //判断是否为一个excle文件
			 if(!reg.test(fileName)){
				 $.messager.alert("提示","文件格式不正确，请重新选择","warning");
				 return;
			 }
			
			//文件上传方法
			 $.ajaxFileUpload({
					url:"improtExcel",
					fileElementId:"fileID",
					dataType:"json",
					success:function(data){
						if(data.success){
							//关闭窗口
							$("#importWin").window('close');
							//刷新数据列表
							$('#list').datagrid("reload");
							//提示信息
							$.messager.alert("提示","导入成功","info");
						}else{
							$.messager.alert("提示","导入失败","error");
						}
					},
					error:function(e){
					}
				});
			
		 });
		 
		 //导出文件
		 $("#exprotBtn").click(function(){
			 alert("122");
			 //提交表单
			 $("#seachForm").submit();
		 });
		 
	});

</script>

<body>
	<!-- 查询列表 -->
	<form id="seachForm" action="exportExcel" method="post">
		省份:<input type="text" name="province"/>
		城市:<input type="text" name="city"/>	
		区域:<input type="text" name="distrcit"/>			
		<input id="seach" type="button" value="查询"/>	
	</form>
	<!-- 按钮列表 -->
	<div id="toolbar">
		<a id="saveBtn" href="javascript:void()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">添加</a>
		<a id="editBtn" href="javascript:void()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
		<a id="delBtn" href="javascript:void()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
		<a id="improtBtn" href="javascript:void()" class="easyui-linkbutton" data-options="iconCls:'icon-print'">excel导入</a>
		<a id="exprotBtn" href="javascript:void()" class="easyui-linkbutton" data-options="iconCls:'icon-print'">excel导出</a>
	</div>
	<!-- 数据列表 -->
	<table id="list"></table> 
	
	<!-- 批量导入窗口excle文件 -->
	<div id="importWin" class="easyui-window" data-options="title:'区域信息导入',closed:true"
		style="width: 450px; top: 50px; left: 200px">
		<table class="table-edit" width="80%" align="center">
			<tr class="title">
				<td colspan="2">区域信息导入</td>
			</tr>
			<tr>
				<td>选择文件</td>
				<td><input id="fileID" type="file" name="file"
					class="easyui-validatebox" required="true" /></td>
			</tr>
			<tr>
				<td colspan="2"><a id="startImport" href="#"
					class="easyui-linkbutton">开始导入</a></td>
			</tr>
		</table>
	</div>
	
	<!-- 编辑表单 -->
	<div id="editWin" class="easyui-window" data-options="title:'区域添加修改',closed:true" style="width:600px;top:50px;left:200px">
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
							<td>省份</td>
							<td>
								<input type="text" name="province" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>城市</td>
							<td>
								<input type="text" name="city" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>区域</td>
							<td>
								<input type="text" name="distrcit" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>邮编</td>
							<td>
								<input type="text" name="postcode" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>简码</td>
							<td>
								<input type="text" name="shortcode" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>城市编码</td>
							<td>
								<input type="text" name="citycode" class="easyui-validatebox" required="true" />
							</td>
						</tr>
					</table>
		  </form>
		  </div>
	</div>	

</body>
</html>