<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>爱我书丛</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  <%
  	 String contextpath=request.getContextPath();
  	 String returnURL=request.getHeader("Referer");
  	 if(returnURL!=null && returnURL.indexOf("login")==-1 )
  		 request.getSession().setAttribute("returnURL", returnURL);
  %>
	
	<link rel="stylesheet" type="text/css" href="<%=contextpath %>/css/login.css" />
  </head>
  <body>
		<p class="lhs_head">
			爱我书丛，精彩等你来看
		</p>
		<p class="lhs_title">
			书是你一生的挚友
		</p>
		<div class="login_panel">
			<div class="login_h">用户登录</div>
			<div class="login_c">
				<form action="<%=contextpath %>/login" method="post">
					<ul class="login">
						<li class="input_li">
							<img src="<%=contextpath %>/images/userlo.png" />
							<input type="text" name="uid" id="uid" placeholder="请输入用户名" required/>
						</li>
						<li class="input_li">
							<img src="<%=contextpath %>/images/userkey.png" />
							<input type="password" name="pwd" id="pwd" placeholder="请输入密码" required/>
						</li>
					</ul>
					<div class="div_btn">
						<div><a href="<%=contextpath %>/register.jsp" class="reg">注册</a></div>
						<div class="btn">
							<input type="submit" value="登录" id="login_btn" />
						</div>
					</div>
					<div class="mesg">
						<!-- 用户名或者密码错误！ -->${error }
					</div>
				</form>
			</div>
		</div>
		
		<script src="<%=contextpath %>/ui/jquery2.js"></script>
		<script>
			$('.input_li > input').focus(function(){
				$(this).parent().css("box-shadow","0px 0px 15px rgb(41,38,236)");
			});
			$('.input_li > input').blur(function(){
				$(this).parent().css("box-shadow","none");
			});
		</script>	
  </body>
</html>
