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

public class EncodingFilter implements Filter {
	String encoding=null;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		encoding=null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse rep=(HttpServletResponse)response;
		
/*		System.out.println("isExistSession:"+req.getSession(false));
		System.out.println("sessionid"+req.getRequestedSessionId());
		System.out.println("isValidId"+req.isRequestedSessionIdValid());
		System.out.println("IsIdInCookie"+req.isRequestedSessionIdFromCookie());
		
*/		
		if(encoding!=null)
		{
			req.setCharacterEncoding(encoding);
			rep.setContentType("text/html;charset="+encoding);
			rep.setCharacterEncoding(encoding);
		}
		
		//System.out.println(req.getRequestURI());
		
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		encoding=filterConfig.getInitParameter("encoding");
	}

}
