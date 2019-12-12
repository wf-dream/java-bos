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
					field : 'courierNum',
					title : '工号',
					width : 90,
					align : 'center'
				},{
					field : 'cname',
					title : '姓名',
					width : 90,
					align : 'center'
				}, {
					field : 'telephone',
					title : '手机号',
					width : 185,
					align : 'center'
				}, {
					field : 'checkPwd',
					title : '查台密码',
					width : 185,
					align : 'center'
				}, {
					field : 'PDA',
					title : 'PDA号',
					width : 185,
					align : 'center'
				}, {
					field : 'name',
					title : '取派标准',
					width : 185,
					align : 'center'
				}, {
					field : 'company',
					title : '所属单位',
					width : 185,
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
		快递员编号:<input type="text" name="courierNum"/>
		员工姓名:<input type="text" name="cname"/>			
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
	<div id="editWin" class="easyui-window" data-options="title:'快递员编辑',closed:true" style="width:700px;top:50px;left:200px">
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
							<td>快递员工号</td>
							<td>
								<input type="text" name="courierNum" class="easyui-validatebox" required="true" />
							</td>
							<td>姓名</td>
							<td>
								<input type="text" name="cname" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>手机</td>
							<td>
								<input type="text" name="telephone" class="easyui-validatebox" required="true" />
							</td>
							<td>所属单位</td>
							<td>
								<input type="text" name="company" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>查台密码</td>
							<td>
								<input type="text" name="checkPwd" class="easyui-validatebox" required="true" />
							</td>
							<td>PDA号码</td>
							<td>
								<input type="text" name="pDA" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>取派标准</td>
							<td>
								<select id="standardId" name="standardId" class="easyui-combobox"    data-options="url:'../standard/getStandardAll',valueField:'id',textField:'name'"  style="width:170px;">   
								</select>  

							</td>
						</tr>
					</table>
		  </form>
		  </div>
	</div>

	
</body>
</html>