<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>注册</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		body{
			padding:0px;
			margin:0px;
			text-align:center;
		}
		.login_panel
		{
			display:inline-block;
			background:#eee;
			margin:30px auto;
		}
		.head
		{
			font:normal bold 22px/50px "微软雅黑","宋体";
			background:#31708f;
			color:white;
			text-align:center;
			height:50px;
		}
		.content
		{
			margin-top:20px;
			padding:0px 80px 15px 80px;
		}
		ul.panel{
			list-style:none;
			padding:0px;
			margin:0px;
		}
		.panel li
		{
			padding-top:8px;
			padding-bottom:8px;
		}
		.panel li input[type="text"],.panel li input[type="password"]			
		{
			width:250px;
			height:35px;
			outline:none;
			border:2px solid #bbb;
			padding:3px;
		}
		.panel li textarea
		{
			outline:none;
			border:2px solid #bbb;
			width:250px;
			height:60px;
		}
		.panel li input:focus,textarea:focus
		{
			box-shadow:0px 0px 10px #107Cea;
			border:2px solid lightblue;
		}
		.panel li	> span
		{
			display:inline-block;
			font-weight:bold;
		}
		.submit
		{
			width:200px;
			height:38px;
			line-height:38px;
			font-size:17px;
			font-weight:bold;
			color:white;
			background:#107Cea;
			border:none;
		}
		.submit:hover
		{
			background:rgb(63,81,181);
		}
	</style>

  </head>
  <%
  	 String contextpath=request.getContextPath();
  %>
  
  <body>
   	<div class="login_panel">
   	  <form action="<%=contextpath %>/servlet/Register" method="post" onsubmit="return submitData();">
   		<div class="head">
   			请填入注册信息
   		</div>
   		<div class="content">
   			<ul class="panel">
   				<li><input type="text" name="username" id="uid" placeholder="请输入用户名" onblur="validateUid();" required /><br/>
   					<span class="userFormat" style="display:none;color:red;">用户名已经被人注册啦,快换一个吧！</span>   					
   				</li>
   				<li><input type="password" name="password" placeholder="请输入密码" required /></li>
   				<li style="text-align:left;"><span>性别：</span>
   					<label><input type="radio" name="sex" checked="checked" value="0">男</label>
   					<label><input type="radio" name="sex" value="1">女</label>
   				</li>
   				<li>
   					<input type="text" name="age" placeholder="请输入年龄" required onblur="validateAge(event);"/><br/>
   					<span class="ageFormat" style="display:none;color:red;">请输入数字!</span>
   				</li>
   				<li><input type="text" name="birthday" placeholder="出生日期(XXXX-XX-XX,如1332-02-09)" onblur="validateBirth(event);" required /><br/>
   					  <span class="birthFormat" style="display:none;color:red;">请按照格式：XXXX-XX-XX输入，如1332-02-09!</span>
   				</li>
   				<li><span>爱好：</span>
   					<label><input type="checkbox" name="hobby" value="打篮球" />打篮球</label>
   					<label><input type="checkbox" name="hobby" value="看动漫"/>看动漫</label>
   					<label><input type="checkbox" name="hobby" value="其他"/>其他</label>
   				</li>
   				<li>
   					<textarea  name="motto" placeholder="请输入个性签名"></textarea>
   				</li>
   				<li style="text-align:center;">
   					<input type="submit" value="提交"  class="submit"/>
   				</li>
   			</ul>
   		</div>
	  </form>
   	</div>
   	<script src="<%=request.getContextPath() %>/ui/jquery2.js"></script>
   	<script type="text/javascript">
   	    var age=0;
   	    var birth=0;
   	    var user=0;
   	    function validateUid(){
   	    	var username=$('#uid').val();
   	    	
   	    	$.ajax({
   	    		type:'get',
   	    		data:{cmd:'IsUsernameSame',uid:username},
   	    		url:'<%=contextpath %>/servlet/Register',
   	    		success:function(result){
   	    			var j=JSON.parse(result);
   	    			if(j.status){
   	    				user=1;
   	    				$('.userFormat').fadeIn();
   	    			}else{
   	    				user=0;
   	    				$('.userFormat').hide();   	    				
   	    			}
   	    		},
   	    		error:function(result){
   	    			
   	    		}
   	    	});
   	    }
   	    function validateAge(e){
   	    	if(isNaN(event.target.value)){
   	    		$('.ageFormat').fadeIn();
   	    		age=1;
   	    	}
   	    	else {
   	    		$('.ageFormat').hide();
   	    		age=0;
   	    	}
   	    }
   		
   	    function validateBirth(e){
   	    	if(/^[0-9]{4,4}-[0-9]{2,2}-[0-9]{2,2}$/.test(e.target.value)){
   	    		$('.birthFormat').hide();
   	    		birth=0;
   	    	}
   	    	else {
   	    		$('.birthFormat').fadeIn();
   	    		birth=1;
   	    	}
   	    		
   	    }
   		function submitData(){
   			//alert(age+"   "+birth)
   			if(age==0&&birth==0&&user==0)
   				return true;
   			else
   				return false;
   		}
   	</script>
  </body>
</html>
