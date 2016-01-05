<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
%>

<div class="panel panel-primary" style="margin:20px 50px 20px 50px;">
	  <!-- Default panel contents -->
	  <div class="panel-heading">共享图片下载</div>
	  <div class="panel-body" style="padding:15px 10px 15px 50px;">
		   <div class="content" style="margin:40px auto;font-size:20px;lien-height:30px;">
		   		<span style="display:inline-block;margin-bottom:15px;">请选择要下载的图片：</span>
		   		<input class="easyui-combobox" style="width:280px;height:30px;" id="pic"
		   				data-options="valueField: 'filename',textField: 'file'
		   					,url:'<%=path %>/servlet/admin/download?cmd=getPic',method:'get'" />
		   		&nbsp;&nbsp;&nbsp;
		  		<a href="javascript:void(0)" class="easyui-linkbutton" style="width:200px;" onclick="down()" size="large">确定下载</a> 
		   </div>
	   </div>
</div>
<script>
	function down(){
		var url='<%=path %>/servlet/admin/download?file='+$('#pic').combobox('getValue')+'&cmd=down';
		var el = document.createElement("a");
		document.body.appendChild(el);
		el.href = url; 
		el.target = '_new'; 
		el.click();
		document.body.removeChild(el);
	}
</script>