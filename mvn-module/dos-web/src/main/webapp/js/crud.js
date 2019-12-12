$(function(){
 
	 //查询数据列表
	 $("#seach").click(function(){
		 $("#list").datagrid("load",getFormData("seachForm"));
	 });
	 
	 //添加收派标准事件
	 $("#saveBtn").click(function(){
		 //清除表单数据 
		 $("#editForm").form('clear');
		 $("#editWin").window('open');
	 });
	 
	 //保存收派标准
	 $("#save").click(function(){
		$("#editForm").form('submit',{
			url:'save',
			onSubmit:function(){
				//验证表单是否生效
				return $("#editForm").form('validate');
			},success:function(data){
				//将字符串转成JSON
				data=eval("("+data+")");
				if(data.success){
					//清除表单
					$("#editForm").form('clear');
					//关闭窗口
					$("#editWin").window('close');
					//刷新datagrid
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
		//获取要修改的数据ID
		 var id=rows[0].id;
		//加载表单数据
		 $('#editForm').form('load','edit?id='+id);
		//打开窗口
		 $("#editWin").window('open');
	 });
	 
	 //删除事件
	 $("#delBtn").click(function(){
		 //获取选中行
		 var rows=$("#list").datagrid('getSelections');
		 if(rows.length==0){
			 $.messager.alert("提示","删除操作至少选中一行","warning");
		 }
		 
		 //提示信息
		 $.messager.confirm('提示','您确认想要删除记录吗？',function(value){    
			    if (value){    
					 //定义数组
					 var ids=new Array();
					 for(var i=0;i<rows.length;i++){
						 ids.push(rows[i].id);
					 }
					 var idss=ids.join(",");
					//ajax请求
					 $.post("delete",{ids:idss},function(data){					
						 if(data.success){							
							 $.messager.alert("提示","删除成功","info");
							 //刷新表格数据
							 $("#list").datagrid("reload");
						 }else{
							 $.messager.alert("提示","删除失败","error");
						 }
					 },"json");
			    }    
			});  
	 });
})