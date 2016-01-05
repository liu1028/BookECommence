package com.lhs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lhs.util.UserInfo;

public class AuthenticationFilter implements Filter {
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse rep=(HttpServletResponse)response;
			
		
		//对于没有登录的，要访问任何有权限的页面，都将被重定向
		if(!UserInfo.getAuthentication(req)){
			if(req.getRequestURI().indexOf("/servlet/admin")>-1 || 
					req.getRequestURI().indexOf("/servlet/user")>-1 )
			{
				rep.sendRedirect(UserInfo.getContextPath(req)+"/login.jsp");		
				return;
			}else
			{
				filterChain.doFilter(request, response);
				return;
			}
		}
		
		//对于已经登录的情况
		if(req.getRequestURI().indexOf("/servlet/admin")>-1 && UserInfo.getJSM(req).equals("user"))
			rep.sendRedirect(UserInfo.getContextPath(req)+"/UserDefault");
		else if(req.getRequestURI().indexOf("/servlet/user")>-1 && UserInfo.getJSM(req).equals("admin"))
			rep.sendRedirect(UserInfo.getContextPath(req)+"/servlet/admin/index");
		else
			filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
