<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String path=request.getContextPath();
%>
<html>
<head>
	<meta http-equiv="content-type" content="text/html;charset=utf-8" />
	<meta http-equiv="Cache-control" content="no-cache" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<title>爱我书丛</title>
	<link rel="stylesheet" href="<%=path%>/css/index.css" />
	<link href="<%=path%>/css/carousel.css" rel="stylesheet" />
	<link href="<%=path%>/css/carousel.theme.css" rel="stylesheet" />
	
	<script src="<%=path%>/ui/jquery2.js"></script>
	<script src="<%=path%>/js/carousel.min.js"></script>
	<script src="<%=path %>/ui/layer/layer.js"></script>
</head>

<body>
	<div class="navheader">
		<div class="header1">
			 <div class="head1_m">
				<div class="head1_logo1 left">
					<div>
						<img src="images/booklogo3.png" width="240" height="100" />
					</div>
				</div>
				<div class="head1_search left">
					<form id="search_form" action="pages/user/lower_search.aspx?cmd" method="get"
						onsubmit="return !($('#search_input_text').val()=='');">
						
						<!--搜索框-->
						<div class="top_search">
							<div class="search-input_div left">
								<label class="search_label" for="search_input_text">请输入有关图书信息</label>
								<input id="search_input_text" name="search_info"	class="search_input" type="text" autocomplete="off" value="" />
								<div class="search_result" style="display:none;position:absolute;width:384px;"></div>
							</div>
							<input type="submit" value="搜索" class="search_button left" />
							<div class="search_reminder_list" style="display:none"></div>
						</div>

						<!--刷选条件-->
						<div class="filter_radio">
							<label><input type="radio" name="filter_rdo" value="all" checked="checked" />全部</label>
							<label><input type="radio" name="filter_rdo" value="bookname" />书名</label>
							<label><input type="radio" name="filter_rdo" value="author" />作者</label>
						    <label><input type="radio" name="filter_rdo" value="publisher" />出版社</label>
						</div>
					</form>
				</div>
				<img class="head1_logo2 left" src="<%=path %>/images/booklogo8.png" />
			   
			    <!--登录与书架信息-->
			     <div class="head1_login right">
                      <c:if test="${sessionScope.IsAuthenticated && sessionScope.JSM eq 'user'}">                                         
	                      <div class="uid_div"><b id="uid">${sessionScope.username }</b>,您好</div>
	                      <div class="por_img block" id="portrait"><img id="portrait_img" src="<%=path %>/images/portrait.jpg" /></div>                                         
	                      <div class="block"><a id="signout" title="注销" href="javascript:void()">注销</a></div>
                      </c:if>
			    </div>
				
			  </div>
		</div>
		
		<!--页面导航栏-->
		<div class="header2">
			<div class="nav-items">
				<ul class="nav_ul left">
					<li><a href="#">首页在线</a></li>
					<li><a href="<%=path %>/servlet/mychart">我的购物车</a></li>
					<li><a href="/pages/user/booksearch.aspx">我的订单</a></li>
					<li><a href="#">关于我们</a></li>
					<li></li>
				</ul>
			</div>
		</div>
	</div>

	<!--主体部分-->
	<div class="page_main">
		<!--第一个主题-->
		<div class="theme">
			<div class="theme_con">
				<div class="title">珍书分类</div>

				<!--图书分类浏览-->
				<div class="classfied left">
					<div class="book_title">书籍分类</div>
					<ul class="book_classfied">
						<li>
							<div class="book_cate">
								<b>文学</b>随笔&nbsp;|&nbsp;童话
							</div>
							<div class="cate_detail" style="top:-35px;">
								<div class="cate_title">文学藏阁</div>
								<div class="cline"></div>
								<div class="cate_item">
									<a href="<%=path %>/BookList?cmd=小说">小说</a>| <a
										href="<%=path %>/BookList?cmd=随笔">随笔</a>| <a
										href="<%=path %>/BookList?cmd=散文">散文</a>
								</div>
								<div class="cate_item">
									<a href="<%=path %>/BookList?cmd=日本文学" style="width:80px;">日本文学</a>|
									<a href="<%=path %>/BookList?cmd=童话">童话</a>| <a
										href="<%=path %>/BookList?cmd=诗歌">诗歌</a>
								</div>
								<div class="cate_item">
									<a href="<%=path %>/BookList?cmd=名著">名著</a>| <a
										href="<%=path %>/BookList?cmd=港台">港台</a>
								</div>
								<div class="bkg_strip"></div>
							</div>
						</li>
						<li>
							<div class="book_cate">
								<b>流行</b>绘本&nbsp;|&nbsp;青春
							</div>
							<div class="cate_detail">
								<div class="cate_title">流行风尚</div>
								<div class="cline"></div>
								<div class="cate_item">
									<a href="<%=path %>/BookList?cmd=漫画">漫画</a>| <a
										href="<%=path %>/BookList?cmd=绘本">绘本</a>| <a
										href="<%=path %>/BookList?cmd=推理">推理</a>
								</div>
								<div class="cate_item">
									<a href="<%=path %>/BookList?cmd=青春">青春</a>| <a
										href="<%=path %>/BookList?cmd=言情">言情</a>| <a
										href="<%=path %>/BookList?cmd=科幻">科幻</a>
								</div>
								<div class="cate_item">
									<a href="<%=path %>/BookList?cmd=武侠">武侠</a>| <a
										href="<%=path %>/BookList?cmd=奇幻">奇幻</a>
								</div>
								<div class="bkg_strip"></div>
							</div>
						</li>
						<li>
							<div class="book_cate">
								<b>文化</b>历史&nbsp;|&nbsp;设计
							</div>
							<div class="cate_detail">
								<div class="cate_title">文化盛宴</div>
								<div class="cline"></div>
								<div class="cate_item">
									<a href="<%=path %>/BookList?cmd=历史">历史</a>| <a
										href="<%=path %>/BookList?cmd=哲学">哲学</a>| <a
										href="<%=path %>/BookList?cmd=传记">传记</a>
								</div>
								<div class="cate_item">
									<a href="<%=path %>/BookList?cmd=设计">设计</a>| <a
										href="<%=path %>/BookList?cmd=建筑">建筑</a>| <a
										href="<%=path %>/BookList?cmd=电影">电影</a>
								</div>
								<div class="cate_item">
									<a href="<%=path %>/BookList?cmd=回忆录" style="width:60px;">回忆录</a>|
									<a href="<%=path %>/BookList?cmd=音乐">音乐</a>
								</div>
								<div class="bkg_strip"></div>
							</div>
						</li>
						<li>
							<div class="book_cate">
								<b>生活</b>旅行&nbsp;|&nbsp;励志
							</div>
							<div class="cate_detail">
								<div class="cate_title">生活点滴</div>
								<div class="cline"></div>
								<div class="cate_item">
									<a href="<%=path %>/BookList?cmd=旅行">旅行</a>| <a
										href="<%=path %>/BookList?cmd=励志">励志</a>| <a
										href="<%=path %>/BookList?cmd=职场">职场</a>
								</div>
								<div class="cate_item">
									<a href="<%=path %>/BookList?cmd=美食">美食</a>| <a
										href="<%=path %>/BookList?cmd=教育">教育</a>| <a
										href="<%=path %>/BookList?cmd=灵修">灵修</a>
								</div>
								<div class="cate_item">
									<a href="<%=path %>/BookList?cmd=健康">健康</a>| <a
										href="<%=path %>/BookList?cmd=家园">家园</a>
								</div>
								<div class="bkg_strip"></div>
							</div>
						</li>
						<li>
							<div class="book_cate">
								<b>经管</b>管理&nbsp;|&nbsp;金融
							</div>
							<div class="cate_detail" style="top:-72px;">
								<div class="cate_title">经管秘籍</div>
								<div class="cline"></div>
								<div class="cate_item">
									<a href="<%=path %>/BookList?cmd=经济学" style="width:60px;">经济学</a>|
									<a href="<%=path %>/BookList?cmd=管理">管理</a>| <a
										href="<%=path %>/BookList?cmd=金融">金融</a>
								</div>
								<div class="cate_item">
									<a href="<%=path %>/BookList?cmd=营销">营销</a>| <a
										href="<%=path %>/BookList?cmd=理财">理财</a>| <a
										href="<%=path %>/BookList?cmd=股票">股票</a>
								</div>
								<div class="cate_item">
									<a href="<%=path %>/BookList?cmd=企业史" style="width:60px;">企业史</a>
								</div>
								<div class="bkg_strip"></div>
							</div>
						</li>
						<li>
							<div class="book_cate">
								<b>科技</b>算法&nbsp;|&nbsp;编程
							</div>
							<div class="cate_detail" style="top:-80px;">
								<div class="cate_title">科技之光</div>
								<div class="cline"></div>
								<div class="cate_item">
									<a href="<%=path %>/BookList?cmd=科普">科普</a>| <a
										href="<%=path %>/BookList?cmd=互联网" style="width:60px;">互联网</a>|
									<a href="<%=path %>/BookList?cmd=编程">编程</a>
								</div>
								<div class="cate_item">
									<a href="<%=path %>/BookList?cmd=神经网络" style="width:80px;">神经网络</a>|
									<a href="<%=path %>/BookList?cmd=算法">算法</a>| <a
										href="<%=path %>/BookList?cmd=通信">通信</a>
								</div>
								<div class="bkg_strip"></div>
							</div>
						</li>
					</ul>
				</div>

				<!--carousel-->
				<div class="carousel right">
					<div id="owl-demo" class="owl-carousel">
						
						<c:if test="${!empty newsCarousel }">
						  <c:forEach items="${newsCarousel}" var="new">
							<a class="item" target="_blank"	href="pages/user/newsdetail.aspx?newid=${new.guid}">
							<img	src="${new.imagePath}" alt="" /></a>
						  </c:forEach>
						</c:if>
						
					</div>
				</div>
				<!--carousel above-->

				<div class="clear"></div>
			</div>
		</div>

		<!--第三主题-->
		<div class="theme"
			style="height:625px;background:url('<%=path %>/images/theme2.jpg') no-repeat;">
			<div class="theme_con" style="width:1200px;margin-top:20px;">
				<div class="title">好书尽览</div>
				<div class="order_l">
					<div class="upfastest left">
						<!--标头-->
						<div>
							<span>上升</span> <b>最快</b> <span class="line"></span>
						</div>

						<!--图片陈列-->
						<div class="picturelist">
						 <%-----c:if --------%>
							  <c:if test="${!empty bookPrompFast }">
							  	<c:forEach items="${bookPrompFast}" var="bookPF"> 
									<div class="picture left">
										<a href="<%=path %>/BookDetailCtrl?id=${ bookPF.guid}"	target="_blank">
										  <img src="<%=path %>${ bookPF.imagePath}" alt="图片逃跑啦!" /> 
										  <span>${ bookPF.bookname}</span>
										</a>
									 </div>
								 </c:forEach>
							  </c:if>
						 </div>
					</div>
						
				<!--
                    <div class="picture left">
                       <a href="#">
                       <img src="http://img14.360buyimg.com/n1/jfs/t1414/42/359340205/110609/2c3d00c0/557ab472Ndc13db33.jpg"/>
                        <span>Swift与Cocoa框架开发</span>
                        </a>
                    </div>
                  -->

					<div class="ranking_list  right">
						<!--标头-->
						<div>
							<span>热门</span> <b>畅销榜</b> <span class="line"></span>
						</div>
						<div class="ranking_n">
							<div class="ranking_banner">
								<span>总排名（持续更新中。。。）</span>
							</div>
							<div class="ranking_content">
								<span class="icon_tr1"></span> <span class="icon_tr2"></span>
								<ul class="li_statiContent">
									<li class="h"><span class="s">实时排名</span> <span>作品名称</span>
										<span class="z">作者</span> <span>销量</span></li>
									
									<%-------C:FOREACH---- --%>
									<c:if test="${!empty bookRanking }">
									<c:forEach items="${bookRanking}" var="bookR" varStatus="status">
										<li>
										   <c:if test="${status.count<=3}">
										   	 <span class="s1 prop"><b class="sq">${status.count}</b></span>
										   </c:if>
										   <c:if test="${status.count>3 }">
										   	<span class="s1 prop">${status.count }</span>
										   </c:if>
											<a href="<%=path %>/BookDetailCtrl?id=${bookR.guid }" target="_blank">
											 <em class="s2 prop">${bookR.bookname }</em>
											 <em class="s3 prop">${bookR.author }</em>
											 <em class="s4 prop">${bookR.access}</em>
										</a>
										</li>
									</c:forEach>
									</c:if>
									<!--
                            <li>
                                <span class="s1 prop"><b class="sq">2</b></span>
                                <a href="#">
                                    <em class="s2 prop">尸兄（我叫白小飞）</em>
                                    <em class="s3 prop">七度羽</em>
                                    <em class="s4 prop">5000</em>
                                </a>
                            </li>
                            -->
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--第四主题-->
		<div class="theme" style="background:#EDEDED;">
			<div class="title">新书上架</div>
			<div class="theme_con"
				style="width:1100px;margin:40px auto;height:390px;">
				<!--标头-->
				<div class="fourth_h" style="margin-bottom:20px;">
					<span>上架</span> <b>新书</b> <span class="line"></span>
				</div>

				<!--图片hover-->
				<div class="img_hover left">
					<div class="bookfigure"></div>
					<div class="bookfigure" style="left:4px;top:4px;"></div>
					
					<%------C:IF----- --%>
					<c:if test="${!empty newbookInfo }">
						<c:forEach items="${newbookInfo}" var="nbookI" varStatus="status">
							<c:if test="${status.count==1 }">
								<a href="<%=path %>/BookDetailCtrl?id=${nbookI.guid }" target="_blank" style="display:block;"></a>
					   		</c:if>
					   		<c:if test="${status.count!=1 }">
							    <a href="<%=path %>/BookDetailCtrl?id=${nbookI.bookname }" target="_blank" style="display:none;">
							    	<img src="<%=path %>${nbookI.imagePath}" style="border-radius:4px;" />
								</a>
							</c:if>
						</c:forEach> 
		            </c:if>
		 <!--
            <a href="#">
                <img src="//img.alicdn.com/bao/uploaded/i2/TB1rTL3GXXXXXaYXXXX36qXFXXX_114539.jpg_b.jpg" style="display:none;"/>
            </a>
            -->
				</div>

				<!--新书carousel展示-->
				<div class="newbook_d right">
					<div class="bookdetail_hover">
						<div style="background:rgba(100,100,100,0.5)">
							<c:if test="${!empty newbookInfo }">
								<c:forEach items="${newbookInfo}" var="nbook" varStatus="status">
									<c:if test="${status.count==1}">
										<div class="detail_e" style="display:block;">
									</c:if>
									<c:if test="${status.count!=1 }">
										<div class="detail_e" style="display:none;">
									</c:if>
									
												<div class="detail_t">${nbook.bookname }</div>
												<i>${nbook.author }</i> <i>${nbook.publisher }</i>
												<div class="para">${nbook.summary }</div>
										</div>
								</c:forEach>
							 </c:if>
								<!--
                    <div class="detail_e" >
                        <div class="detail_t">聚落回</div>
                            <i>药钱孙</i>
                            <i>北京邮电大学出版社</i>
                            <div class="para">这是一本绯闻绯闻访问传说的故事集锦，我们都很向往那里的生活与故事，只有心灵虔诚的人，才能通向那里。服务范围废物访问废物访问废物访问。</div>
                    </div>
                    -->
							</div>
						</div>
						<div id="newbook_carousel" class="owl-carousel pp">
							
							<c:if test="${!empty newbookInfo }">
								<c:forEach items="${newbookInfo}" var="nwbook" varStatus="status">
									<a class="item">
										<img src="<%=path %>${nwbook.imagePath }"	alt="" />
										${nwbook.bookname }
									</a>
								</c:forEach>
							</c:if>

							<!--
                   <a class="item"><img src="//img.alicdn.com/bao/uploaded/i2_114539.jpg_b.jpg" alt=""/>聚落回</a>
                   -->
						</div>
					</div>
				</div>
			</div>

			<script type="text/template" id="log_reg_temp">
               <div class="log_reg_panel">
                  <div class="tab">
	                <span id="login_span" onclick="loginspan_click(this);">登录</span>
	                <span id="register_span" onclick="registerspan_click(this);">注册</span>
                  </div>
                  <div class="login_part">
	                <form   onsubmit="return login_validate();">
		                <ul class="login">
			                <li>
				                <input type="text" name="username" id="login_u" placeholder="请输入用户名"  onblur="user_empty(this);" />
				                <br/>
				                <span style="color:red;display:none;" id="v_n">用户名不能为空!</span>
			                </li>
			                <li>
				                <input type="password" name="password" id="login_p" placeholder="请输入密码"  onblur="password_empty(this);"/>
				                <br/>
				                <span style="color:red;display:none;" id="v_p">密码不能为空!</span>
			                </li>
			                <li >
				                <input type="submit" class="l_r_btn" value="登录"/>
			                </li>
		                </ul>
	                </form> 
                 </div>
                 <div class="register_part">
	                <form action="handler/register.ashx" method="post" onsubmit="return  register_validate();">
		                <ul class="register">
			                <li>
				                <input type="text" name="username" id="register_u" placeholder="请决定一个用户名" onblur="user_empty(this);IsUsernameExits();" />
				                <br/>
				                <span  style="color:red;display:none;" id="v_n_r">用户名不能为空!</span>
				                <span id="no_reg" style="color:red;display:none;">用户名已被注册</span>
				                <span id="yes_reg" style="color:blue;display:none;">用户名有效</span>
			                </li>
			                <li>
				                <input type="password" name="password" id="register_p" placeholder="请决定一个密码" onblur="password_empty(this);"/>
				                <br/>
				                <span style="color:red;display:none;"  id="v_p_r">密码不能为空!</span>
			                </li>
			                <li >
				                <input type="submit" class="l_r_btn" value="注册"/>
			                </li>
		                </ul>
	                </form>
                </div>
  </div>              
</script>

		</div>

		<div class="footer">
			<div class="copyright"
				style="top:30px;left:600px;color:White;position:absolute;">
				爱我书丛&copy;狂野镖客</div>
		</div>

	<script type="text/javascript">
        $(document).ready(function () {
        	$('#signout').click(function(){
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
    			
    			
            /****书架的删除操作*****/
            $('.delete_favor').click(function () {
                // alert($('.shelf_item').index($(this).parent('.shelf_words').parent('.shelf_item')));
                var target = $(this).parent('.shelf_words').parent('.shelf_item');

                $.ajax({
                    type: 'post',
                    data: { cmd: 'deleteFavor', bguid: target.find('input[type="hidden"]').val() },
                    url: '/handler/user.ashx',
                    success: function (result) {
                        var jd = $.parseJSON(result);

                        if (jd.success) {
                            target.slideUp(500, function () { $(this).remove(); });
                        } else {
                            layer.msg("系统忙，请稍候再试！", { shift: 3, time: 2000 });
                        }
                    }
                });
                //  target.slideUp(500, function () { $(this).remove(); });
            });


            /***头像弹出欢迎界面****/
            $('#portrait').click(function () {
                layer.tips('亲，欢迎您(' + $('#uid').text() + ')登录', '#portrait', { tips: [4, '#78BA32'] });
            });

            /*
            *  上架新书的hover
            */
            $('#newbook_carousel a').mouseover(function () {
                var index = $('#newbook_carousel a').index(this);

                $('.img_hover a').hide();
                $('.img_hover a').eq(index).show();

                $('.detail_e').hide();
                $('.detail_e').eq(index).show();
            });


            /*
            * carousel
            */
            $('#owl-demo').owlCarousel({
                items: 1,
                autoPlay: true
            });

            $('#newbook_carousel').owlCarousel({
                center: true,
                items: 7,
                loop: true
            });

            /*
            *  有关搜索框的操作
            */
            $('#search_input_text').focus(function () {
                $('.search_label').hide();
            });

            $('#search_input_text').blur(function () {
                if ($('#search_input_text').val() != '')
                    return;
                $('input[type=radio][name=filter_rdo]').each(function (index, el) {
                    if (el.checked) {
                        if (el.value == "全部")
                            $('.search_label').html('请输入有关图书信息');
                        else
                            $('.search_label').html('请输入' + el.value);

                        $('.search_label').show();
                    }
                });
            });

            $('input[type=radio][name=filter_rdo]').change(function () {
                $('#search_input_text').val('');

                if ($('input[type=radio][name=filter_rdo]:checked').val() == "全部")
                    $('.search_label').html('请输入有关图书信息');
                else
                    $('.search_label').html('请输入' + $('input[type=radio][name=filter_rdo]:checked').val());

                $('.search_label').show();
            });

            $('.search_button').mouseover(function () {
                $(this).css("background-position", "0px -651px");
            });

            $('.search_button').mouseout(function () {
                $(this).css("background-position", "0px -605px");
            });


            /*
            *  有关书签的操作
            */
            $('#bookshelf').click(function () {
                if ($('#shelf_info').is(':hidden'))
                    $('#shelf_info').show();
                else
                    $('#shelf_info').hide();
            });

         
        });

		</script>
</body>
</html>
