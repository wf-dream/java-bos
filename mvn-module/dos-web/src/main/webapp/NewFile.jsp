<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="favicon.ico" rel="icon" type="image/x-icon" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css" href="js/themes/icon.css">
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="js/themes/default/easyui.css">

<script type="text/javascript">
		$(function(){
			$("#list").treegrid({
				url:'data/treegrid.json',
				idField:'id',    
			    treeField:'name',
				columns:[ [ {
					field : 'id',
					title : '编号',
					width : 120,
					align : 'center'
				},{
					field : 'name',
					title : '电器',
					width : 120,
					align : 'center'
					
				}, {
					field : 'price',
					title : '价格',
					width : 120,
					align : 'center'				
				}] ],
				pagination:true
			});
		})
	
</script>


</head>
<body>
	<table id="list"></table>
	123
</body>
</html>