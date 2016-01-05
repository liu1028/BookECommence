<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%
	String path=request.getContextPath();
%>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<link rel="stylesheet" href="<%=path %>/css/bootstrap.css" type="text/css" />
		<link rel="stylesheet" href="<%=path %>/css/bootstrap-theme.css" type="text/css" />
		<link rel="stylesheet" href="<%=path %>/ui/themes/icon.css" type="text/css" />
		<link rel="stylesheet" href="<%=path %>/css/admin_index.css" type="text/css" />
		<link rel="stylesheet" href="<%=path %>/ui/themes/metro-green/easyui.css" type="text/css" />
				
	</head>
	<body>
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				<a class="navbar-brand" href="#">欢迎您，${username}</a>
			</div>
		
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li style="padding-top:15px;">
						<c:if test="${cookie.lastvisited!=null}">
							<span style="font-size:13px;">上次登录时间：${cookie.lastvisited.value }</span>
						</c:if>
					</li>
				</ul>
				<form class="navbar-form navbar-left" role="search">
					<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li style="padding-top:12px;padding-right:30px;">
						<span style="font-size:15px;">网站访问量：${ visitedCnt}</span> 
					</li>
					<li style="padding-top:12px;padding-right:30px;">
						<span style="font-size:15px;">在线用户数：${applicationScope.onlineCount}</span>
					</li>
					<li style="padding-top:6px;">
 						<button type="button" class="btn btn-default" id="quitBtn">安全退出</button>					
 					</li>
				</ul>
			</div><!-- /.navbar-collapse -->
		</div><!-- /.container-fluid -->
		</nav>
		
		<div class="easyui-layout" style="margin-top:-20px;" fit="true">
			<div data-options="region:'south',border:false" style="height: 30px; background: #e6eef8;padding: 10px; text-align: center;">Copyright &copy; 2015 Taoxiaoxiong版权所有
			</div>
			<div data-options="region:'west'" style="width: 200px;">
				<div class="easyui-accordion" data-options="border:false">
					<div title="系统管理" data-options="iconCls:'icon-cog'"  visible="false">
						<ul class="lmenu">
							<li onclick="addTabHtml('用户管理','<%=path %>/servlet/admin/UserManage?cmd=getUI','icon-user')"  id="usermanager" class="icon-user">用户管理</li>
						</ul>
					</div>
					<div title="个人空间" data-options="iconCls:'icon-smile'" visible="false">
						<ul class="lmenu">
							<li onclick="addTabHtml('分享图片','<%=path %>/servlet/admin/upload?cmd=getUI','icon-pie')" id="adminupload" class="icon-pie">分享图片</li>
							<li onclick="addTabHtml('共享图片','<%=path %>/servlet/admin/download?cmd=getUI','icon-cake')" id="admindown" class="icon-cake">共享图片</li>
						</ul>
					</div>
				</div>
			</div>
			<div data-options="region:'center'">
				<div class="easyui-tabs" data-options="border:false" id="center-tabs">
					<div title="首页" data-options="iconCls:'icon-home'">
						 <div style="padding:10px;position:relative;">
						      <p style="position:absolute;top:20px;right:10px;color:#106CC8;font-size:18px;font-weight:bold;width:200px;height:100px;text-indent:2em;z-index:9999"> 
						        	希望黎明赶走黑暗，阳光倾洒在床边上。一觉醒来听见花开，梦里也有人爱。河流绕着山川归去远方，而你的笑容依旧那么好看。
						      </p>
						      <img src="<%=path %>/images/bkg1.jpg" style="width:100%;height:88%;border-radius:9px;box-shadow:2px 2px 5px gray;"  alt="图片逃跑啦！">
						 </div>		
					</div>
				</div>
			</div>
		</div>
		
	<script src="<%=path %>/ui/jquery2.js"></script>
	<script src="<%=path %>/ui/bootstrap.js"></script>		
	<script src="<%=path %>/ui/jquery.easyui.min.js"></script>
	<script src="<%=path %>/ui/locale/easyui-lang-zh_CN.js"></script>
	<script>
		$('#quitBtn').click(function(){
			$.ajax({
				type:'get',
				url:'<%=path %>/servlet/quit',
				success:function(result){
					var j=$.parseJSON(result);
					if(j.status){
						window.location.href="<%=path%>/login.jsp";
					}
				}
			});
		});
	
		function addTabHtml(title, url, iconCls) {
			if ($('#center-tabs').tabs('exists', title)) {
				$('#center-tabs').tabs('select', title);
				//$('#center-tabs').tabs('getSelected').panel('refresh', url);
			} else {
				$.ajax({
					type: "GET",
					url: url,
					dataType: "html",
					cache: "false",
					success: function (html) {
						$('#center-tabs').tabs('add', {
							title: title,
							content: html,
							closable: true,
							iconCls: iconCls
						});
					}
				});
			}
		}
		
        function pagerFilter(data) {
            if (typeof data.length == 'number' && typeof data.splice == 'function') {	// is array
                data = {
                    total: data.length,
                    rows: data
                }
            }
            var dg = $(this);
            var opts = dg.datagrid('options');
            var pager = dg.datagrid('getPager');
            pager.pagination({
                onSelectPage: function (pageNum, pageSize) {
                    opts.pageNumber = pageNum;
                    opts.pageSize = pageSize;
                    pager.pagination('refresh', {
                        pageNumber: pageNum,
                        pageSize: pageSize
                    });
                    dg.datagrid('loadData', data);
                }
            });
            if (!data.originalRows) {
                data.originalRows = (data.rows);
            }
            var start = (opts.pageNumber - 1) * parseInt(opts.pageSize);
            var end = start + parseInt(opts.pageSize);
            data.rows = (data.originalRows.slice(start, end));
            return data;
        }
        Date.prototype.Format = function (fmt) {
            var o = {
                "M+": this.getMonth() + 1,                 //月份   
                "d+": this.getDate(),                    //日   
                "h+": this.getHours(),                   //小时   
                "m+": this.getMinutes(),                 //分   
                "s+": this.getSeconds(),                 //秒   
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度   
                "S": this.getMilliseconds()             //毫秒   
            };
            if (/(y+)/.test(fmt))
                fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
                if (new RegExp("(" + k + ")").test(fmt))
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            return fmt;
        }  
	
	</script>
	</body>
</html>