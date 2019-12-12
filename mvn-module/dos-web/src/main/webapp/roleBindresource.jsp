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
<title>角色绑定资源</title>
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
				url:'resourcee/findall',
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
					field : 'resourceType',
					title : '资源类型',
					//width : 90,
					align : 'center'
				}] ],
				pagination:true,
			   toolbar:'#toolbar',
			   pageSize:100,
			   pageList:[100,200],
			   checkbox:true,
			   cascadeCheck:false
			});
			
			$("#saveBtn").click(function(){
				//获取角色Id
				var roleId="${param.roleId}";
				//获取所选中的资源id
				var rows=$("#list").treegrid("getChildren");
				var resArray=new Array();
				//遍历数组
				$(rows).each(function(i){
					if(rows[i].checked){
						resArray.push(rows[i].id);	
					}
				});
				
				//将获取的资源ID拼接成字符串
				var resIds=resArray.join(",");
				alert(resIds);
				$.post("role/roleBindresource",{resIds:resIds,roleId:roleId},function(data){
					if(data.success){
						$.messager.alert("提示","角色绑定资源成功","info");
					}else{
						$.messager.alert("提示","角色绑定资源失败","error");
					}
				},"json");
				
			});			
});

	</script>

</head>
<body>
	<!-- 列表显示 -->
	<table id="list"></table>
			
	<div id="toolbar">
		<a id="saveBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">绑定资源</a>
	</div>	
</body>
</html>