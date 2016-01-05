<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path=request.getContextPath();
%>
<html>
<head>
    <meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	
    <title>爱我书从</title>    
    <meta http-equiv="content-type" content="text/html" />
    <link rel="Stylesheet" href="<%=path %>/css/commonBook.css" />
    <script type="text/javascript" src="<%=path %>/ui/jquery2.js"></script>
    <script type="text/javascript" src="<%=path %>/ui/layer/layer.js"></script>
</head>
<body>
    <div class="nav_fixed">
        <ul>
            <li><a href="<%=path %>/UserDefault">首页</a>|</li>
            <li><a href="<%=path %>/servlet/mychart">购物车</a>|</li>
            <li><a href="booksearch.aspx">图书检索</a></li>
        </ul>
    </div>
    <div class="border">
        <div class="panel">
            <div class="head">
                <div class="linear">
                    <div class="motto">
                          书上有路勤为径<br />
                          学海无涯苦作舟
                    </div>
                </div>
            </div>
            <div class="content" style="position:relative;">
                <div class="strip_v v1"></div>
                <div class="strip_v v2"></div>
                <div id="category_t" class="title">${category }</div>

                <input type="hidden" id="cate_input" value="${category }" />   <!-- category input -->
                <span class="line" id="ca_line"></span>
                <ul class="bookmenu">
	                <c:if test="${!empty detail }">
	                   <c:forEach items="${detail}" var="bookd" varStatus="status">
	                            <li class="booklist_item">
	                                <div class="img left" style="margin-top:8px;">
	                                    <img src="<%=path %>${bookd.imagePath}" alt="该图片逃跑啦!" />
	                                </div>
	                                <div class="con left">
	                                    <div class="bname"><a href="<%=path %>/BookDetailCtrl?id=${bookd.guid }" target="_blank">${bookd.bookname }</a></div>
	                                    <div class="l">作者：${bookd.author}</div>
	                                    <div class="l">出版社：${bookd.publisher }</div>
	                                    <div class="l">出版日期：${bookd.pubdate }</div>
	                                    <div class="l">人气指数：${bookd.access }</div>
	                                    <div class="l">价格：${bookd.price }</div>
	                                </div>
	                                <div class="num right">${status.count }</div>
	                            </li>
	                    </c:forEach>
	                </c:if>
	                <c:if test="${empty detail }">
	                	<li style="color:red;font-weight:bold;font-size:23px;">抱歉，这个类别还没有书籍，请稍候再来！</li>
	                </c:if>
	                <!--
	                    <li class="booklist_item">
	                        <div class="img left">
	                            <img src="http://img13.360buyimg.com/n1/jfs/t1870/255/67118695/351915/798ba476/55ec039eNb9a5ff3e.jpg" /></div>
	                        <div class="con left">
	                            <div class="bname"><a href="#">天上上</a></div>
	                            <div class="l">作者：冰室主人</div>
	                            <div class="l">出版社：长江出版社</div>
	                            <div class="l">出版日期：2015-09-16</div>
	                            <div class="l">人气指数：345</div>
	                        </div>   
	                        <div class="num right">1</div>
	                    </li>
	                -->
                </ul>
                <c:if test="${!empty detail }">   
                	<div class="morelist" id="moreblist">查看更多</div>
                </c:if>
            </div>
        </div>
    </div>
    <div class="footer"></div>
    <script type="text/javascript">
        var curpage = 1;

        $('#moreblist').click(function () {
            curpage++;
            var category = $('#cate_input').val();

            $.ajax({
                type: 'post',
                url: '<%=path%>/BookList',
                data: {
                    cmd: 'moreblist',
                    pages: curpage,
                    category: category
                },
                success: function (result) {
                    //result = result.replace(/@/,"\"");
                    var jd = $.parseJSON(result);
                    if (jd.hasMore) {
                        //alert(jd.data.replace(/@/g, '"'));
                        layer.load();
                        setTimeout(function () {
                            $('.bookmenu').append(jd.data.replace(/@/g, '"'));
                            layer.closeAll('loading');
                        }, 1000);
                    } else {
                        $('#moreblist').fadeOut();
                    }
                }
            });
        });
    </script>
</body>
</html>
