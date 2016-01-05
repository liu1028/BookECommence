package com.lhs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lhs.InjectionFrawork.Injection;
import com.lhs.entity.User;
import com.lhs.service.UserService;
import com.lhs.util.MD5Converter;
import com.lhs.util.UserInfo;

public class LoginCtrl extends HttpServlet {

	private static final long serialVersionUID = 5529683923008629371L;
	

	/**
	 * Constructor of the object.
	 */
	public LoginCtrl() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if(request.getServletPath().indexOf("quit")>-1)
		{
			UserInfo.setAuthentication(request, false);
			UserInfo.removeJSM(request);
			UserInfo.removeUsername(request);
			
			PrintWriter writer=response.getWriter();
			writer.println("{\"status\":true}");
		}
		else
			response.sendRedirect(request.getContextPath()+"/login.jsp");
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username=request.getParameter("uid");
		String password=request.getParameter("pwd");
		
		if(!username.equals("")&&!password.equals(""))
		{
			/*******Ioc******/
			UserService userService=(UserService)injector.GetClassObject(UserService.class);
			
			User user=new User();
			user.setUsername(username);
			user.setPassword(password);
						
			User re_user=userService.findUser(username);
			if(re_user!=null)
			{
				if(MD5Converter.getString(password).equals(re_user.getPassword()))
				{
					UserInfo.setUsername(request, username);
					UserInfo.setAuthentication(request, true);
					
					if(re_user.getJsm().equals("user"))
					{
						UserInfo.setJSM(request, "user");
						
						String returnURL=(String)request.getSession().getAttribute("returnURL");
						if(returnURL!=null){
							response.sendRedirect(returnURL);
							request.getSession().removeAttribute("returnURL");
						}
						else
							request.getRequestDispatcher("/UserDefault").forward(request, response);
					}
					else
					{
						UserInfo.setJSM(request,"admin");
						request.getSession().removeAttribute("returnURL");
						response.sendRedirect(request.getContextPath()+"/servlet/admin/index");							
					}
					
				}
				else
				{
					request.setAttribute("error", "用户名或者密码错误！");
					request.getRequestDispatcher("/login.jsp").forward(request, response);				
				}
			}
			else
			{
				request.setAttribute("error", "用户名或者密码错误！");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
			
		}
	}

	Injection injector=null;
	public void init() throws ServletException {
		injector=Injection.getInstance();
	}

}
