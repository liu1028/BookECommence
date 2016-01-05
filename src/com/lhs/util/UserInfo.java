package com.lhs.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserInfo {

	/******对于用户是否验证的getter、setter********/
	public static void setAuthentication(HttpServletRequest request, Boolean bo)
	{
		 request.getSession().setAttribute("IsAuthenticated", bo);
	}
	public static Boolean getAuthentication(HttpServletRequest request)
	{
		Boolean bo=(Boolean)request.getSession().getAttribute("IsAuthenticated");
		
		return bo;
	}
	
	/******设置用户的角色名*******/
	public static void setJSM(HttpServletRequest request,String jsm)
	{
		/*****角色名指：admin , user*****/
		request.getSession().setAttribute("JSM", jsm);
	}
	public static String getJSM(HttpServletRequest request)
	{
		return (String)request.getSession().getAttribute("JSM");
	}
	public static void removeJSM(HttpServletRequest request)
	{
		request.getSession().removeAttribute("JSM");
	}

	
	/******设置用户的名字********/
	public static void setUsername(HttpServletRequest request,String uid)
	{
		request.getSession().setAttribute("username", uid);		
	}
	public static String getUsername(HttpServletRequest request)
	{
		return (String)request.getSession().getAttribute("username");
				
	}
	public static void removeUsername(HttpServletRequest request)
	{
		request.getSession().removeAttribute("username");
	}

	
	public static String getContextPath(HttpServletRequest request)
	{
		return request.getContextPath();
	}
}
