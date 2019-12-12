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
<title>定区管理</title>
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
					field : 'FixedAreaName',
					title : '定区名称',
					width : 90,
					align : 'center'
				},{
					field : 'cname',
					title : '快递员姓名',
					width : 90,
					align : 'center'
				}, {
					field : 'telephone',
					title : '手机号',
					width : 185,
					align : 'center'
				}
				] ],
				pagination:true,
			   toolbar:'#toolbar' 
			});
		 
		 $("#associateBtn").click(function(){
			 var rows=$("#list").datagrid('getSelections')
			 if(rows.length!=1){
					$.messager.alert("提示","关联客户必须选中一行","warning");
					return;
				}
				//打开窗口
				$("#customerWin").window('open');
							
				//发送ajax请求加载未关联定区客户
				$.post("findNoAasociateCutomer",function(data){
					//清除数据
					$("#noassociationSelect").empty();
					//遍历JSON数据
					$(data).each(function(){
						$("#noassociationSelect").append("<option value="+this.id+">"+this.username+"</option>");
					})
				},"json");
				
				var id=rows[0].id;
				//发送ajax请求加载关联定区客户
				$.post("findHasAasociateCustomerByFixedAreaId",{id:id},function(data){
					//清除数据
					$("#associationSelect").empty();
					//遍历JSON数据
					$(data).each(function(){
						$("#associationSelect").append("<option value="+this.id+">"+this.username+"</option>");
					})
				},"json");
		 });
		 
		 //左边指向右边
		 $("#toRight").click(function(){
			 $("#associationSelect option:selected").appendTo($("#noassociationSelect"));
		 })
		 //右边指向左边
		  $("#toLeft").click(function(){
			 $("#noassociationSelect option:selected").appendTo($("#associationSelect"));
		 });
		 
		 //关联客户
		 $("#associationBtn").click(function(){
			 //获取左边关联客户Id
			 var customerArray=$("#associationSelect option");
			 var customerIds;
			 var custArray=new Array;
			 //将客户ID装到数组中
			 for(var i=0;i<customerArray.length;i++){
				 custArray.push($(customerArray[i]).val());
			 }
			 customerIds=custArray.join(",");
			
			 //获取定区Id
			 var rows=$("#list").datagrid('getSelections');
			 var FixedAreaId=rows[0].id;
			 
			 //请求发送到后台
			 $.post("assoctiateCutomerByFxiedArea",{customerIds:customerIds,FixedAreaId:FixedAreaId},function(data){
				 if(data.success){
						$.messager.alert("提示","关联客户成功","info");
						//关闭窗口
						$("#customerWin").window('close');
					}else{
						$.messager.alert("提示","关联客户失败","error");
					}
			 },"json");
		 });
		
	});
</script>


<body>
	<!-- 查询列表 -->
	<form id="seachForm" method="post">		
		定区名称:<input type="text" name="FixedAreaName"/>			
		<input id="seach" type="button" value="查询"/>	
	</form>
	<!-- 按钮列表 -->
	<div id="toolbar">
		<a id="saveBtn" href="javascript:void()" class="easyui-linkbutton" data-options="iconCls:'icon-save'">添加</a>
		<a id="editBtn" href="javascript:void()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
		<a id="delBtn" href="javascript:void()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
		<a id="associateBtn" href="javascript:void()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">关联客户</a>
	</div>
	<!-- 数据列表 -->
	<table id="list"></table> 
	
	<!-- 关联客户窗口 -->
	<div class="easyui-window" title="关联客户窗口" id="customerWin" modal="true"
			collapsible="false" closed="true" minimizable="false" maximizable="false" style="top:20px;left:200px;width: 700px;height: 300px;">
			<div style="overflow:auto;padding:5px;" border="false">
				<form id="customerForm" method="post">
					<table class="table-edit" width="80%" align="center">
						<tr class="title">
							<td colspan="3">关联客户</td>
						</tr> 
						<tr>
							<td>
								<select id="associationSelect" multiple="multiple" size="10" style="width: 300px;"></select>
							</td>
							<td>
								<input type="button" value="》》" id="toRight">
								<br/>
								<input type="button" value="《《" id="toLeft">
							</td>
							<td>
								<select id="noassociationSelect" name="customerIds" multiple="multiple" size="10" style="width: 300px;"></select>
							</td>
						</tr>
						<tr>
							<td colspan="3"><a id="associationBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">关联客户</a> </td>
						</tr>
					</table>
				</form>
			</div>
	</div>
	
	

	<!-- 编辑表单 -->
	<div id="editWin" class="easyui-window" data-options="title:'定区管理',closed:true" style="width:700px;top:50px;left:200px">
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
							<td>区域名称</td>
							<td>
								<input type="text" name="FixedAreaName" class="easyui-validatebox" required="true" />
							</td>							
						</tr>
					
						<tr>
							<td>电话</td>
							<td>
								<input type="text" name=telephone class="easyui-validatebox" required="true" />
							</td>							
						</tr>
						
						<tr>
							<td>快递员</td>
							<td>
								<select id="FixedAreaLeader" name="FixedAreaLeader" class="easyui-combobox" data-options="url:'../courier/getCourierAll',valueField:'id',textField:'cname'"  style="width:170px;"></select>
							</td>
						</tr>
					</table>
		  </form>
		  </div>
	</div>
	
</body>
</html>