<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path=request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>我的购物小车</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	<link rel="stylesheet"  href="<%=path%>/css/chart.css" />
	
	<script type="text/javascript" src="<%=path %>/ui/jquery2.js"></script>
    <script type="text/javascript" src="<%=path %>/ui/layer/layer.js"></script>
	
  </head>
  
  <body>
    <div class="nav_fixed">
        <ul>
            <li><a href="<%=path %>/UserDefault">首页</a><b style="color:orange;">|</b></li>
            <li><a href="<%=path %>/servlet/mychart">购物车</a><b style="color:orange;">|</b></li>
            <li><a href="booksearch.aspx">图书检索</a></li>
        </ul>
    </div>
    
    
  </body>
</html>
