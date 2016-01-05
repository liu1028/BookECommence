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
	
    <title>书籍详情</title>    
    <meta http-equiv="content-type" content="text/html" />
    <link rel="Stylesheet" href="<%=path %>/css/commonBook.css" />
    <link rel="Stylesheet" href="<%=path %>/css/bookdetail.css" />
    
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
                
              <!------------MAIN CONTENT------------- -->
              	<div style="height:100px;width:20px;"></div>
				<c:if test="${!empty book }">
				    <div class="book_detail">
				        <div class="img_d left">
				            <img src="<%=path %>${book.imagePath}" alt="图片逃跑啦！" />
				        </div>
				        <div class="con_d left">
				            <div class="favor" id="favor_btn">加入购物车</div>
				            <div class="bname_d">${book.bookname}</div>
				            <div class="ld">作者：${book.author}</div>
				            <div class="ld">出版社：${book.publisher}</div>
				            <div class="ld">出版日期：${book.pubdate}</div>
				            <div class="ld">人气指数：${book.access}</div>
				            <div class="ld">价格：${book.price}</div>
				        </div>  
				        <div class="clear"></div> 
				        <div class="summary">
				        <div class="d_title">此书简介：</div>
				            ${book.summary}
				         </div>
				         <div class="catalog">
				            <div class="d_title"> 此书目录：</div>
				            <div class="cata_c">
				            	${book.catalog}
				            </div>
				         </div>
				         <input type="hidden" value="${book.guid }" id="id_input"/>
				    </div>
				</c:if>
	              	   
              	   
  <script type="text/javascript">
        $(document).ready(function () {
            $('.favor').click(function () {
                var bguid = $('input[type="hidden"]').val();

                $.ajax({
                    type: 'post',
                    data: { bguid: bguid, cmd: 'addChart',price:${book.price} },
                    url: '<%=path %>/servlet/mychart',
                    success: function (result) {
                        var jd = $.parseJSON(result);
                        if(!jd.IsLogged){
                        	window.location.href="<%=path%>/login.jsp";
                        	return;
                        }
                        if (jd.IsExist)
                            layer.msg('亲，书儿已被加入购物车啦！', { shift: 6, time: 2000 });
                        else {
                            layer.msg('亲，添加成功啦！', { shift: 1, time: 1500 });
                        }
                    }
                });
            });
        });
    </script> 	                         
              <!-- ---------MAIN CONTENT-------------- -->  
              
              
            </div>
        </div>
    </div>
    <div class="footer" style="height:60px;">
      	<div style="color:white;font-size:25px;margin:20px 0px 0px 20px !important;">爱我书丛©狂野镖客</div>
    </div>
</body>
</html>

