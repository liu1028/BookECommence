<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
%>

   <div class="panel panel-primary" style="margin:10px 15px 20px 15px;">
	  <!-- Default panel contents -->
	  <div class="panel-heading">用户管理</div>
	  <div class="panel-body" style="padding:14px 10px 15px 25px;">
	  	<div class="form-inline">
			  <div class="form-group">
			    <label for="uid">用户名</label>
			    <input type="text" class="form-control" id="uid" placeholder="请输入用户名">
			  </div>
			  &nbsp;&nbsp;
			  <div class="form-group">
			    <label for="sex">性别</label>
			    <select class="form-control" id="sexf">
			    	<option value="">全部</option>
			    	<option value="0">男</option>
			    	<option value="1">女</option>
			    </select>
			  </div>
			  &nbsp;&nbsp;			  
			  <button class="btn btn-default" id="search">开始搜索</button>
		</div>
	  </div>
	
	  <!-- Table -->
 		<table id="user_datagrid"  style="margin-top:10px !important;">
	        <thead>
	            <tr>
	                <th data-options="field:'id',hidden:true">编号</th>
	                <th data-options="field:'username',width:130,align:'center'">用户名</th>
	                <th data-options="field:'sex',width:70,align:'center'">性别</th>
	                <th data-options="field:'age',width:40,align:'center'">年龄</th>
	                <th data-options="field:'birth',width:140,align:'center'">出生日期</th>
	                <th data-options="field:'hobby',width:160,align:'center'">爱好</th>
	                <th data-options="field:'motto',width:240,align:'center'">个性签名</th>
	                <th data-options="field:'CZ',width:140,align:'center', formatter:CZForma">操作</th>
	            </tr>
	        </thead>    
    </table>    
 </div>

<!-- 编辑用户窗口 -->
<div class="easyui-window" id="edit_window" style="padding:25px 35px 20px 15px;"
	data-options="closed:true,minimizable:false,width:350,title:'编辑用户',iconCls:'icon-tip',collapsible:false,modal:true" >
	<form id="editform" method="post">
		<input type="hidden" name="id" value="" />
		<ul>
			<li>
				<span class="plain">用户名：</span>
				<input type="text" name="username" class="easyui-validatebox textbox" data-options="required:true" style="width:140px;"/>
			</li>
			<li>
				<span class="plain">性别：</span>
				<input id="sex" name="sex" class="easyui-combobox validatebox" data-options="required:true,panelHeight:60,
						valueField: 'sex',textField: 'value',data: [{sex: '0',value: '男'},{sex:'1',value:'女'}]"	/>			  
			</li>
			<li>
				<span class="plain">年龄：</span>
				<input class="easyui-numberbox textbox" data-options="required:true,min:0,max:150,precision:0" type="text" name="age" id="age"/>
			</li>
			<li>
				<span class="plain">出生日期：</span>
				<input class="easyui-datebox" required="required" id="birth" name="birth"/>
			</li>
			<li style="height:70px;">
				<span class="plain">个性签名：</span>
				<input class="easyui-textbox" data-options="multiline:true" style="height:70px;"  id="motto" name="motto"/>
			</li>
    	 </ul>
		  <div style="margin-top:20px;text-align:center;">
		  	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="sub_edit()" iconCls="icon-ok">提交更改</a>
		  </div>		  
	</form>
</div>


<script type="text/javascript">
	$('#sex').combobox({

	});
	
	$('#user_datagrid')
		.datagrid({
			border:true,
			rownumbers:true,
			singleSelect:true,
			autoRowHeight:false,
			remoteSort:false,
			pagination:true,
		});
		var pager=	$('#user_datagrid').datagrid('getPager');
		pager.pagination({
				pageSize: 10,
	            pageList: [10, 20, 30],
		        onSelectPage: function (pageNum, pageSize) {
		        	var uid=$('#uid').val();
		        	var sex=$('#sexf').val();
		        	if(pageNum==0) pageNum=1;
		        	$.ajax({
		        		type:'post',
		        		url:'<%=path %>/servlet/admin/UserManage?cmd=getPagination',
		        		data:{username:uid,sex:sex,pagesize:pageSize,pagenum:pageNum},
		        		success:function(result){
		        			var json=$.parseJSON(result);
		        			$('#user_datagrid').datagrid('loadData',json);
		        		}
		        	});
		        }
		        
	    });
	
	$('#search').click(function(){
		pager.pagination('select',1);
	});
	
	pager.pagination('select',1);

	function CZForma(value, row, index){
        return "<a href='javascript:void(0)' onclick=editUser('" +index+ "')>编辑用户</a>"+
        	"&nbsp;|&nbsp;"+
        	"<a href='javascript:void(0)' onclick=deleteUser('" + index + "')>删除用户</a>";
	}
	
	function editUser(index){
		$('#edit_window').window('center').window('open');
		
        var data = $("#user_datagrid").datagrid("getRows")[index];		
		$('#editform').form('load',data);
	}
	function sub_edit()
	{
		$('#editform').form('submit', {
			url: '<%=path %>/servlet/admin/UserManage?cmd=edit',
			onSubmit: function(){
				var isValid = $(this).form('validate');
				return isValid;	
			},
			success: function(result){
				var j=$.parseJSON(result);
				if(j.status){
					$('#edit_window').window('close');
					$.messager.alert('信息提示','编辑成功');
					pager.pagination('select',pager.pagination('options').pageNumber);
				}
			},
    		error:function(result){
    			$.messager.alert('系统忙，请稍后再试！');
    		}
		});
	}
	
	function deleteUser(index){
        var data = $("#user_datagrid").datagrid("getRows")[index];

        $.messager.confirm('确认', '您确认想要删除[' + data.username + ']这个用户 ？',
	        function (r) {
	            if (r) {
	            	$.ajax({
	            		type:'get',
	            		data:{unique:data.id},
	            		url:'<%=path %>/servlet/admin/UserManage?cmd=deletion',
	            		success:function(result){
	            			var j=$.parseJSON(result);
	            			if(j.status){
	            				$.messager.alert("信息提示","删除成功!");
	            				pager.pagination('select',pager.pagination('options').pageNumber);
	            			}
	            		},
	            		error:function(result){
	            			$.messager.alert('系统忙，请稍后再试！');
	            		}
	            	});
	            }
        });

	}
</script>
